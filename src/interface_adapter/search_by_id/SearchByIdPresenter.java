package interface_adapter.search_by_id;

import interface_adapter.SearchResultState;
import interface_adapter.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdOutputBoundary;
import use_case.search_by_id.SearchIdOutputData;

import javax.naming.directory.SearchResult;

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

    @Override
    public void prepareSuccessView(SearchIdOutputData response) {
        System.out.println(response.getRecipe().getName());

        SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setRecipeName(response.getRecipe().getName());
        searchResultState.setRecipeCategory(response.getRecipe().getCategory());
        searchResultState.setRecipeInstructions(response.getRecipe().getInstructions());
        searchResultState.setRecipeAreaOfOrigin(response.getRecipe().getAreaOfOrigin());
        searchResultState.setRecipeId(response.getRecipe().getMealId());

        this.searchResultViewModel.setState(searchResultState);
        System.out.println("\n" + searchResultViewModel.getState().getRecipeName());
        searchResultViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchByIdState searchByIdState = searchByIdViewModel.getState();
        searchByIdState.setRecipeIdError(error);
        searchByIdViewModel.firePropertyChanged();
    }
}
