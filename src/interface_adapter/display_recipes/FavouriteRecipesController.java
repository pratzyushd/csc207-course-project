package interface_adapter.display_recipes;

import use_case.display_favourite_recipe.DisplayFavouriteInputBoundary;
import use_case.display_favourite_recipe.DisplayFavouriteInputData;

public class FavouriteRecipesController {

    final DisplayFavouriteInputBoundary displayFavouriteInteractor;

    /**
     * Constructs a new FavouriteRecipesController with the specified displayFavouriteInteractor.
     *
     * @param displayFavouriteInteractor The interactor to be used for displaying favorite recipes.
     */
    public FavouriteRecipesController(DisplayFavouriteInputBoundary displayFavouriteInteractor) {
        this.displayFavouriteInteractor = displayFavouriteInteractor;
    }

    /**
     * Executes the display favorite recipes use case
     */
    public void execute() {
        DisplayFavouriteInputData displayFavouriteInputData = new DisplayFavouriteInputData();
        displayFavouriteInteractor.execute(displayFavouriteInputData);
    }
}
