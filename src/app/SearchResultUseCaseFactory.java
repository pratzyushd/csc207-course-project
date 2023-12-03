package app;

import data_access.Persistence;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.favourite_recipe.FavouriteRecipeController;
import interface_adapter.favourite_recipe.FavouriteRecipePresenter;
import interface_adapter.favourite_recipe.FavouriteRecipeViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.tag_recipe.TagRecipeController;
import interface_adapter.tag_recipe.TagRecipePresenter;
import interface_adapter.tag_recipe.TagRecipeViewModel;
import use_case.favourite_recipe.FavouriteRecipeInputBoundary;
import use_case.favourite_recipe.FavouriteRecipeInteractor;
import use_case.favourite_recipe.FavouriteRecipeOutputBoundary;
import use_case.favourite_recipe.FavouriteRecipeUserDataAccessInterface;
import use_case.tag_recipe.TagRecipeInputBoundary;
import use_case.tag_recipe.TagRecipeInteractor;
import use_case.tag_recipe.TagRecipeOutputBoundary;
import use_case.tag_recipe.TagRecipeUserDataAccessInterface;
import view.SearchResultView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory to create the components required for the view to display the
 * results from the search use cases, and all the required links between them.
 */
public class SearchResultUseCaseFactory {
    private SearchResultUseCaseFactory() {}

    /**
     * Create the required view for displaying search results.
     * @param viewManagerModel the shared view manager model between all views.
     * @param favouriteRecipeViewModel the view model that we switch ot if the user wants to favourite the recipe.
     * @param searchResultViewModel the view model for displaying the results of a search
     * @param favouriteRecipeUserDataAccessInterface the DAO that allows the user to favourite a recipe.
     * @param jsonPersistence the DAO that implements the persistence, and that stores the user objects.
     * @return the fully constructed View.
     */
    public static SearchResultView create(
            ViewManagerModel viewManagerModel, FavouriteRecipeViewModel favouriteRecipeViewModel, TagRecipeViewModel tagRecipeViewModel,
            SearchResultViewModel searchResultViewModel, FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface,
            TagRecipeUserDataAccessInterface tagRecipeUserDataAccessInterface, Persistence jsonPersistence) {

        try {
            FavouriteRecipeController favouriteRecipeController = createFavouriteRecipeUseCase(favouriteRecipeViewModel,
                    favouriteRecipeUserDataAccessInterface, jsonPersistence);
            TagRecipeController tagRecipeController = createTagRecipeUseCase(tagRecipeViewModel,
                    tagRecipeUserDataAccessInterface, jsonPersistence);
            return new SearchResultView(favouriteRecipeController, tagRecipeController, favouriteRecipeViewModel, tagRecipeViewModel, searchResultViewModel, viewManagerModel);
        } catch (IOException e) {
            // TODO - write a better message to display in the message dialog.
            JOptionPane.showMessageDialog(null, "Cannot favourite recipe.");
        }

        return null;
    }

    private static FavouriteRecipeController createFavouriteRecipeUseCase (FavouriteRecipeViewModel
            favouriteRecipeViewModel, FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface,
                                                                           Persistence jsonPersistence) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        FavouriteRecipeOutputBoundary favouriteRecipePresenter = new FavouriteRecipePresenter(favouriteRecipeViewModel);

        FavouriteRecipeInputBoundary favouriteRecipeInteractor = new FavouriteRecipeInteractor(
                favouriteRecipeUserDataAccessInterface, favouriteRecipePresenter, jsonPersistence);

        return new FavouriteRecipeController(favouriteRecipeInteractor);
    }

    private static TagRecipeController createTagRecipeUseCase (TagRecipeViewModel tagRecipeViewModel,
                                                               TagRecipeUserDataAccessInterface tagRecipeUserDataAccessInterface,
                                                               Persistence jsonPersistence) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        TagRecipeOutputBoundary tagRecipePresenter = new TagRecipePresenter(tagRecipeViewModel);

        TagRecipeInputBoundary tagRecipeInteractor = new TagRecipeInteractor(
                tagRecipeUserDataAccessInterface, tagRecipePresenter, jsonPersistence);

        return new TagRecipeController(tagRecipeInteractor);
    }
}
