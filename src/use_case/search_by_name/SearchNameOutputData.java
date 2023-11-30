package use_case.search_by_name;

import java.util.List;
import java.util.Map;

public class SearchNameOutputData {
    private final String name;
    private final List<Map<String, String>> recipes;

    /**
     * Creates an output data object with the given name and its associated recipes.
     * @param name the name of certain recipes.
     * @param recipes recipes associated with the given name.
     */
    public SearchNameOutputData(String name, List<Map<String, String>> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public List<Map<String, String>> getRecipes() { return recipes; }
}
