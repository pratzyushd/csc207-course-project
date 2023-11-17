package use_case.search_by_name;

import entity.Recipe;

public interface SearchNameUserDataAccessInterface {
    /**
     * Asks the API for recipes given the name.
     * @param name the name of the recipes that the user wants.
     * @return an array of Recipes that belong to the name.
     */
    Recipe[] searchRecipesByName(String name);
}
