package use_case.search_by_name;

public class SearchNameInputData {
    final private String name;

    public SearchNameInputData(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
