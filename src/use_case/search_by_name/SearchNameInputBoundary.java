package use_case.search_by_name;

public interface SearchNameInputBoundary {
    /**
     * Executes the use case then triggers the presenter, giving it either the relevant recipes or an error.
     * @param searchInputData the name the user wants to find recipes for.
     */
    void execute(SearchNameInputData searchInputData);
}
