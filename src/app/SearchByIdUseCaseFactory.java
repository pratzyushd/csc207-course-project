package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.SearchResultViewModel;
import interface_adapter.search_by_id.SearchByIdPresenter;
import interface_adapter.search_by_id.SearchByIdController;
import interface_adapter.search_by_id.SearchByIdViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdInputBoundary;
import use_case.search_by_id.SearchIdInteractor;
import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import view.SearchByIdView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory to create the components required for the Search By ID use case, and all the
 * required links between them.
 */
public class SearchByIdUseCaseFactory {
    private SearchByIdUseCaseFactory() {}

    /**
     * Create the final view for the Search By ID use case.
     * @param viewManagerModel the shared view manger between all the views.
     * @param searchByIdViewModel The specific view model for this use case.
     * @param searchResultViewModel The results view model, which is what we switch to after this use case is executed.
     * @param searchIdUserDataAccessObject The DAO that implements the functionality for this use case.
     * @return The fully constructed View.
     */
    public static SearchByIdView create(
            ViewManagerModel viewManagerModel, SearchByIdViewModel searchByIdViewModel,
            SearchResultViewModel searchResultViewModel, SearchIdUserDataAccessInterface searchIdUserDataAccessObject) {

        try {
            SearchByIdController searchByIdController = createSearchByIdUseCase(viewManagerModel, searchByIdViewModel,
                    searchResultViewModel, searchIdUserDataAccessObject);
            return new SearchByIdView(searchByIdController, searchByIdViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchByIdController createSearchByIdUseCase(ViewManagerModel viewManagerModel, SearchByIdViewModel
            searchByIdViewModel, SearchResultViewModel searchResultViewModel, SearchIdUserDataAccessInterface searchIdUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchIdOutputBoundary searchIdOutputBoundary = new SearchByIdPresenter(viewManagerModel, searchByIdViewModel, searchResultViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SearchIdInputBoundary searchIdInteractor = new SearchIdInteractor(
                searchIdUserDataAccessObject, searchIdOutputBoundary);

        return new SearchByIdController(searchIdInteractor);
    }
}
