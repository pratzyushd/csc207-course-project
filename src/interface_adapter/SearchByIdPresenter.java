package interface_adapter;

import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchByIdPresenter implements SearchIdOutputBoundary {
    private final SearchByIdViewModel searchByIdViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByIdPresenter(ViewManagerModel viewManagerModel, SearchByIdViewModel searchByIdViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByIdViewModel = searchByIdViewModel;
    }

    @Override
    public void prepareSuccessView(SearchIdOutputData response) {
//        // On success, switch to the results view.
//        SearchByIdState searchByIdState = searchByIdViewModel.getState();
//        searchByIdState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SearchIdOutputData error) {
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
    }
}
