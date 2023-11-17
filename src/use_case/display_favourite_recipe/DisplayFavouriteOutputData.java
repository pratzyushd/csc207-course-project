package use_case.display_favourite_recipe;

import entity.Recipe;
import java.util.List;

public class DisplayFavouriteOutputData {
    private final List<Recipe> recipes;

    /**
     * Creates an output data object with the given recipes.
     * @param recipes the recipes to display.
     */
    public DisplayFavouriteOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }


}