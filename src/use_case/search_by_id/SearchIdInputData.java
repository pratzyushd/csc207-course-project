package use_case.search_by_id;

public class SearchIdInputData {
    final private String id;

    public SearchIdInputData(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}
