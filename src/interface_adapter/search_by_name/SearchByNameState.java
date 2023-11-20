package interface_adapter.search_by_name;

public class SearchByNameState {
    private String recipeName = "";
    private String recipeNameError = null;

    public SearchByNameState(SearchByNameState copy) {
        recipeName = copy.recipeName;
        recipeNameError = copy.recipeNameError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchByNameState() {}

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeNameError() {
        return recipeNameError;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void getRecipeNameError(String repeatPasswordError) {
        this.recipeNameError = recipeNameError;
    }
}
