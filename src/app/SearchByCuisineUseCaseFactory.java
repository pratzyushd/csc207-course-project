package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.search_by_cuisine.SearchByCuisineController;
import interface_adapter.search_by_cuisine.SearchByCuisinePresenter;
import interface_adapter.search_by_cuisine.SearchByCuisineViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_cuisine.SearchCuisineInputBoundary;
import use_case.search_by_cuisine.SearchCuisineInteractor;
import use_case.search_by_cuisine.SearchCuisineOutputBoundary;
import use_case.search_by_cuisine.SearchCuisineUserDataAccessInterface;
import use_case.search_by_id.SearchIdInputBoundary;
import use_case.search_by_id.SearchIdInteractor;
import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import view.SearchByCuisineView;
import view.SearchByIdView;

import javax.swing.*;
import java.io.IOException;

public class SearchByCuisineUseCaseFactory {
    private SearchByCuisineUseCaseFactory() {}

    public static SearchByCuisineView create(
            ViewManagerModel viewManagerModel, SearchByCuisineViewModel searchByCuisineViewModel,
            SearchCuisineUserDataAccessInterface searchCuisineUserDataAccessObject) {

        try {
            SearchByCuisineController searchByCuisineController = createSearchByCuisineUseCase(viewManagerModel, searchByCuisineViewModel,
                    searchCuisineUserDataAccessObject);
            return new SearchByCuisineView(searchByCuisineController, searchByCuisineViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchByCuisineController createSearchByCuisineUseCase(ViewManagerModel viewManagerModel, SearchByCuisineViewModel
            searchByCuisineViewModel, SearchCuisineUserDataAccessInterface searchCuisineUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchCuisineOutputBoundary searchCuisineOutputBoundary = new SearchByCuisinePresenter(viewManagerModel, searchByCuisineViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SearchCuisineInputBoundary searchCuisineInteractor = new SearchCuisineInteractor(
                searchCuisineUserDataAccessObject, searchCuisineOutputBoundary);

        return new SearchByCuisineController(searchCuisineInteractor);
    }
}
