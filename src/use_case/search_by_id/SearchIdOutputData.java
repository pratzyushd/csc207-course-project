package use_case.search_by_id;

import entity.Recipe;

public class SearchIdOutputData {
    private final String id;
    private final Recipe[] recipes;

    /**
     * Creates an output data object with the given id and its associated recipes.
     * @param id the id associated with certain recipes.
     * @param recipes recipes associated with the given id.
     */
    public SearchIdOutputData(String id, Recipe[] recipes) {
        this.id = id;
        this.recipes = recipes;
    }

    public String getId() { return id; }

    public Recipe[] getRecipes() { return recipes; }
}
