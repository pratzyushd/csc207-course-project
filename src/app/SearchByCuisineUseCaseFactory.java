package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.search_by_cuisine.SearchByCuisineController;
import interface_adapter.search_by_cuisine.SearchByCuisinePresenter;
import interface_adapter.search_by_cuisine.SearchByCuisineViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_cuisine.SearchCuisineInputBoundary;
import use_case.search_by_cuisine.SearchCuisineInteractor;
import use_case.search_by_cuisine.SearchCuisineOutputBoundary;
import use_case.search_by_cuisine.SearchCuisineUserDataAccessInterface;
import view.SearchByCuisineView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory to create the components required for the SearchByCuisine use case, and all the
 * required links between them.
 */
public class SearchByCuisineUseCaseFactory {
    private SearchByCuisineUseCaseFactory() {}

    /**
     * Create the final view for the Search By Cuisine use case.
     * @param viewManagerModel The manager for the views, which is shared among all the views.
     * @param searchByCuisineViewModel The specific view model for this use case.
     * @param searchCuisineUserDataAccessObject The required DAO for this use case.
     * @return a fully constructed view.
     */
    public static SearchByCuisineView create(
            ViewManagerModel viewManagerModel, SearchByCuisineViewModel searchByCuisineViewModel,
            SearchResultViewModel searchResultViewModel,
            SearchCuisineUserDataAccessInterface searchCuisineUserDataAccessObject) {

        try {
            SearchByCuisineController searchByCuisineController = createSearchByCuisineUseCase(viewManagerModel, searchByCuisineViewModel,
                    searchResultViewModel, searchCuisineUserDataAccessObject);
            return new SearchByCuisineView(searchByCuisineController, searchByCuisineViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchByCuisineController createSearchByCuisineUseCase(ViewManagerModel viewManagerModel, SearchByCuisineViewModel
            searchByCuisineViewModel, SearchResultViewModel searchResultViewModel, SearchCuisineUserDataAccessInterface searchCuisineUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchCuisineOutputBoundary searchCuisineOutputBoundary = new SearchByCuisinePresenter(viewManagerModel, searchByCuisineViewModel,
                searchResultViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SearchCuisineInputBoundary searchCuisineInteractor = new SearchCuisineInteractor(
                searchCuisineUserDataAccessObject, searchCuisineOutputBoundary);

        return new SearchByCuisineController(searchCuisineInteractor);
    }
}
