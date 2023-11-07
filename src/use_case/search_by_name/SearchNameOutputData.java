package use_case.search_by_name;

import entity.Recipe;

public class SearchNameOutputData {
    private final String name;
    private final Recipe[] recipes;
    private boolean useCaseFailed;

    public SearchNameOutputData(String cuisine, Recipe[] recipes, boolean useCaseFailed) {
        this.name = cuisine;
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public String getCuisine() {
        return name;
    }

    public Recipe[] getRecipes() { return recipes; }
}
