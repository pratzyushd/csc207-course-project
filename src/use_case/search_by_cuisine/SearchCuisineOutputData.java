package use_case.search_by_cuisine;

import entity.Recipe;

public class SearchCuisineOutputData {
    private final String cuisine;
    private final Recipe[] recipes;

    /**
     * Creates an output data object with the given cuisine name and its associated recipes.
     * @param cuisine area the recipe is originated from.
     * @param recipes recipes associated with the given cuisine.
     */
    public SearchCuisineOutputData(String cuisine, Recipe[] recipes) {
        this.cuisine = cuisine;
        this.recipes = recipes;
    }

    public String getCuisine() { return cuisine; }

    public Recipe[] getRecipes() { return recipes; }
}
