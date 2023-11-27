package use_case.display_tagged_recipe;

import java.util.HashMap;
import java.util.List;

public class DisplayTaggedOutputData {

    private final List<HashMap> recipes;

    /**
     * Creates an output data object with the given recipes.
     * @param recipes the recipes to display.
     */
    public DisplayTaggedOutputData(List<HashMap> recipes) { this.recipes = recipes; }

    public List<HashMap> getRecipes() { return recipes; }
}