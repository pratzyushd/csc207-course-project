package use_case.display_tagged_recipe;


import entity.User;

public class DisplayTaggedInputData {
    private final User user;
    private final String tag;

    /** Creates an input data object with the given user and tag.
     * @param user the user whose tagged recipes we want to display.
     * @param tag the tag to filter recipes we want to display.
     */
    public DisplayTaggedInputData(User user, String tag) {
        this.user = user;
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public String getTag() {
        return tag;
    }
}
