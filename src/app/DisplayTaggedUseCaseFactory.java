package app;

import interface_adapter.display_recipes.*;
import interface_adapter.ViewManagerModel;
import use_case.display_tagged_recipe.*;
import view.DisplayTaggedView;

import javax.swing.*;
import java.io.IOException;

/**
 * A factory class for creating the DisplayTaggedUseCase.
 */
public class DisplayTaggedUseCaseFactory {

    private DisplayTaggedUseCaseFactory() {}

    /**
     * Create the final view for the Display Tagged use case.
     * @param viewManagerModel the shared view manger between all the views.
     * @param recipesViewModel The specific view model for this use case.
     * @param taggedRecipeUserDataAccessObject The DAO that implements the functionality for this use case.
     * @return The fully constructed View.
     */
    public static DisplayTaggedView create(
            ViewManagerModel viewManagerModel, RecipesViewModel recipesViewModel,
            DisplayTaggedUserDataAccessInterface taggedRecipeUserDataAccessObject) {

        try {
            TaggedRecipesController taggedRecipesController = createDisplayTaggedUseCase(viewManagerModel, recipesViewModel,
                    taggedRecipeUserDataAccessObject);
            return new DisplayTaggedView(taggedRecipesController, recipesViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static TaggedRecipesController createDisplayTaggedUseCase(ViewManagerModel viewManagerModel, RecipesViewModel
            recipesViewModel, DisplayTaggedUserDataAccessInterface taggedRecipeUserDataAccessObject) throws IOException {

        DisplayTaggedOutputBoundary displayTaggedOutputBoundary = new TaggedRecipesPresenter(recipesViewModel, viewManagerModel);

        DisplayTaggedInputBoundary displayTaggedInteractor = new DisplayTaggedInteractor(
                displayTaggedOutputBoundary, taggedRecipeUserDataAccessObject);

        return new TaggedRecipesController(displayTaggedInteractor);
    }
}