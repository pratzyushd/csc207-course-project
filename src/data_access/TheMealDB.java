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

public class TheMealDB implements RecipeAPI {

    /* We don't need an API key here, because TheMealDB API allows us to use the key "1" for development purposes. */
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/";
    private RecipeFactory recipeFactory;

    public TheMealDB(RecipeFactory recipeFactory) {
        this.recipeFactory = recipeFactory;
    }
    @Override
    public Recipe getRecipeById(String id) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "lookup.php?i=" + id))
                .addHeader("Content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                return null;
            }
            if (response.code() != 200) {
                return null;
            }

            JSONObject responseBody = new JSONObject(response.body().string());

            /* We get back an empty list i.e. the ID doesn't exist in TheMealDB. */
            if (responseBody.isNull("meals")) {
                return null;
            }

            System.out.println(responseBody);
            JSONArray mealArray = responseBody.getJSONArray("meals");
            JSONObject meal = mealArray.getJSONObject(0);
            return this.recipeFactory.create(
                    meal.getInt("idMeal"),
                    meal.getString("strMeal"),
                    meal.getString("strCategory"),
                    meal.getString("strInstructions"),
                    meal.getString("strArea"));

        } catch (IOException | JSONException e) {
            return null;
        }
    }

    @Override
    public Recipe[] searchRecipesByCuisine(String cuisine) {
        return new Recipe[0];
    }

    @Override
    public Recipe[] searchRecipesByName(String name) {
        return new Recipe[0];
    }
}
