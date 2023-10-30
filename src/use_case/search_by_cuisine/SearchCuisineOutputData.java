package use_case.search_by_cuisine;

import entity.Recipe;

public class SearchCuisineOutputData {
    private final String cuisine;
    private final Recipe[] recipes;
    private boolean useCaseFailed;

    public SearchCuisineOutputData(String cuisine, Recipe[] recipes, boolean useCaseFailed) {
        this.cuisine = cuisine;
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public String getCuisine() {
        return cuisine;
    }

    public Recipe[] getRecipes() { return recipes; }
}
