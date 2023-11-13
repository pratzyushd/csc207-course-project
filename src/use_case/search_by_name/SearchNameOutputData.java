package use_case.search_by_name;

import entity.Recipe;

public class SearchNameOutputData {
    private final String name;
    private final Recipe[] recipes;

    public SearchNameOutputData(String cuisine, Recipe[] recipes) {
        this.name = cuisine;
        this.recipes = recipes;
    }

    public String getCuisine() {
        return name;
    }

    public Recipe[] getRecipes() { return recipes; }
}
