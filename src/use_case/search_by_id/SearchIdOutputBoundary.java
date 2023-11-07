package use_case.search_by_id;

import entity.Recipe;

public interface SearchIdOutputBoundary {
    void prepareSuccessView(Recipe[] recipes);
    void prepareFailView(String error);
}
