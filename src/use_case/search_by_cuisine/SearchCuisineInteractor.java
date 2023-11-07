package use_case.search_by_cuisine;

import entity.Recipe;

public class SearchCuisineInteractor implements SearchCuisineInputBoundary {
    final SearchCuisineUserDataAccessInterface userDataAccessObject;
    final SearchCuisineOutputBoundary searchCuisinePresenter;

    public SearchCuisineInteractor(SearchCuisineUserDataAccessInterface userDataAccessInterface,
                           SearchCuisineOutputBoundary searchCuisineOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchCuisinePresenter = searchCuisineOutputBoundary;
    }

    @Override
    public void execute(SearchCuisineInputData searchCuisineInputData) {
        String cuisine = searchCuisineInputData.getCuisine();
        Recipe[] recipes = userDataAccessObject.searchRecipesByCuisine(cuisine);

        if (recipes.length == 0) {
            searchCuisinePresenter.prepareFailView(cuisine + "recipes do not exist.");
        } else {
            searchCuisinePresenter.prepareSuccessView(recipes);
        }
    }
}
