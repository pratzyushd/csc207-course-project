package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The DAO that allows us to access TheMealDB API.
 */
public class TheMealDB implements RecipeAPI {

    /* We don't need an API key here, because TheMealDB API allows us to use the key "1" for development purposes. */
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final RecipeFactory recipeFactory;

    public TheMealDB(RecipeFactory recipeFactory) {
        this.recipeFactory = recipeFactory;
    }
    @Override
    public Recipe searchRecipesById(String id) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "lookup.php?i=" + id))
                .addHeader("Content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!responseFromApiIsSuccessful(response)) {
                return null;
            }

            JSONObject responseBody = new JSONObject(response.body().string());

            /* We get back an empty list i.e. the ID doesn't exist in TheMealDB. */
            if  (!dataIsInAPIResponse(responseBody)) {
                return null;
            }

            /* The API returns an array with only one item, so we know that the first item is the one we want. */
            JSONArray mealArray = responseBody.getJSONArray("meals");
            JSONObject meal = mealArray.getJSONObject(0);
            return constructRecipeFromMealJSON(meal);

        } catch (IOException | JSONException e) {
            return null;
        }
    }

    @Override
    public Recipe[] searchRecipesByCuisine(String cuisine) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "filter.php?a=" + cuisine))
                .addHeader("Content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONArray mealArray = getMealArrayFromResponse(response);
            if (mealArray == null) {
                return new Recipe[0];
            }

            String[] mealIdArray = new String[mealArray.length()];
            for (int i = 0; i < mealArray.length(); i++) {
                JSONObject currentMeal = mealArray.getJSONObject(i);
                mealIdArray[i] = currentMeal.getString("idMeal");
            }

            return getRecipesFromListOfIds(mealIdArray);

        } catch (IOException | JSONException e) {
            return new Recipe[0];
        }
    }

    @Override
    public Recipe[] searchRecipesByName(String name) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "search.php?s=" + name))
                .addHeader("Content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONArray mealArray = getMealArrayFromResponse(response);
            if (mealArray == null) {
                return new Recipe[0];
            }

            Recipe[] recipes = new Recipe[mealArray.length()];
            for (int i = 0; i < mealArray.length(); i++) {
                JSONObject current = mealArray.getJSONObject(i);
                recipes[i] = constructRecipeFromMealJSON(current);
            }

            return recipes;

        } catch (IOException | JSONException e) {
            return new Recipe[0];
        }
    }

    private Boolean responseFromApiIsSuccessful(Response response) {
        if (response.body() == null) {
            return false;
        }
        /* As per code review comment, we might later differentiate the types of errors that we
         * could have, in which case we would change the below line. */
        return response.code() == 200;
    }

    private Boolean dataIsInAPIResponse(JSONObject responseBody) {
        return !(responseBody.isNull("meals"));
    }

    private Recipe constructRecipeFromMealJSON(JSONObject meal) {
        return this.recipeFactory.create(
                meal.getInt("idMeal"),
                meal.getString("strMeal"),
                meal.getString("strCategory"),
                meal.getString("strInstructions"),
                meal.getString("strArea"));
    }

    /**
     * Gets the JSONArray of the meals from the response from the API.
     * @param response the response object from the API
     * @return JSONArray of the meals, or null if some error.
     */
    private JSONArray getMealArrayFromResponse(Response response) throws IOException {
        if (!responseFromApiIsSuccessful(response)) {
            return null;
        }

        JSONObject responseBody = new JSONObject(response.body().string());

        /* We get back an empty list i.e. the ID doesn't exist in TheMealDB. */
        if (!dataIsInAPIResponse(responseBody)) {
            return null;
        }

        return responseBody.getJSONArray("meals");
    }

    /**
     * Method to get a list of recipes from a list of IDs. This is useful in situations where the API only returns a
     * list of IDs, and not the actual recipe information itself (e.g. search by cuisine).
     * @param idList list of strings, each of which is a valid ID.
     * @return array of Recipe objects, each of which contains the recipe information. NOTE: there may be null objects,
     * if we couldn't actually get the recipe objects for some reason.
     */
    private Recipe[] getRecipesFromListOfIds(String[] idList) {
        CountDownLatch latch = new CountDownLatch(idList.length);
        Recipe[] recipeArray = new Recipe[idList.length];
        /* We set our thread pool size to 5 i.e. 5 simultaneous workers at a time */
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < idList.length; i++) {
            final int insertionIndex = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    recipeArray[insertionIndex] = searchRecipesById(idList[insertionIndex]);
                    latch.countDown();
                }
            });
        }

        try {
            /* Ensures that we wait for everything to finish */
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        return recipeArray;

    }

    @Override
    public Recipe[] searchRecipesByListOfIds(String[] ids) {
        return getRecipesFromListOfIds(ids);
    }
}
