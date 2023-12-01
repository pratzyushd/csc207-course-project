package use_case.tag_recipe;

import entity.Recipe;

public interface TagRecipeUserDataAccessInterface {
    /**
     * Asks the API for a recipe given the id.
     * @param id the id that the user wants the recipe from.
     * @return a Recipe that belongs to the id.
     */
    Recipe searchRecipesById(int id);
}
