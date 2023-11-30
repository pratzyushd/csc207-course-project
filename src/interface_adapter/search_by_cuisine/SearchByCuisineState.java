package interface_adapter.search_by_cuisine;

public class SearchByCuisineState {
    private String recipeCuisine = "";
    private String recipeCuisineError = "";

    public SearchByCuisineState(SearchByCuisineState copy) {
        recipeCuisine = copy.recipeCuisine;
        recipeCuisineError = copy.recipeCuisineError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchByCuisineState() {}

    public String getRecipeCuisine() {
        return recipeCuisine;
    }

    public String getRecipeCuisineError() {
        return recipeCuisineError;
    }

    public void setRecipeCuisine(String recipeCuisine) {
        this.recipeCuisine = recipeCuisine;
    }
    public void setRecipeCuisineError(String recipeCuisineError) {
        this.recipeCuisineError = recipeCuisineError;
    }

}
