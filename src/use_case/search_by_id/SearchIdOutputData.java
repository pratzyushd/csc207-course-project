package use_case.search_by_id;

import entity.Recipe;

public class SearchIdOutputData {
    private final int id;
    private final Recipe recipe;

    /**
     * Creates an output data object with the given id and its associated recipe.
     * @param id the id associated with a certain recipe.
     * @param recipe recipe associated with the given id.
     */
    public SearchIdOutputData(int id, Recipe recipe) {
        this.id = id;
        this.recipe = recipe;
    }

    public int getId() { return id; }

    public Recipe getRecipe() { return recipe; }
}
