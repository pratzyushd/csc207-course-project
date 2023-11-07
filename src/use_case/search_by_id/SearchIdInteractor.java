package use_case.search_by_id;

import entity.Recipe;

public class SearchIdInteractor implements SearchIdInputBoundary {
    final SearchIdUserDataAccessInterface userDataAccessObject;
    final SearchIdOutputBoundary searchIdPresenter;

    public SearchIdInteractor(SearchIdUserDataAccessInterface userDataAccessInterface,
                              SearchIdOutputBoundary searchIdOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchIdPresenter = searchIdOutputBoundary;
    }

    @Override
    public void execute(SearchIdInputData searchIdInputData) {
        String id = searchIdInputData.getId();
        Recipe[] recipes = userDataAccessObject.searchRecipesById(id);

        if (recipes.length == 0) {
            searchIdPresenter.prepareFailView(id + "recipies do not exist.");
        } else {
            searchIdPresenter.prepareSuccessView(recipes);
        }
    }
}
