package use_case.search_by_cuisine;

public interface SearchCuisineInputBoundary {
    /**
     * Executes the use case then triggers the presenter, giving it either the relevant recipes or an error.
     * @param searchInputData the cuisine the user wants to find recipes for.
     */
    void execute(SearchCuisineInputData searchInputData);
}
