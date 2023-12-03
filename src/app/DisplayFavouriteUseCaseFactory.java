package app;

import interface_adapter.display_recipes.*;
import interface_adapter.ViewManagerModel;
import use_case.display_favourite_recipe.*;
import view.DisplayFavouriteView;

import javax.swing.*;
import java.io.IOException;

/**
 * A factory class for creating the DisplayFavouriteUseCase.
 */
public class DisplayFavouriteUseCaseFactory {

    private DisplayFavouriteUseCaseFactory() {}

    /**
     * Create the final view for the Display Favourite use case.
     * @param viewManagerModel the shared view manger between all the views.
     * @param recipesViewModel The specific view model for this use case.
     * @param favouriteRecipeUserDataAccessObject The DAO that implements the functionality for this use case.
     * @return The fully constructed View.
     */
    public static DisplayFavouriteView create(
            ViewManagerModel viewManagerModel, RecipesViewModel recipesViewModel,
            DisplayFavouriteUserDataAccessInterface favouriteRecipeUserDataAccessObject) {

        try {
            FavouriteRecipesController favouriteRecipesController = createDisplayFavouriteUseCase(viewManagerModel, recipesViewModel,
                    favouriteRecipeUserDataAccessObject);
            return new DisplayFavouriteView(favouriteRecipesController, recipesViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static FavouriteRecipesController createDisplayFavouriteUseCase(ViewManagerModel viewManagerModel, RecipesViewModel
            recipesViewModel, DisplayFavouriteUserDataAccessInterface favouriteRecipeUserDataAccessObject) throws IOException {

        DisplayFavouriteOutputBoundary displayFavouriteOutputBoundary = new FavouriteRecipesPresenter(recipesViewModel, viewManagerModel);

        DisplayFavouriteInputBoundary displayFavouriteInteractor = new DisplayFavouriteInteractor(
                favouriteRecipeUserDataAccessObject, displayFavouriteOutputBoundary);

        return new FavouriteRecipesController(displayFavouriteInteractor);
    }
}

