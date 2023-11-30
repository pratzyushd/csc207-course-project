package use_case.favourite_recipe;

public interface FavouriteRecipeInputBoundary {
    /**
     * Executes the use case.
     * @param favouriteRecipeInputData an object containing the data required to "favourite" a recipe.
     */
    void execute(FavouriteRecipeInputData favouriteRecipeInputData);
}
