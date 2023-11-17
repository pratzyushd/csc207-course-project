package use_case.display_tagged_recipe;

import entity.User;

public class DisplayTaggedInputData {
    private final String username;
    private final String tag;

    public DisplayTaggedInputData(String username, String tag) {
        this.username = username;
        this.tag = tag;
    }

    public String getUsername() {
        return username;
    }

    public String getTag() {
        return tag;
    }
}
