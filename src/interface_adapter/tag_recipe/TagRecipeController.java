package interface_adapter.tag_recipe;

import use_case.tag_recipe.TagRecipeInputBoundary;
import use_case.tag_recipe.TagRecipeInputData;

public class TagRecipeController {
    private final TagRecipeInputBoundary tagRecipeInteractor;
    public TagRecipeController(TagRecipeInputBoundary tagRecipeInteractor) {
        this.tagRecipeInteractor = tagRecipeInteractor;
    }

    /**
     * call the tagRecipeInteractor's execute method to start the use case.
     * @param mealId The id of the recipe the user wants to find.
     */
    public void execute(int mealId, String tagName) {
        TagRecipeInputData tagRecipeInputData = new TagRecipeInputData(mealId, tagName);
        tagRecipeInteractor.execute(tagRecipeInputData);
    }
}
