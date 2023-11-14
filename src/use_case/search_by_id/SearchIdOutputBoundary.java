package use_case.search_by_id;

import entity.Recipe;

public interface SearchIdOutputBoundary {
    /**
     * Output the UI that shows the relevant recipes the user searched for if they existed.
     * @param recipes the relevant recipes retrieved by the DAO and needed to be displayed to the user.
     */
    void prepareSuccessView(Recipe[] recipes);

    /**
     * Output the UI that shows that there doesn't exist any recipe from the specified id.
     * @param error error message telling user that there doesn't exist any recipe from the specified id.
     */
    void prepareFailView(String error);
}
