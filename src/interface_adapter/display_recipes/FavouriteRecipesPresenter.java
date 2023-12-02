package interface_adapter.display_recipes;

import interface_adapter.ViewManagerModel;
import use_case.display_favourite_recipe.DisplayFavouriteOutputBoundary;
import use_case.display_favourite_recipe.DisplayFavouriteOutputData;

public class FavouriteRecipesPresenter implements DisplayFavouriteOutputBoundary {

        private final RecipesViewModel favouriteRecipesViewModel;
        private final ViewManagerModel viewManagerModel;

        /**
         * Constructs a new FavouriteRecipesPresenter with the specified favouriteRecipesViewModel
         * and viewManagerModel.
         *
         * @param favouriteRecipesViewModel The view model for favorite recipes.
         * @param viewManagerModel The model for managing views in the application.
         */
        public FavouriteRecipesPresenter(RecipesViewModel favouriteRecipesViewModel, ViewManagerModel viewManagerModel) {
                this.favouriteRecipesViewModel = favouriteRecipesViewModel;
                this.viewManagerModel = viewManagerModel;
        }

        /**
         * Prepares and updates the view with the data from the display favorite recipes use case output.
         *
         * @param outputData The output data from the display favorite recipes use case.
         */
        public void prepareSuccessView(DisplayFavouriteOutputData outputData) {
                favouriteRecipesViewModel.setRecipes(outputData.getRecipes());
                viewManagerModel.setActiveView(favouriteRecipesViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
        }
}
