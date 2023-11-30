package use_case.tag_recipe;

public class TagRecipeInputData {
    final private int mealId;
    final private String tagName;

    public TagRecipeInputData(int mealId, String tagName) {
        this.mealId = mealId;
        this.tagName = tagName;
    }

    int getMealId() {
        return mealId;
    }

    String getTagName() {
        return tagName;
    }
}
