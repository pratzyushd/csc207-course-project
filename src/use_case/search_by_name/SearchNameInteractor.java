package use_case.search_by_name;

import entity.Recipe;

public class SearchNameInteractor implements SearchNameInputBoundary {
    final SearchNameUserDataAccessInterface userDataAccessObject;
    final SearchNameOutputBoundary searchNamePresenter;

    public SearchNameInteractor(SearchNameUserDataAccessInterface userDataAccessInterface,
                                SearchNameOutputBoundary searchNameOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchNamePresenter = searchNameOutputBoundary;
    }

    @Override
    public void execute(SearchNameInputData searchNameInputData) {
        String name = searchNameInputData.getName();
        Recipe[] recipes = userDataAccessObject.searchRecipesByName(name);

        if (recipes.length == 0) {
            searchNamePresenter.prepareFailView(name + "recipes do not exist.");
        } else {
            searchNamePresenter.prepareSuccessView(recipes);
        }
    }
}
