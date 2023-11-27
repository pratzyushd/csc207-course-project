package use_case.favourite_recipe;

import entity.Recipe;

public class FavouriteRecipeOutputData {
    private final Recipe recipe;

    public FavouriteRecipeOutputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
