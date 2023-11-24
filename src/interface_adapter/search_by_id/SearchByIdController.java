package interface_adapter.search_by_id;

import use_case.search_by_id.SearchIdInputBoundary;
import use_case.search_by_id.SearchIdInputData;

public class SearchByIdController {
    final SearchIdInputBoundary searchIdInteractor;
    public SearchByIdController(SearchIdInputBoundary searchIdInteractor) {
        this.searchIdInteractor = searchIdInteractor;
    }

    /**
     * call the searchIdInteractor's execute method to start the use case.
     * @param id The id of the recipe the user wants to find.
     */
    public void execute(String id) {
        SearchIdInputData searchIdInputData = new SearchIdInputData(id);

        searchIdInteractor.execute(searchIdInputData);
    }
}


