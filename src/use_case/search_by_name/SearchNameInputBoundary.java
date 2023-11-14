package use_case.search_by_name;

public interface SearchNameInputBoundary {
    /**
     * Returns a success view with the relevant recipes if the DAO returns a non-empty list of recipes. Otherwise,
     * return a fail view saying the recipes do not exist.
     * @param searchInputData the name the user wants to find recipes for.
     */
    void execute(SearchNameInputData searchInputData);
}
