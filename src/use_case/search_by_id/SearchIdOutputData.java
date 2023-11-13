package use_case.search_by_id;

import entity.Recipe;

public class SearchIdOutputData {
    private final String id;
    private final Recipe[] recipes;

    public SearchIdOutputData(String id, Recipe[] recipes) {
        this.id = id;
        this.recipes = recipes;
    }

    public String getId() {
        return id;
    }

    public Recipe[] getRecipes() { return recipes; }
}
