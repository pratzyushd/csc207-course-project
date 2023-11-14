package use_case.search_by_id;

import entity.Recipe;

public interface SearchIdUserDataAccessInterface {
    /**
     * Asks the API for recipes given the id.
     * @param id the id that the user wants the recipes from.
     * @return an array of Recipes that belong to the id.
     */
    Recipe[] searchRecipesById(String id);
}
