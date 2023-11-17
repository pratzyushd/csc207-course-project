package use_case.search_by_id;

public interface SearchIdOutputBoundary {
    /**
     * Output the UI that shows the relevant recipes the user searched for if they existed.
     * @param searchIdOutputData the relevant id and associated recipe retrieved by the DAO
     *                           and needed to be displayed to the user.
     */
    void prepareSuccessView(SearchIdOutputData searchIdOutputData);

    /**
     * Output the UI that shows that there doesn't exist any recipe from the specified id.
     * @param searchIdOutputData error message telling user that there doesn't exist any recipe from the specified id.
     */
    void prepareFailView(SearchIdOutputData searchIdOutputData);
}
