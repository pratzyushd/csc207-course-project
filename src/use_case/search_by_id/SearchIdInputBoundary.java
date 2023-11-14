package use_case.search_by_id;

public interface SearchIdInputBoundary {
    /**
     * Returns a success view with the relevant recipes if the DAO returns a non-empty list of recipes. Otherwise,
     * return a fail view saying the recipes do not exist.
     * @param searchInputData the id the user wants to find recipes for.
     */
    void execute(SearchIdInputData searchInputData);
}
