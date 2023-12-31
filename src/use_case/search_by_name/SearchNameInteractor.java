package use_case.search_by_name;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements the search by name use case. Accesses the DAO to retrieve the relevant recipes
 * and formats the output data.
 */
public class SearchNameInteractor implements SearchNameInputBoundary {
    private final SearchNameUserDataAccessInterface userDataAccessObject;
    private final SearchNameOutputBoundary searchNamePresenter;

    /**
     * Creates an instance of the SearchNameInteractor with its corresponding DAO and presenter.
     * @param userDataAccessObject the DAO that retrieves the recipes relevant to the given name.
     * @param searchNamePresenter the presenter that modifies the view based on the data retrieved by the DAO.
     */
    public SearchNameInteractor(SearchNameUserDataAccessInterface userDataAccessObject,
                                SearchNameOutputBoundary searchNamePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.searchNamePresenter = searchNamePresenter;
    }

    @Override
    public void execute(SearchNameInputData searchNameInputData) {
        String name = searchNameInputData.getName();
        Recipe[] recipes = userDataAccessObject.searchRecipesByName(name);
        System.out.println(recipes.length);
        List<Map<String, String>> recipesAsMap = new ArrayList<>();
        for (Recipe recipe: recipes) {
            recipesAsMap.add(recipe.toMap());
        }
        SearchNameOutputData outputData = new SearchNameOutputData(name, recipesAsMap);

        if (recipes.length == 0) {
            searchNamePresenter.prepareFailView("No results found for given search term " + "\"" + name + "\"");
        } else {
            searchNamePresenter.prepareSuccessView(outputData);
        }
    }
}
