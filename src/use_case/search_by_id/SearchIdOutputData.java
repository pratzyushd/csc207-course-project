package use_case.search_by_id;

import entity.Recipe;

import java.util.Map;

public class SearchIdOutputData {
    private final int id;
    private final Map<String, String> recipe;

    /**
     * Creates an output data object with the given id and its associated recipe.
     * @param id the id associated with a certain recipe.
     * @param recipe recipe associated with the given id.
     */
    public SearchIdOutputData(int id, Map<String, String> recipe) {
        this.id = id;
        this.recipe = recipe;
    }

    public int getId() { return id; }

    public Map<String, String> getRecipe() { return recipe; }
}
