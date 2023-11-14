package use_case.search_by_cuisine;

public class SearchCuisineInputData {
    private final String cuisine;

    /**
     * Creates an input data object with the given cuisine name.
     * @param cuisine area the recipe is originated from.
     */
    public SearchCuisineInputData(String cuisine) {
        this.cuisine = cuisine;
    }
    String getCuisine() {
        return cuisine;
    }
}
