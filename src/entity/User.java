package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface User {

    void assignTag(Recipe recipe, String tag);

    void addFavourite(Recipe recipe);

    String getUsername();

    String getPassword();

    HashMap<String, ArrayList<Recipe>> getTaggedRecipes();

    ArrayList<Recipe> getFavourites();
}