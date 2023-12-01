package use_case.tag_recipe;

public class TagRecipeInputData {
    final private int mealId;
    final private String tagName;

    /**
     * Create a new input data object for the Tag Recipe use case.
     * @param mealId the ID of the recipe to tag.
     * @param tagName the tag to give that recipe.
     */
    public TagRecipeInputData(int mealId, String tagName) {
        this.mealId = mealId;
        this.tagName = tagName;
    }

    public int getMealId() {
        return mealId;
    }

    public String getTagName() {
        return tagName;
    }
}
