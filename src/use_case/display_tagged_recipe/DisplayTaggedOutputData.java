package use_case.display_tagged_recipe;

import java.util.List;
import entity.Recipe;

public class DisplayTaggedOutputData {

    private final List<Recipe> recipes;

    public DisplayTaggedOutputData(List<Recipe> recipes) { this.recipes = recipes; }

    public List<Recipe> getRecipes() { return recipes; }
}