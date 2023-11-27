package use_case.favourite_recipe;

import entity.Recipe;

public interface FavouriteRecipeUserDataAccessInterface {
    /**
     * Asks the API for a recipe given the id.
     * @param id the id that the user wants the recipe from.
     * @return a Recipe that belongs to the id.
     */
    Recipe searchRecipesById(int id);
}
