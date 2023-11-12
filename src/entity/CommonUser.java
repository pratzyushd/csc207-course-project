package entity;

import java.util.ArrayList;

class CommonUser implements User {

    private final String name;
    private final String password;
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
    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }
}