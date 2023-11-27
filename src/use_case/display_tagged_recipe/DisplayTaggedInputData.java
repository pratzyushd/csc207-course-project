package use_case.display_tagged_recipe;


public class DisplayTaggedInputData {
    private final String tag;

    /** Creates an input data object with the given tag.
     * @param tag the tag to filter recipes we want to display.
     */
    public DisplayTaggedInputData(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
