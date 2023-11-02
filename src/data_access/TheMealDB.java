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
    private static final String API_URL = "www.themealdb.com/api/json/v1/1/";
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

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.body() == null) {
                return null;
            }
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") != 200) {
                return null;
            }
            JSONArray mealArray = responseBody.getJSONArray("meals");
            // TODO: initialise recipe using recipe factory.
        } catch (IOException | JSONException e) {
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return null;
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
