package use_case.search_by_cuisine;

public interface SearchCuisineInputBoundary {
    /**
     * Returns a success view with the relevant recipes if the DAO returns a non-empty list of recipes. Otherwise,
     * return a fail view saying the recipes do not exist.
     * @param searchInputData the cuisine the user wants to find recipes for.
     */
    void execute(SearchCuisineInputData searchInputData);
}
