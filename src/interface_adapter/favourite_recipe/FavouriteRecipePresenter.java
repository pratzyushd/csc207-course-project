package interface_adapter.favourite_recipe;

import use_case.favourite_recipe.FavouriteRecipeOutputBoundary;

public class FavouriteRecipePresenter implements FavouriteRecipeOutputBoundary {
    private final FavouriteRecipeViewModel favouriteRecipeViewModel;

    public FavouriteRecipePresenter(FavouriteRecipeViewModel favouriteRecipeViewModel) {
        this.favouriteRecipeViewModel = favouriteRecipeViewModel;
    }

    /**
     * Prepares and updates the view model with the name of the recipe that we added to favourites.
     * @param recipeName the name of the recipe that we successfully added to favourites.
     */
    @Override
    public void prepareSuccessView(String recipeName) {
        FavouriteRecipeState favouriteRecipeState = favouriteRecipeViewModel.getState();
        favouriteRecipeState.setFavouriteRecipeMessage(recipeName + " has been added to your favourites!");
        favouriteRecipeViewModel.firePropertyChanged();
    }

    /**
     * Prepares and updates the view model with the name of the recipe that we couldn't add to favourites.
     * @param recipeName the name of the recipe that we failed to add to favourites.
     */
    @Override
    public void prepareFailView(String recipeName) {
        FavouriteRecipeState favouriteRecipeState = favouriteRecipeViewModel.getState();
        favouriteRecipeState.setFavouriteRecipeMessage(recipeName + " already exists in your favourites!");
        favouriteRecipeViewModel.firePropertyChanged();
    }
}
