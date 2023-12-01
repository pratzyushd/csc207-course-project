package interface_adapter;

import java.util.ArrayList;

public class SearchResultState {
    private ArrayList<String> recipeNames = new ArrayList<>();
    private ArrayList<String> recipeCategories = new ArrayList<>();
    private ArrayList<String> recipeInstructions = new ArrayList<>();
    private ArrayList<String> recipeAreaOfOrigins = new ArrayList<>();
    private ArrayList<Integer> recipeIds = new ArrayList<>();
    private String searchTerm = "";

    /**
     * Create a new instance based on the copy, overwriting our instance vars with the ones from the copy.
     * @param copy the copy to base ourselves on.
     */
    public SearchResultState(SearchResultState copy) {
        recipeNames = copy.recipeNames;
        recipeCategories = copy.recipeCategories;
        recipeInstructions = copy.recipeInstructions;
        recipeAreaOfOrigins = copy.recipeAreaOfOrigins;
        recipeIds = copy.recipeIds;
        searchTerm = copy.searchTerm;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchResultState() {}

    public ArrayList<String> getRecipeNames() {
        return recipeNames;
    }

    public ArrayList<String> getRecipeCategories() {
        return recipeCategories;
    }

    public ArrayList<String> getRecipeInstructions() {
        return recipeInstructions;
    }

    public ArrayList<String> getRecipeAreaOfOrigins() {
        return recipeAreaOfOrigins;
    }

    public ArrayList<Integer> getRecipeIds() {
        return recipeIds;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void setRecipeNames(ArrayList<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    public void setRecipeCategories(ArrayList<String> recipeCategories) {
        this.recipeCategories = recipeCategories;
    }

    public void setRecipeInstructions(ArrayList<String> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public void setRecipeAreaOfOrigins(ArrayList<String> recipeAreaOfOrigins) {
        this.recipeAreaOfOrigins = recipeAreaOfOrigins;
    }

    public void setRecipeIds(ArrayList<Integer> recipeIds) {
        this.recipeIds = recipeIds;
    }

}

