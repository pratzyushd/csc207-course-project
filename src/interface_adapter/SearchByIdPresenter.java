package interface_adapter;

import use_case.search_by_id.SearchIdOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchByIdPresenter {
    private final SearchByIdViewModel searchByIdViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByIdPresenter(ViewManagerModel viewManagerModel, SearchByIdViewModel searchByIdViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByIdViewModel = searchByIdViewModel;
    }

    // todo: add @Overide decorator later
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

    // todo: add @Overide decorator later
    public void prepareFailView(String error) {
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
    }
}
