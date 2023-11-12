package entity;

import java.util.ArrayList;
import java.util.HashMap;

class CommonUser implements User {

    private final String name;
    private final String password;
    private HashMap<String, ArrayList<Recipe>> tags;
    private ArrayList<Recipe> favourites;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.favourites = new ArrayList<>();
        this.tags = new HashMap<>();
    }

    /**
     * Assign the given recipe to the given tag. If tag does not already exist, create a new one.
     * @param recipe the recipe to which the tag will be assigned.
     * @param tag the tag to be given to the recipe.
     */
    @Override
    public void assignTag(Recipe recipe, String tag) {
        if (tags.containsKey(tag)) {
            tags.get(tag).add(recipe);
        } else {
            ArrayList<Recipe> recipesWithTag = new ArrayList<>();
            recipesWithTag.add(recipe);
            tags.put(tag, recipesWithTag);
        }
    }

    @Override
    public void addFavourite(Recipe recipe) {
        favourites.add(recipe);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public HashMap<String, ArrayList<Recipe>> getAllTagged() {
        return tags;
    }

    @Override
    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }
}