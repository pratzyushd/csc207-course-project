package use_case.tag_recipe;

public class TagRecipeInputData {
    final private int mealId;
    final private String tagName;

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
