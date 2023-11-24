package interface_adapter.search_by_name;

import use_case.search_by_name.SearchNameInputBoundary;
import use_case.search_by_name.SearchNameInputData;

public class SearchByNameController {
    final SearchNameInputBoundary searchNameInteractor;
    public SearchByNameController(SearchNameInputBoundary searchNameInteractor) {
        this.searchNameInteractor = searchNameInteractor;
    }

    /**
     * Call the searchNameInteractor's execute method to start the use case.
     * @param id The id of the recipe the user wants to find.
     */
    public void execute(String id) {
        SearchNameInputData searchNameInputData = new SearchNameInputData(id);

        searchNameInteractor.execute(searchNameInputData);
    }
}