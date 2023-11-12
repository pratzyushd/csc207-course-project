package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface User {

    void addFavourite(Recipe recipe);

    String getName();

    String getPassword();

    HashMap<String, ArrayList<Recipe>> getAllTagged();

    ArrayList<Recipe> getFavourites();
}