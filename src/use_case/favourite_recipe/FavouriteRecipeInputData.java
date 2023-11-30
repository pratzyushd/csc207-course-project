package use_case.favourite_recipe;

public class FavouriteRecipeInputData {
    final private int mealId;

    /**
     * Create a new InputData object based on the provided meal ID.
     * @param mealId the meal ID that this object will store.
     */
    public FavouriteRecipeInputData(int mealId) {
        this.mealId = mealId;
    }

    int getMealId() {
        return mealId;
    }
}
