package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_name.SearchByNameController;
import interface_adapter.search_by_name.SearchByNamePresenter;
import interface_adapter.search_by_name.SearchByNameViewModel;
import use_case.search_by_name.SearchNameInputBoundary;
import use_case.search_by_name.SearchNameInteractor;
import use_case.search_by_name.SearchNameOutputBoundary;
import use_case.search_by_name.SearchNameUserDataAccessInterface;
import view.SearchByNameView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory to create the components required for the Search By Name use case, and all the
 * required links between them.
 */
public class SearchByNameUseCaseFactory {
    private SearchByNameUseCaseFactory() {}

    /**
     * Create the final view for the Search By Name use case.
     * @param viewManagerModel the view manger model that is shared by all views.
     * @param searchByNameViewModel The specific view model for this use case.
     * @param searchResultViewModel the view model for the search result view
     * @param searchNameUserDataAccessObject The DAO that implements the required functionality for this use case
     * @return the fully constructed View.
     */
    public static SearchByNameView create(
            ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel,
            SearchResultViewModel searchResultViewModel, SearchNameUserDataAccessInterface searchNameUserDataAccessObject) {

        try {
            SearchByNameController searchByNameController = createSearchByNameUseCase(viewManagerModel, searchByNameViewModel,
                    searchResultViewModel, searchNameUserDataAccessObject);
            return new SearchByNameView(searchByNameController, searchByNameViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchByNameController createSearchByNameUseCase(ViewManagerModel viewManagerModel, SearchByNameViewModel
            searchByNameViewModel, SearchResultViewModel searchResultViewModel, SearchNameUserDataAccessInterface searchNameUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchNameOutputBoundary searchNameOutputBoundary = new SearchByNamePresenter(viewManagerModel, searchByNameViewModel,
                searchResultViewModel);

        SearchNameInputBoundary searchNameInteractor = new SearchNameInteractor(
                searchNameUserDataAccessObject, searchNameOutputBoundary);

        return new SearchByNameController(searchNameInteractor);
    }
}
