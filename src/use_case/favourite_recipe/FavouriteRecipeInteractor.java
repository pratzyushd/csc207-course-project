package use_case.favourite_recipe;

import data_access.Persistence;
import entity.Recipe;
import entity.User;

import java.util.ArrayList;

/**
 * Implements the favourite recipe use case. Accesses the DAO to retrieve the User object
 * and add their desired recipe to their favourites.
 */
public class FavouriteRecipeInteractor implements FavouriteRecipeInputBoundary {
    final FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessObject;
    final Persistence jsonPersistence;
    final FavouriteRecipeOutputBoundary favouriteRecipePresenter;

    /**
     * Creates an instance of the FavouriteRecipeInteractor with its corresponding DAO and presenter.
     * @param favouriteRecipeUserDataAccessInterface the DAO used to retrieve the recipe.
     * @param favouriteRecipePresenter the presenter that modifies the view based on the data retrieved by the DAO
     */
    public FavouriteRecipeInteractor(FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface,
                                     FavouriteRecipeOutputBoundary favouriteRecipePresenter,
                                     Persistence jsonPersistence) {
        this.favouriteRecipeUserDataAccessObject = favouriteRecipeUserDataAccessInterface;
        this.jsonPersistence = jsonPersistence;
        this.favouriteRecipePresenter = favouriteRecipePresenter;
    }

    /**
     * Executes the use case by interacting with the DAO and triggers the presenter to show a fail or success view.
     * @param favouriteRecipeInputData the input data object containing the id of the recipe the user wants to favourite
     */
    @Override
    public void execute(FavouriteRecipeInputData favouriteRecipeInputData) {
        User user = jsonPersistence.getUser();
        System.out.println(user.getFavourites().size());
        Recipe recipe = favouriteRecipeUserDataAccessObject.searchRecipesById(favouriteRecipeInputData.getMealId());

        ArrayList<Recipe> currentFavourites = user.getFavourites();
        for (Recipe recipe1 : currentFavourites) {
            System.out.println(recipe1.getName());
        }

        if (currentFavourites.contains(recipe)) {
            favouriteRecipePresenter.prepareFailView(recipe.getName());
        } else {
            user.addFavourite(recipe);
            jsonPersistence.setUser(user);
            favouriteRecipePresenter.prepareSuccessView(recipe.getName());
        }
    }
}
