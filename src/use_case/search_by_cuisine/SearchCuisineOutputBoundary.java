package use_case.search_by_cuisine;

public interface SearchCuisineOutputBoundary {
    /**
     * Output the UI that shows the relevant recipes the user searched for if they existed.
     * @param outputData contains the relevant cuisine and its associated recipes retrieved by the
     * DAO that need to be displayed to the user.
     */
    void prepareSuccessView(SearchCuisineOutputData outputData);

    /**
     * Output the UI that shows that there doesn't exist any recipe from the specified cuisine.
     * @param error error message telling user that there doesn't exist any recipe from the specified cuisine.
     */
    void prepareFailView(String error);
}
