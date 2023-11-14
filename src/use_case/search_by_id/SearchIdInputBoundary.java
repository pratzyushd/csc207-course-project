package use_case.search_by_id;

public interface SearchIdInputBoundary {
    /**
     * Executes the use case then triggers the presenter, giving it either the relevant recipe or an error.
     * @param searchInputData the id the user wants to find recipes for.
     */
    void execute(SearchIdInputData searchInputData);
}
