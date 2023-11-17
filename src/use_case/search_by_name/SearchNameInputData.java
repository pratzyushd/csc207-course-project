package use_case.search_by_name;

public class SearchNameInputData {
    private final String name;

    /**
     * Creates an input data object with the given name.
     * @param name the name of certain recipes.
     */
    public SearchNameInputData(String name) {
        this.name = name;
    }

    String getName() { return name; }
}
