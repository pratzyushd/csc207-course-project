package use_case.search_by_cuisine;

import entity.Recipe;

public interface SearchCuisineOutputBoundary {
    void prepareSuccessView(Recipe[] recipes);
    void prepareFailView(String error);
}
