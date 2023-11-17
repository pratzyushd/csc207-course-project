package use_case.display_favourite_recipe;

public interface DisplayFavouriteOutputBoundary {
    /**
     * Prepares the success view for the user.
     * @param outputData the recipes to display.
     */
    void prepareSuccessView(DisplayFavouriteOutputData outputData);

}