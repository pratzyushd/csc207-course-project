package use_case.display_favourite_recipe;

import java.util.HashMap;
import java.util.List;

public class DisplayFavouriteOutputData {
    private final List<HashMap> recipes;

    /**
     * Creates an output data object with the given recipes.
     * @param recipes the recipes to display.
     */
    public DisplayFavouriteOutputData(List<HashMap> recipes) {this.recipes = recipes;}

    public List<HashMap> getRecipes() {return recipes;}


}