package interface_adapter;

public class SearchByIdState {
    private String recipeId = "";
    private String recipeIdError = null;

    public SearchByIdState(SearchByIdState copy) {
        recipeId = copy.recipeId;
        recipeIdError = copy.recipeIdError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchByIdState() {}

    public String getRecipeId() {
        return recipeId;
    }

    public String getRecipeIdError() {
        return recipeIdError;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void getRecipeIdError(String repeatPasswordError) {
        this.recipeIdError = recipeIdError;
    }
}
