package interface_adapter.search_by_cuisine;

import use_case.search_by_cuisine.SearchCuisineInputBoundary;
import use_case.search_by_cuisine.SearchCuisineInputData;

public class SearchByCuisineController {
    final SearchCuisineInputBoundary searchCuisineInteractor;

    /**
     * Create a new controller for the given interactor.
     * @param searchCuisineInteractor the interactor this controller is responsible for.
     */
    public SearchByCuisineController(SearchCuisineInputBoundary searchCuisineInteractor) {
        this.searchCuisineInteractor = searchCuisineInteractor;
    }

    /**
     * Call the searchCuisineInteractor's execute method to start the use case.
     * @param id The ID of the recipe the user wants to find.
     */
    public void execute(String id) {
        SearchCuisineInputData searchCuisineInputData = new SearchCuisineInputData(id);

        searchCuisineInteractor.execute(searchCuisineInputData);
    }
}


