package use_case.tag_recipe;

public interface TagRecipeInputBoundary {
    /**
     * Executes the use case.
     * @param tagRecipeInputData an object containing the data required to "tag" a recipe.
     */
    void execute(TagRecipeInputData tagRecipeInputData);
}
