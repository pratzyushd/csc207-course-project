package app;

import entity.CommonUserFactory;
import entity.UserFactory;
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

public class SearchByIdUseCaseFactory {
    private SearchByIdUseCaseFactory() {}

    public static SearchByIdView create(
            ViewManagerModel viewManagerModel, SearchByIdViewModel searchByIdViewModel,
            SearchIdUserDataAccessInterface searchIdUserDataAccessObject) {

        try {
            SearchByIdController searchByIdController = createSearchByIdUseCase(viewManagerModel, searchByIdViewModel,
                    searchIdUserDataAccessObject);
            return new SearchByIdView(searchByIdController, searchByIdViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchByIdController createSearchByIdUseCase(ViewManagerModel viewManagerModel, SearchByIdViewModel
            searchByIdViewModel, SearchIdUserDataAccessInterface searchIdUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchIdOutputBoundary searchIdOutputBoundary = new SearchByIdPresenter(viewManagerModel, searchByIdViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SearchIdInputBoundary searchIdInteractor = new SearchIdInteractor(
                searchIdUserDataAccessObject, searchIdOutputBoundary);

        return new SearchByIdController(searchIdInteractor);
    }
}
