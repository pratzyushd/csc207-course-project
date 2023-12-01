package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface User {

    /**
     * Assign the given recipe to the given tag. If tag does not already exist, create a new one.
     * @param recipe the recipe to which the tag will be assigned.
     * @param tag the tag to be given to the recipe.
     */
    void assignTag(Recipe recipe, String tag);

    void addFavourite(Recipe recipe);

    String getUsername();

    HashMap<String, ArrayList<Recipe>> getTaggedRecipes();

    ArrayList<Recipe> getFavourites();
}