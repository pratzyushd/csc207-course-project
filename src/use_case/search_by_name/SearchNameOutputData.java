package use_case.search_by_name;

import entity.Recipe;

public class SearchNameOutputData {
    private final String name;
    private final Recipe[] recipes;

    /**
     * Creates an output data object with the given name and its associated recipes.
     * @param name the name of certain recipes.
     * @param recipes recipes associated with the given name.
     */
    public SearchNameOutputData(String name, Recipe[] recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public Recipe[] getRecipes() { return recipes; }
}
