package use_case.search_by_id;

import entity.Recipe;

public interface SearchIdUserDataAccessInterface {
    /**
     * Asks the API for a recipe given the id.
     * @param id the id that the user wants the recipe from.
     * @return a Recipe that belongs to the id.
     */
    Recipe searchRecipesById(String id);
}
