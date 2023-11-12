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