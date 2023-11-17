package use_case.display_tagged_recipe;

import java.util.List;
import entity.Recipe;

public class DisplayTaggedOutputData {

    private final List<Recipe> recipes;

    /**
     * Creates an output data object with the given recipes.
     * @param recipes the recipes to display.
     */
    public DisplayTaggedOutputData(List<Recipe> recipes) { this.recipes = recipes; }

    public List<Recipe> getRecipes() { return recipes; }
}