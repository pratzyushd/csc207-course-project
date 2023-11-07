package use_case.search_by_id;

import entity.Recipe;

public class SearchIdOutputData {
    private final String id;
    private final Recipe[] recipes;
    private boolean useCaseFailed;

    public SearchIdOutputData(String id, Recipe[] recipes, boolean useCaseFailed) {
        this.id = id;
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public String getId() {
        return id;
    }

    public Recipe[] getRecipes() { return recipes; }
}
