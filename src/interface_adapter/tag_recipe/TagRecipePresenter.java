package interface_adapter.tag_recipe;

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
        TagRecipeState tagRecipeState = tagRecipeViewModel.getState();
        tagRecipeState.setTagRecipeMessage(recipeName + " has been added to your tag, " + tagName + ".");
        tagRecipeViewModel.firePropertyChanged();
    }

    /**
     * Prepares and updates the view model with the name of the recipe that we couldn't add to a tag.
     * @param recipeName the name of the recipe that we failed to add to a tag.
     */
    @Override
    public void prepareFailView(String recipeName, String tagName) {
        TagRecipeState tagRecipeState = tagRecipeViewModel.getState();
        if (tagName.isEmpty()) {
            tagRecipeState.setTagRecipeMessage("Please provide a name for the tag you wish to add " + recipeName + " to.");
        } else {
            tagRecipeState.setTagRecipeMessage(recipeName + " already exists in " + tagName + ".");
        }


        tagRecipeViewModel.firePropertyChanged();
    }
}
