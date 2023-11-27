package interface_adapter;

public class SearchResultState {
    private String recipeName = "";
    private String recipeCategory = "";
    private String recipeInstructions = "";
    private String recipeAreaOfOrigin = "";
    private int recipeId;

    public SearchResultState(SearchResultState copy) {
        recipeName = copy.recipeName;
        recipeCategory = copy.recipeCategory;
        recipeInstructions = copy.recipeInstructions;
        recipeAreaOfOrigin = copy.recipeAreaOfOrigin;
        recipeId = copy.recipeId;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchResultState() {}

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName (String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeAreaOfOrigin() {
        return recipeAreaOfOrigin;
    }

    public void setRecipeAreaOfOrigin(String recipeAreaOfOrigin) {
        this.recipeAreaOfOrigin = recipeAreaOfOrigin;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

}

