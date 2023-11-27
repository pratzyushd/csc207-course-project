package interface_adapter.display_recipes;

import interface_adapter.ViewManagerModel;
import use_case.display_tagged_recipe.DisplayTaggedOutputBoundary;
import use_case.display_tagged_recipe.DisplayTaggedOutputData;

public class TaggedRecipesPresenter implements DisplayTaggedOutputBoundary {

        private final RecipesViewModel taggedRecipesViewModel;
        private final ViewManagerModel viewManagerModel;

        /**
         * Constructs a new TaggedRecipesPresenter with the specified taggedRecipesViewModel
         * and viewManagerModel.
         *
         * @param taggedRecipesViewModel The view model for tagged recipes.
         * @param viewManagerModel The model for managing views in the application.
         */
        public TaggedRecipesPresenter(RecipesViewModel taggedRecipesViewModel, ViewManagerModel viewManagerModel) {
                this.taggedRecipesViewModel = taggedRecipesViewModel;
                this.viewManagerModel = viewManagerModel;
        }

        /**
         * Prepares and updates the view with the data from the display tagged recipes use case output.
         *
         * @param outputData The output data from the display tagged recipes use case.
         */
        public void prepareSuccessView(DisplayTaggedOutputData outputData) {
                taggedRecipesViewModel.setRecipes(outputData.getRecipes());
                viewManagerModel.setActiveView(taggedRecipesViewModel.getViewName());
        }
}
