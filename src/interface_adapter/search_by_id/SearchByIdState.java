package interface_adapter.search_by_id;

public class SearchByIdState {
    private String recipeId = "";
    private String recipeIdError = "";

    /**
     * Creates an instance of the SearchByIdState with the same state as the provided copy.
     * @param copy The SearchByIdState instance to copy.
     */
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

    public void setRecipeIdError(String recipeIdError) {
        this.recipeIdError = recipeIdError;
    }
}
