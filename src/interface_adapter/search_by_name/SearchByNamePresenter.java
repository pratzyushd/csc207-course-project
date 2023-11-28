package interface_adapter.search_by_name;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdOutputData;
import use_case.search_by_name.SearchNameOutputBoundary;
import use_case.search_by_name.SearchNameOutputData;

import java.util.Map;

public class SearchByNamePresenter implements SearchNameOutputBoundary {
    private final SearchByNameViewModel searchByNameViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Creates an instance of the SearchByNamePresenter with its corresponding view manager model and view model.
     * @param viewManagerModel The view manager model responsible for switching between views.
     * @param searchByNameViewModel The view model that alerts the view when something has changed.
     */
    public SearchByNamePresenter(ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchByNameViewModel = searchByNameViewModel;
    }

    /**
     * Output a UI with all the relevant recipes for the user.
     * @param response contains the name and its associated recipes retrieved by the DAO and needed
     *                             to be displayed to the user.
     */
    @Override
    public void prepareSuccessView(SearchNameOutputData response) {
        System.out.println(response.getRecipes().size());
        for (Map<String, String> recipe : response.getRecipes()) {
            System.out.println(recipe.get("name"));
        }
    }

    /**
     * Output a UI with an error message telling the user that there doesn't exist any recipes with the input name.
     * @param error error message telling user that there doesn't exist any recipe from the
     *                             specified name.
     */
    @Override
    public void prepareFailView(SearchNameOutputData error) {
    }
}
