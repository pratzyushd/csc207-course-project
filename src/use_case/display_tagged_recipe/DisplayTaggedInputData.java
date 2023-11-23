package use_case.display_tagged_recipe;


public class DisplayTaggedInputData {
    private final String username;
    private final String tag;

    /** Creates an input data object with the given user and tag.
     * @param username the user whose tagged recipes we want to display.
     * @param tag the tag to filter recipes we want to display.
     */
    public DisplayTaggedInputData(String username, String tag) {
        this.username = username;
        this.tag = tag;
    }

    public String getUser() {
        return username;
    }

    public String getTag() {
        return tag;
    }
}
