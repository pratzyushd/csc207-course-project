package use_case.tag_recipe;

public interface TagRecipeOutputBoundary {
    /**
     * Prepares the success view for the user, given the name of the recipe.
     * @param recipeName the name of the recipe that we successfully added to tags.
     */
    void prepareSuccessView(String recipeName, String tagName);

    /**
     * Prepares the failure view for the user.
     * @param recipeName the name of the recipe that we failed to add to tags.
     */
    void prepareFailView(String recipeName, String tagName);
}
