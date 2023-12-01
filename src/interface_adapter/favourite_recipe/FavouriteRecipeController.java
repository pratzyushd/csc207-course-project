package interface_adapter.favourite_recipe;

import use_case.favourite_recipe.FavouriteRecipeInputBoundary;
import use_case.favourite_recipe.FavouriteRecipeInputData;

public class FavouriteRecipeController {
    final FavouriteRecipeInputBoundary favouriteRecipeInteractor;
    public FavouriteRecipeController(FavouriteRecipeInputBoundary favouriteRecipeInteractor) {
        this.favouriteRecipeInteractor = favouriteRecipeInteractor;
    }

    /**
     * Call the favouriteRecipeInteractor's execute method to start the use case.
     * @param mealId The id of the recipe the user wants to find.
     */
    public void execute(int mealId) {
        FavouriteRecipeInputData favouriteRecipeInputData = new FavouriteRecipeInputData(mealId);
        favouriteRecipeInteractor.execute(favouriteRecipeInputData);
    }
}
