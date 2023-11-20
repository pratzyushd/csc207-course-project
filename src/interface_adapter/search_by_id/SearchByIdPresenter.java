package interface_adapter.search_by_id;

import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdOutputData;

public class SearchByIdPresenter implements SearchIdOutputBoundary {
    private final SearchByIdViewModel searchByIdViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByIdPresenter(ViewManagerModel viewManagerModel, SearchByIdViewModel searchByIdViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByIdViewModel = searchByIdViewModel;
    }

    @Override
    public void prepareSuccessView(SearchIdOutputData response) {
        System.out.println(response.getRecipe().getName());
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
