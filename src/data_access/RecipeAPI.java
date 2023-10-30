package data_access;

import entity.Recipe;

public interface RecipeAPI {
    /** Get a particular recipe from the API based on an ID.
     *
     * @param id the ID that the API identifies the recipe by.
     * @return the recipe corresponding to that id.
     */
    Recipe getRecipeById(String id);

    /** Get a list of recipes from the API that are of the specified cuisine.
     *
     * @param cuisine the cuisine that we want to search for.
     * @return array of Recipe instances for each recipe from the API. This may be an empty list.
     */
    Recipe[] searchRecipesByCuisine(String cuisine);

    /**
     * Get a list of recipes from the API that match the specified name.
     * @param name the name that we want to search for.
     * @return array of Recipe instances for each recipe from the API. This may be an empty list, but will typically
     * contain a single element.
     */
    Recipe[] searchRecipesByName(String name);
}
