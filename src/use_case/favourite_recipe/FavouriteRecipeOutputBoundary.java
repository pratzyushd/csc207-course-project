package use_case.favourite_recipe;

import entity.Recipe;

public interface FavouriteRecipeOutputBoundary {
    void prepareSuccessView(String recipeName);

    void prepareFailView(String recipeName);
}
