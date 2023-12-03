package interface_adapter.search_by_cuisine;

import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_cuisine.SearchCuisineOutputBoundary;
import use_case.search_by_cuisine.SearchCuisineOutputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchByCuisinePresenter implements SearchCuisineOutputBoundary {
    private final SearchByCuisineViewModel searchByCuisineViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchResultViewModel searchResultViewModel;

    public SearchByCuisinePresenter(ViewManagerModel viewManagerModel, SearchByCuisineViewModel searchByCuisineViewModel,
                                    SearchResultViewModel searchResultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByCuisineViewModel = searchByCuisineViewModel;
        this.searchResultViewModel = searchResultViewModel;
    }

    /**
     * Prepare the success view with the relevant information of the recipes found from the search.
     * @param response contains the relevant cuisine and its associated recipes retrieved by the
     * DAO that need to be displayed to the user.
     */
    @Override
    public void prepareSuccessView(SearchCuisineOutputData response) {
        List<Map<String, String>> recipeResults = response.getRecipes();
        ArrayList<String> recipeNames = new ArrayList<>();
        ArrayList<String> recipeCategories = new ArrayList<>();
        ArrayList<String> recipeInstructions = new ArrayList<>();
        ArrayList<String> recipeAreaOfOrigins = new ArrayList<>();
        ArrayList<Integer> recipeIds = new ArrayList<>();
        for (Map<String, String> recipe : recipeResults) {
            recipeNames.add(recipe.get("name"));
            recipeCategories.add(recipe.get("category"));
            recipeInstructions.add(recipe.get("instructions"));
            recipeAreaOfOrigins.add(recipe.get("areaOfOrigin"));
            recipeIds.add(Integer.valueOf(recipe.get("id")));
        }

        SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setSearchTerm(response.getCuisine());
        searchResultState.setRecipeNames(recipeNames);
        searchResultState.setRecipeCategories(recipeCategories);
        searchResultState.setRecipeInstructions(recipeInstructions);
        searchResultState.setRecipeAreaOfOrigins(recipeAreaOfOrigins);
        searchResultState.setRecipeIds(recipeIds);

        this.searchResultViewModel.setState(searchResultState);
        searchResultViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare the fail view with the relevant error message.
     * @param error OutputData object containing the error.
     */
    @Override
    public void prepareFailView(String error) {
        SearchByCuisineState searchByCuisineState = searchByCuisineViewModel.getState();
        searchByCuisineState.setRecipeCuisineError(error);
        searchByCuisineViewModel.firePropertyChanged();
    }
}
