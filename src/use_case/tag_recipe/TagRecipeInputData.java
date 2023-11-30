package use_case.tag_recipe;

public class TagRecipeInputData {
    final private int mealId;

    public TagRecipeInputData(int mealId) {
        this.mealId = mealId;
    }

    int getMealId() {
        return mealId;
    }
}
