package app;

import interface_adapter.display_recipes.*;
import interface_adapter.ViewManagerModel;
import use_case.display_favourite_recipe.*;
import view.DisplayFavouriteView;

import javax.swing.*;
import java.io.IOException;

public class DisplayFavouriteUseCaseFactory {

    private DisplayFavouriteUseCaseFactory() {}

    public static DisplayFavouriteView create(
            ViewManagerModel viewManagerModel, RecipesViewModel recipesViewModel,
            DisplayFavouriteUserDataAccessInterface favouriteRecipeUserDataAccessObject) {

        try {
            FavouriteRecipesController favouriteRecipesController = createDisplayFavouriteUseCase(viewManagerModel, recipesViewModel,
                    favouriteRecipeUserDataAccessObject);
            return new DisplayFavouriteView(favouriteRecipesController, recipesViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static FavouriteRecipesController createDisplayFavouriteUseCase(ViewManagerModel viewManagerModel, RecipesViewModel
            recipesViewModel, DisplayFavouriteUserDataAccessInterface favouriteRecipeUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        DisplayFavouriteOutputBoundary displayFavouriteOutputBoundary = new FavouriteRecipesPresenter(recipesViewModel, viewManagerModel);

        DisplayFavouriteInputBoundary displayFavouriteInteractor = new DisplayFavouriteInteractor(
                favouriteRecipeUserDataAccessObject, displayFavouriteOutputBoundary);

        return new FavouriteRecipesController(displayFavouriteInteractor);
    }
}

