import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SampleApiUsage {
    private static final String MEAL_DB_API_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String NUTRITION_API_URL = "https://api.api-ninjas.com/v1/nutrition";
    private static final String NUTRITION_API_TOKEN = System.getenv("NUTRITION_API_TOKEN");


    public static String getNutritionApiToken() {
        return NUTRITION_API_TOKEN;
    }
    public static void main(String[]args){
            sampleRecipe();
            sampleNutrition();
    }

    public static void sampleRecipe() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(MEAL_DB_API_URL+"search.php?s=Spaghetti"))
                .addHeader("Content-type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            if (response.body() == null) {
                throw new RuntimeException("Empty response body");
            }
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sampleNutrition() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(NUTRITION_API_URL+"?query=Spaghetti"))
                .addHeader("Content-type", "application/json")
                .addHeader("X-Api-Key", NUTRITION_API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            if (response.body() == null) {
                throw new RuntimeException("Empty response body");
            }
            /* We have to do something special here, because this API gives us back a JSON Array instead of a
             * JSON object. */
            String responseStr = response.body().string();
            JSONArray responseBody = new JSONArray(responseStr);
            System.out.println(responseBody.getJSONObject(0));
            /* We can use the getString method on a JSONObject to get the value of an associated key */
            System.out.println(responseBody.getJSONObject(0).getString("name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}