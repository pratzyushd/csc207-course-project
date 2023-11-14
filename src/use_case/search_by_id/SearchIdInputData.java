package use_case.search_by_id;

public class SearchIdInputData {
    private final String id;

    /**
     * Creates an input data object with the given id.
     * @param id the id associated with certain recipes.
     */
    public SearchIdInputData(String id) {
        this.id = id;
    }

    String getId() { return id; }
}
