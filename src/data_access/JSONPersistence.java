package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONPersistence implements Persistence {
    private UserFactory userFactory;
    private RecipeFactory recipeFactory;
    private final File JSONFile;
    private final RecipeAPI recipeDAO;

    public JSONPersistence(UserFactory userFactory, RecipeFactory recipeFactory, String filePath, RecipeAPI recipeDAO) {
        this.userFactory = userFactory;
        this.recipeFactory = recipeFactory;
        this.JSONFile = new File(filePath);
        this.recipeDAO = recipeDAO;
    }

    /**
     * Make a JSONObject out of the tags for a given user.
     * @param user the User object which we make the JSONObject based on.
     * @return JSONObject containing a "tags" key and then an array of
     * JSONObjects, each of which has a key of the tag name, with an associated
     * JSONArray for the recipe IDs.
     */
    private JSONObject JSONForUserTags(User user) {
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        HashMap<String, ArrayList<Recipe>> tagsMap = user.getTaggedRecipes();
        String[] tags = tagsMap.keySet().toArray(new String[0]);

        for (String tag : tags) {
            /* Make a JSON Array of the recipe IDs. */
            ArrayList<Recipe> recipeArray = tagsMap.get(tag);
            for (Recipe recipe : recipeArray) {
                array.put(recipe.getMealId());
            }
            /* Add the tag as the key and the array as the values. */
            obj.put(tag, array);
        }
        return obj;
    }

    /**
     * Create a JSONArray form the given User object's favourites.
     * @param user the User object which we work with.
     * @return JSONArray that contains a list of all the IDs for the recipes
     * that the user has "favourited".
     */
    private JSONArray JSONForUserFavourites(User user) {
        JSONArray array = new JSONArray();
        ArrayList<Recipe> recipeList = user.getFavourites();
        for (Recipe recipe : recipeList) {
            array.put(recipe.getMealId());
        }
        return array;
    }

    /**
     * Create a JSONObject that contains the user's tags (with associated
     * recipes) and favourites.
     * @param user The User object on which we base the output
     * @return A JSONObject that has two keys: "favourites" and "tags".
     * The value for "favourites" is a JSONArray of the IDs of the
     * "favourited" recipes. The value for "tags" is a JSONObject, which
     * contains the tag names as keys, and JSONArrays of the IDs of the
     * recipes as the values.
     */
    private JSONObject constructJSONObjectFromUser(User user) {
        JSONObject obj = new JSONObject();
        JSONObject tagsJSON = JSONForUserTags(user);
        JSONArray favouritesJSON = JSONForUserFavourites(user);
        obj.put("username", user.getUsername());
        obj.put("tags", tagsJSON);
        obj.put("favourites", favouritesJSON);
        return obj;
    }

    /**
     * Save the user's tags and favourites to the file that was given upon
     * object creation.
     * @param user the User object whose contents are saved.
     */
    public void save(User user) {
        JSONObject obj = constructJSONObjectFromUser(user);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.JSONFile));
            writer.write(obj.toString(4));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User load() {
        User user;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.JSONFile))) {
            JSONTokener tokenizer = new JSONTokener(reader);
            JSONObject obj = new JSONObject(tokenizer);
            String name = obj.getString("username");
            JSONArray favourites = obj.getJSONArray("favourites");
            JSONObject tagsMap = obj.getJSONObject("tags");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Recipe[] generateRecipesFromFavourites(JSONArray favourites) {
        Recipe[] recipes = new Recipe[favourites.length()];
        for (int i = 0; i < favourites.length(); i++) {
            recipes[i] = recipeDAO.searchRecipesById(favourites.getString(i));
        }
        return recipes;
    }
}