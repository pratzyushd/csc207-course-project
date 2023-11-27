package use_case.display_favourite_recipe;

import java.util.List;
import java.util.Map;

public class DisplayFavouriteOutputData {
    private final List<Map<String,String>> recipes;

    /**
     * Creates an output data object with the given recipes.
     * @param recipes the recipes to display.
     */
    public DisplayFavouriteOutputData(List<Map<String,String>> recipes) {this.recipes = recipes;}

    public List<Map<String,String>> getRecipes() {return recipes;}


}