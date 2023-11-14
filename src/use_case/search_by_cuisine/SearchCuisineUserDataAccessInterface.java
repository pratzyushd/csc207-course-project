package use_case.search_by_cuisine;

import entity.Recipe;

public interface SearchCuisineUserDataAccessInterface {
    /**
     * Asks the API for recipes given the cuisine.
     * @param cuisine the area that the user wants the recipes to be originated from.
     * @return an array of Recipes that belong to the cuisine.
     */
    Recipe[] searchRecipesByCuisine(String cuisine);
}
