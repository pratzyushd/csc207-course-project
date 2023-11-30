package use_case.search_by_cuisine;

import java.util.List;
import java.util.Map;

public class SearchCuisineOutputData {
    private final String cuisine;
    private final List<Map<String, String>> recipes;

    /**
     * Creates an output data object with the given cuisine name and its associated recipes.
     * @param cuisine area the recipe is originated from.
     * @param recipes recipes associated with the given cuisine.
     */
    public SearchCuisineOutputData(String cuisine, List<Map<String, String>> recipes) {
        this.cuisine = cuisine;
        this.recipes = recipes;
    }

    public String getCuisine() { return cuisine; }

    public List<Map<String, String>> getRecipes() { return recipes; }
}
