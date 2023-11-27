package app;

import entity.CommonUserFactory;
import entity.UserFactory;
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

public class SearchByNameUseCaseFactory {
    private SearchByNameUseCaseFactory() {}

    public static SearchByNameView create(
            ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel,
            SearchNameUserDataAccessInterface searchNameUserDataAccessObject) {

        try {
            SearchByNameController searchByNameController = createSearchByNameUseCase(viewManagerModel, searchByNameViewModel,
                    searchNameUserDataAccessObject);
            return new SearchByNameView(searchByNameController, searchByNameViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchByNameController createSearchByNameUseCase(ViewManagerModel viewManagerModel, SearchByNameViewModel
            searchByNameViewModel, SearchNameUserDataAccessInterface searchNameUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchNameOutputBoundary searchNameOutputBoundary = new SearchByNamePresenter(viewManagerModel, searchByNameViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SearchNameInputBoundary searchNameInteractor = new SearchNameInteractor(
                searchNameUserDataAccessObject, searchNameOutputBoundary);

        return new SearchByNameController(searchNameInteractor);
    }
}
