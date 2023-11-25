package interface_adapter.favourite_recipe;

import use_case.favourite_recipe.FavouriteRecipeOutputBoundary;

public class FavouriteRecipePresenter implements FavouriteRecipeOutputBoundary {
    private final FavouriteRecipeViewModel favouriteRecipeViewModel;

    public FavouriteRecipePresenter(FavouriteRecipeViewModel favouriteRecipeViewModel) {
        this.favouriteRecipeViewModel = favouriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(String recipeName) {
        FavouriteRecipeState favouriteRecipeState = favouriteRecipeViewModel.getState();
        favouriteRecipeState.setFavouriteRecipeMessage(recipeName + " has been added to your favourites!");
        favouriteRecipeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String recipeName) {
        FavouriteRecipeState favouriteRecipeState = favouriteRecipeViewModel.getState();
        favouriteRecipeState.setFavouriteRecipeMessage(recipeName + " already exists in your favourites!");
        favouriteRecipeViewModel.firePropertyChanged();
    }
}
