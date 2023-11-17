package interface_adapter;

import entity.User;
import use_case.display_favourite_recipe.DisplayFavouriteInputBoundary;
import use_case.display_favourite_recipe.DisplayFavouriteInputData;

public class FavouriteRecipesController {
    final DisplayFavouriteInputBoundary displayFavouriteInteractor;
    public FavouriteRecipesController(DisplayFavouriteInputBoundary displayFavouriteInteractor) {
        this.displayFavouriteInteractor = displayFavouriteInteractor;
    }

    public void execute(User user) {
        DisplayFavouriteInputData displayFavouriteInputData = new DisplayFavouriteInputData(user);
        displayFavouriteInteractor.execute(displayFavouriteInputData);
    }
}
