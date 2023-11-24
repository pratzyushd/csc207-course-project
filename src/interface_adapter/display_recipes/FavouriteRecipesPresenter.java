package interface_adapter.display_recipes;

import interface_adapter.ViewManagerModel;
import use_case.display_favourite_recipe.DisplayFavouriteOutputBoundary;
import use_case.display_favourite_recipe.DisplayFavouriteOutputData;

public class FavouriteRecipesPresenter implements DisplayFavouriteOutputBoundary {

        private final RecipesViewModel favouriteRecipesViewModel;
        private final ViewManagerModel viewManagerModel;

        public FavouriteRecipesPresenter(RecipesViewModel favouriteRecipesViewModel, ViewManagerModel viewManagerModel) {
                this.favouriteRecipesViewModel = favouriteRecipesViewModel;
                this.viewManagerModel = viewManagerModel;
        }

        public void prepareSuccessView(DisplayFavouriteOutputData outputData) {
                favouriteRecipesViewModel.setRecipes(outputData.getRecipes());
                viewManagerModel.setActiveView(favouriteRecipesViewModel.getViewName());
        }
}
