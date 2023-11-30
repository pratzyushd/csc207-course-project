package interface_adapter.tag_recipe;

import interface_adapter.tag_recipe.TagRecipeState;
import use_case.tag_recipe.TagRecipeOutputBoundary;

public class TagRecipePresenter implements TagRecipeOutputBoundary {
    private final TagRecipeViewModel tagRecipeViewModel;

    public TagRecipePresenter(TagRecipeViewModel tagRecipeViewModel) {
        this.tagRecipeViewModel = tagRecipeViewModel;
    }

    /**
     * Prepares and updates the view model with the name of the recipe that we added to a tag.
     * @param recipeName the name of the recipe that we successfully added to tags.
     */
    @Override
    public void prepareSuccessView(String recipeName, String tagName) {
//        FavouriteRecipeState favouriteRecipeState = favouriteRecipeViewModel.getState();
//        favouriteRecipeState.setFavouriteRecipeMessage(recipeName + " has been added to your favourites!");
//        favouriteRecipeViewModel.firePropertyChanged();
    }

    /**
     * Prepares and updates the view model with the name of the recipe that we couldn't add to a tag.
     * @param recipeName the name of the recipe that we failed to add to a tag.
     */
    @Override
    public void prepareFailView(String recipeName, String tagName) {
//        FavouriteRecipeState favouriteRecipeState = favouriteRecipeViewModel.getState();
//        favouriteRecipeState.setFavouriteRecipeMessage(recipeName + " already exists in your favourites!");
//        favouriteRecipeViewModel.firePropertyChanged();
    }
}
