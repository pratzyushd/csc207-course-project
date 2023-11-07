package use_case.search_by_name;

import entity.Recipe;

public interface SearchNameOutputBoundary {
    void prepareSuccessView(Recipe[] recipes);
    void prepareFailView(String error);
}
