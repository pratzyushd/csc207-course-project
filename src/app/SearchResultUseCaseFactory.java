package app;

import data_access.Persistence;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.SearchResultViewModel;
import interface_adapter.favourite_recipe.FavouriteRecipeController;
import interface_adapter.favourite_recipe.FavouriteRecipePresenter;
import interface_adapter.favourite_recipe.FavouriteRecipeViewModel;
import interface_adapter.search_by_id.SearchByIdPresenter;
import interface_adapter.search_by_id.SearchByIdController;
import interface_adapter.search_by_id.SearchByIdViewModel;
import interface_adapter.ViewManagerModel;
import use_case.favourite_recipe.FavouriteRecipeInputBoundary;
import use_case.favourite_recipe.FavouriteRecipeInteractor;
import use_case.favourite_recipe.FavouriteRecipeOutputBoundary;
import use_case.favourite_recipe.FavouriteRecipeUserDataAccessInterface;
import use_case.search_by_id.SearchIdInputBoundary;
import use_case.search_by_id.SearchIdInteractor;
import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import view.SearchByIdView;
import view.SearchResultView;

import javax.swing.*;
import java.io.IOException;

public class SearchResultUseCaseFactory {
    private SearchResultUseCaseFactory() {}

    public static SearchResultView create(
            ViewManagerModel viewManagerModel, FavouriteRecipeViewModel favouriteRecipeViewModel,
            SearchResultViewModel searchResultViewModel, FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface,
            Persistence jsonPersistence) {

        try {
            FavouriteRecipeController favouriteRecipeController = createFavouriteRecipeUseCase(favouriteRecipeViewModel,
                    favouriteRecipeUserDataAccessInterface, jsonPersistence);
            return new SearchResultView(favouriteRecipeController, favouriteRecipeViewModel, searchResultViewModel);
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
}
