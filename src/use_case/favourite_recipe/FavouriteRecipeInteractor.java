package use_case.favourite_recipe;

import entity.Recipe;

import java.util.ArrayList;

public class FavouriteRecipeInteractor implements FavouriteRecipeInputBoundary {
    final FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessObject;
    final FavouriteRecipeOutputBoundary favouriteRecipePresenter;

    public FavouriteRecipeInteractor(FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface,
                                     FavouriteRecipeOutputBoundary favouriteRecipeOutputBoundary) {
        this.favouriteRecipeUserDataAccessObject = favouriteRecipeUserDataAccessInterface;
        this.favouriteRecipePresenter = favouriteRecipeOutputBoundary;
    }

    @Override
    public void execute(FavouriteRecipeInputData favouriteRecipeInputData) {
        Recipe recipeToAdd = favouriteRecipeInputData.getRecipe();
        ArrayList<Recipe> userCurrentFavourites = favouriteRecipeInputData.getUser().getFavourites();

        if (userCurrentFavourites.contains(recipeToAdd)) {
            // prepare fail view - recipe already exists in favourites
        } else {
            favouriteRecipeInputData.getUser().addFavourite(recipeToAdd);
            // TODO - use DAO to modify the user's favourite recipes in JSON file
        }
    }
}
