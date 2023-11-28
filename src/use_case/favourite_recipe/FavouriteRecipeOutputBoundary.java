package use_case.favourite_recipe;

import entity.Recipe;

public interface FavouriteRecipeOutputBoundary {
    /**
     * Prepares the success view for the user, given the name of the recipe.
     * @param recipeName the name of the recipe that we successfully added to favourites.
     */
    void prepareSuccessView(String recipeName);

    /**
     * Prepares the failure view for the user.
     * @param recipeName the name of the recipe that we failed to add to favourites.
     */
    void prepareFailView(String recipeName);
}
