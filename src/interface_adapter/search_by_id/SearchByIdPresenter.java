package interface_adapter.search_by_id;

import interface_adapter.SearchResultState;
import interface_adapter.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdOutputData;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByIdPresenter implements SearchIdOutputBoundary {
    private final SearchByIdViewModel searchByIdViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchResultViewModel searchResultViewModel;

    /**
     * Creates an instance of the SearchByIdPresenter with its corresponding view manager model and view model.
     * @param viewManagerModel The view manager model responsible for switching between views.
     * @param searchByIdViewModel The view model that alerts the view when something has changed.
     */
    public SearchByIdPresenter(ViewManagerModel viewManagerModel, SearchByIdViewModel searchByIdViewModel,
                               SearchResultViewModel searchResultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByIdViewModel = searchByIdViewModel;
        this.searchResultViewModel = searchResultViewModel;
    }

    /**
     * Prepare the success view given the relevant output data (which must contain the recipe).
     * @param response the OutputData object that stores the relevant recipe that was fetched from the DAO.
     */
    @Override
    public void prepareSuccessView(SearchIdOutputData response) {
        ArrayList<String> recipeNames = new ArrayList<>(Arrays.asList(response.getRecipe().get("name")));
        ArrayList<String> recipeCategories = new ArrayList<>(Arrays.asList(response.getRecipe().get("category")));
        ArrayList<String> recipeInstructions = new ArrayList<>(Arrays.asList(response.getRecipe().get("instructions")));
        ArrayList<String> recipeAreaOfOrigins = new ArrayList<>(Arrays.asList(response.getRecipe().get("areaOfOrigin")));
        ArrayList<Integer> recipeIds = new ArrayList<>(Arrays.asList(response.getId()));

        SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setRecipeNames(recipeNames);
        searchResultState.setRecipeCategories(recipeCategories);
        searchResultState.setRecipeInstructions(recipeInstructions);
        searchResultState.setRecipeAreaOfOrigins(recipeAreaOfOrigins);
        searchResultState.setRecipeIds(recipeIds);
        searchResultState.setSearchTerm(String.valueOf(response.getId()));

        this.searchResultViewModel.setState(searchResultState);
        searchResultViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare the fail view given the particular error message.
     * @param error the error message generated.
     */
    @Override
    public void prepareFailView(String error) {
        SearchByIdState searchByIdState = searchByIdViewModel.getState();
        searchByIdState.setRecipeIdError(error);
        searchByIdViewModel.firePropertyChanged();
    }
}
