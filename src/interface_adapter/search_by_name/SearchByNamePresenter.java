package interface_adapter.search_by_name;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdOutputData;
import use_case.search_by_name.SearchNameOutputBoundary;
import use_case.search_by_name.SearchNameOutputData;

public class SearchByNamePresenter implements SearchNameOutputBoundary {
    private final SearchByNameViewModel searchByNameViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByNamePresenter(ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByNameViewModel = searchByNameViewModel;
    }

    @Override
    public void prepareSuccessView(SearchNameOutputData response) {
        System.out.println(response.getRecipes().length);
        for (Recipe recipe : response.getRecipes()) {
            System.out.println(recipe.getName());
        }
//        System.out.println(response.getRecipes().getName());
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
    public void prepareFailView(SearchNameOutputData error) {
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
    }
}
