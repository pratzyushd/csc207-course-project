package interface_adapter.search_by_name;

public class SearchByNameState {
    private String recipeName = "";
    private String recipeNameError = "";

    /**
     * Creates an instance of the SearchByNameState with the same state as the provided copy.
     * @param copy The SearchByNameState instance to copy.
     */
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

    public void setRecipeNameError(String recipeNameError) {
        this.recipeNameError = recipeNameError;
    }
}
