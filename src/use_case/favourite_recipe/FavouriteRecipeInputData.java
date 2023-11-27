package use_case.favourite_recipe;

public class FavouriteRecipeInputData {
    final private int mealId;

    public FavouriteRecipeInputData(int mealId) {
        this.mealId = mealId;
    }

    int getMealId() {
        return mealId;
    }
}
