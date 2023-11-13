package use_case.search_by_cuisine;

import entity.Recipe;

public class SearchCuisineOutputData {
    private final String cuisine;
    private final Recipe[] recipes;

    public SearchCuisineOutputData(String cuisine, Recipe[] recipes) {
        this.cuisine = cuisine;
        this.recipes = recipes;
    }

    public String getCuisine() {
        return cuisine;
    }

    public Recipe[] getRecipes() { return recipes; }
}
