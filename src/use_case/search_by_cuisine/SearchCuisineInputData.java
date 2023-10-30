package use_case.search_by_cuisine;

public class SearchCuisineInputData {
    final private String cuisine;

    public SearchCuisineInputData(String cuisine) {
        this.cuisine = cuisine;
    }

    String getCuisine() {
        return cuisine;
    }
}
