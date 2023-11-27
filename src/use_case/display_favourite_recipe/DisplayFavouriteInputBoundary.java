package use_case.display_favourite_recipe;

public interface DisplayFavouriteInputBoundary {
    /**
     * Executes the use case
     * An empty DisplayFavouriteInputData object is passed as parameter to remain consistent with Clean Architecture and
     * remain extensible in the future even though input data is not needed for this use case.
     */
    void execute(DisplayFavouriteInputData input);
}