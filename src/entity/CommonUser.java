package entity;

import java.util.ArrayList;
import java.util.HashMap;

class CommonUser implements User {

    private final String username;
    private final HashMap<String, ArrayList<Recipe>> taggedRecipes;
    private final ArrayList<Recipe> favourites;

    /**
     * Create a User with the given username and password given the username is available.
     * @param username an alphanumeric username assigned by the user.
     */
    CommonUser(String username) {
        this.username = username;
        this.favourites = new ArrayList<>();
        this.taggedRecipes = new HashMap<>();
    }

    @Override
    public void assignTag(Recipe recipe, String tag) {
        if (taggedRecipes.containsKey(tag)) {
            taggedRecipes.get(tag).add(recipe);
        } else {
            ArrayList<Recipe> recipesWithTag = new ArrayList<>();
            recipesWithTag.add(recipe);
            taggedRecipes.put(tag, recipesWithTag);
        }
    }

    @Override
    public void addFavourite(Recipe recipe) {
        favourites.add(recipe);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public HashMap<String, ArrayList<Recipe>> getTaggedRecipes() {
        return taggedRecipes;
    }

    @Override
    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }
}