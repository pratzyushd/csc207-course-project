package interface_adapter.display_recipes;

import interface_adapter.ViewManagerModel;
import use_case.display_tagged_recipe.DisplayTaggedOutputBoundary;
import use_case.display_tagged_recipe.DisplayTaggedOutputData;

public class TaggedRecipesPresenter implements DisplayTaggedOutputBoundary {

        private final RecipesViewModel taggedRecipesViewModel;
        private final ViewManagerModel viewManagerModel;

        public TaggedRecipesPresenter(RecipesViewModel taggedRecipesViewModel, ViewManagerModel viewManagerModel) {
                this.taggedRecipesViewModel = taggedRecipesViewModel;
                this.viewManagerModel = viewManagerModel;
        }

        public void prepareSuccessView(DisplayTaggedOutputData outputData) {
                taggedRecipesViewModel.setRecipes(outputData.getRecipes());
                viewManagerModel.setActiveView(taggedRecipesViewModel.getViewName());
        }
}
