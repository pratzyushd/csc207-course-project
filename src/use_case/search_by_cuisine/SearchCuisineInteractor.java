package use_case.search_by_cuisine;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements the search by cuisine use case. Accesses the DAO to retrieve the relevant recipes
 * and formats the output data.
 */
public class SearchCuisineInteractor implements SearchCuisineInputBoundary {
    private final SearchCuisineUserDataAccessInterface userDataAccessObject;
    private final SearchCuisineOutputBoundary searchCuisinePresenter;

    /**
     * Creates an instance of the SearchCuisineInteractor with its corresponding DAO and presenter.
     * @param userDataAccessObject the DAO that retrieves the recipes relevant to the given cuisine.
     * @param searchCuisinePresenter the presenter that modifies the view based on the data retrieved by the DAO.
     */
    public SearchCuisineInteractor(SearchCuisineUserDataAccessInterface userDataAccessObject,
                           SearchCuisineOutputBoundary searchCuisinePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.searchCuisinePresenter = searchCuisinePresenter;
    }
    @Override
    public void execute(SearchCuisineInputData searchCuisineInputData) {
        String cuisine = searchCuisineInputData.getCuisine();
        Recipe[] recipes = userDataAccessObject.searchRecipesByCuisine(cuisine);
        /* Process from an array of Recipe objects to a List of the Map representations
         * of the Recipe objects instead. */
        List<Map<String, String>> recipeList = new ArrayList<>(recipes.length);
        for (Recipe recipe : recipes) {
            recipeList.add(recipe.toMap());
        }
        SearchCuisineOutputData outputData = new SearchCuisineOutputData(cuisine, recipeList);

        if (recipes.length == 0) {
            searchCuisinePresenter.prepareFailView("No results found for given cuisine " + "\"" + cuisine + "\"");
        } else {
            searchCuisinePresenter.prepareSuccessView(outputData);
        }
    }
}
