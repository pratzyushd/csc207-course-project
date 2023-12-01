package interface_adapter.search_by_name;

import interface_adapter.SearchResultState;
import interface_adapter.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_name.SearchNameOutputBoundary;
import use_case.search_by_name.SearchNameOutputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchByNamePresenter implements SearchNameOutputBoundary {
    private final SearchByNameViewModel searchByNameViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SearchResultViewModel searchResultViewModel;

    /**
     * Creates an instance of the SearchByNamePresenter with its corresponding view manager model and view model.
     * @param viewManagerModel The view manager model responsible for switching between views.
     * @param searchByNameViewModel The view model that alerts the view when something has changed.
     */
    public SearchByNamePresenter(ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel,
                                 SearchResultViewModel searchResultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByNameViewModel = searchByNameViewModel;
        this.searchResultViewModel = searchResultViewModel;
    }

    /**
     * Output a UI with all the relevant recipes for the user.
     * @param response contains the name and its associated recipes retrieved by the DAO and needed
     * to be displayed to the user.
     */
    @Override
    public void prepareSuccessView(SearchNameOutputData response) {
        System.out.println(response.getRecipes().size());
        for (Map<String, String> recipe : response.getRecipes()) {
            System.out.println(recipe.get("name"));
        }

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
        searchResultState.setSearchTerm(response.getName());
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
     * Output a UI with an error message telling the user that there doesn't exist any recipes with the input name.
     * @param error error message telling user that there doesn't exist any recipe from the
     * specified name.
     */
    @Override
    public void prepareFailView(String error) {
        SearchByNameState searchByNameState = searchByNameViewModel.getState();
        searchByNameState.setRecipeNameError(error);
        searchByNameViewModel.firePropertyChanged();
    }
}
