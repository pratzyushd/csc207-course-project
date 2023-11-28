package interface_adapter.search_by_cuisine;

import interface_adapter.ViewManagerModel;
import use_case.search_by_cuisine.SearchCuisineOutputBoundary;
import use_case.search_by_cuisine.SearchCuisineOutputData;

import java.util.Map;

public class SearchByCuisinePresenter implements SearchCuisineOutputBoundary {
    private final SearchByCuisineViewModel searchByCuisineViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByCuisinePresenter(ViewManagerModel viewManagerModel, SearchByCuisineViewModel searchByCuisineViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByCuisineViewModel = searchByCuisineViewModel;
    }

    @Override
    public void prepareSuccessView(SearchCuisineOutputData response) {
        System.out.println(response.getRecipes().size());
        for (Map<String, String> recipe : response.getRecipes()) {
            System.out.println(recipe.get("name"));
        }
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
    public void prepareFailView(SearchCuisineOutputData error) {
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
    }
}
