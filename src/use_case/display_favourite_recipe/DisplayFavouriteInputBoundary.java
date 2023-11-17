package use_case.display_favourite_recipe;

public interface DisplayFavouriteInputBoundary {
    /**
     * Executes the use case
     * @param input the user whose favourite recipes we want to display.
     */
    void execute(DisplayFavouriteInputData input);
}