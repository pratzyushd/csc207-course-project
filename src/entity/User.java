package entity;

import java.util.ArrayList;

public interface User {

    void addFavourite(Recipe recipe);

    String getName();

    String getPassword();

    ArrayList<Recipe> getFavourites();
}