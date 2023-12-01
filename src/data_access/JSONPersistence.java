package data_access;

import entity.Recipe;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class JSONPersistence implements Persistence {
    private final UserFactory userFactory;
    private String filePath;
    private final RecipeAPI recipeDAO;
    private User currentUser;

    /**
     * Construct a new instance of the JSONPersistence DAO.
     * @param userFactory a factory to make user's with. This is used to create the user when we load it.
     * @param filePath the path to the file that the information is read from / written to.
     * @param recipeDAO the DAO that allows access to the recipe information.
     */
    public JSONPersistence(UserFactory userFactory, String filePath, RecipeAPI recipeDAO) {
        this.userFactory = userFactory;
        this.filePath = filePath;
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
        HashMap<String, ArrayList<Recipe>> tagsMap = user.getTaggedRecipes();
        String[] tags = tagsMap.keySet().toArray(new String[0]);

        for (String tag : tags) {
            /* Make a JSON Array of the recipe IDs. */
            ArrayList<Recipe> recipeArray = tagsMap.get(tag);
            JSONArray array = new JSONArray();
            for (Recipe recipe : recipeArray) {
                array.put(recipe.getMealId());
            }
            /* Add the tag as the key and the array as the values. */
            obj.put(tag, array);
        }
        return obj;
    }

    /**
     * Create a JSONArray from the given User object's favourites.
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
            writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(obj.toString(4));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a new User object based on the information in the JSON file.
     * This involves constructing new Recipe objects, and making queries to
     * the Recipe API DAO.
     * @return User object with username, favourites, and tags fields all populated.
     */
    public User load() {
        User user;
        String name;
        JSONArray favourites;
        JSONObject tagsMap;
        /* Read and parse the information from the file. */
        try {
            String fileContents = Files.readString(Path.of(this.filePath));
            JSONTokener tokenizer = new JSONTokener(fileContents);
            JSONObject obj = new JSONObject(tokenizer);
            name = obj.getString("username");
            favourites = obj.getJSONArray("favourites");
            tagsMap = obj.getJSONObject("tags");
        } catch (JSONException | IOException e) {
            user = userFactory.create("");
            return user;
        }
        /* Construct the user object. */
        user = userFactory.create(name);
        /* Update user with favourites */
        Recipe[] favouritesList = generateRecipesFromJSONArray(favourites);
        for (Recipe recipe : favouritesList) {
            user.addFavourite(recipe);
        }
        /* Update user with tags */
        for (String key: tagsMap.keySet()) {
            Recipe[] recipeArray = generateRecipesFromJSONArray(tagsMap.getJSONArray(key));
            for (Recipe recipe : recipeArray) {
                user.assignTag(recipe, key);
            }
        }
        setUser(user);
        return user;
    }

    /**
     * Helper method to generate an array of Recipes from a JSON array. Makes use
     * of the functionality in the DAO of getting recieps from a list of IDs.
     * @param array the JSONArray to convert.
     * @return the converted array of Recipe objects.
     */
    private Recipe[] generateRecipesFromJSONArray(JSONArray array) {
        String[] idList = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            idList[i] = String.valueOf(array.getInt(i));
        }

        return recipeDAO.searchRecipesByListOfIds(idList);
    }

    @Override
    public List<Recipe> getFavouriteRecipes(User user) {
        if (usernameDoesNotMatch(user.getUsername())) {
            return new ArrayList<Recipe>();
        }
        return this.currentUser.getFavourites();
    }

    @Override
    public List<Recipe> getTaggedRecipes(User user, String tag) {
        if (usernameDoesNotMatch(user.getUsername())) {
            return new ArrayList<Recipe>();
        }
        if (!this.currentUser.getTaggedRecipes().containsKey(tag)) {
            return new ArrayList<Recipe>();
        }
        return this.currentUser.getTaggedRecipes().get(tag);
    }

    private boolean usernameDoesNotMatch(String username) {
        return !Objects.equals(username, this.currentUser.getUsername());
    }

    @Override
    public User getUser() {
        if (this.currentUser == null) {
            load();
        }
        return this.currentUser;
    }

    public void setUser(User user) {
        this.currentUser = user;
        save(this.currentUser);
    }

    @Override
    public List<String> getTags(User user) {
        HashMap<String, ArrayList<Recipe>> taggedMap = this.currentUser.getTaggedRecipes();
        return new ArrayList<>(taggedMap.keySet());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
