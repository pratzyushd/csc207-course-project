package use_case.display_user_tags;

import entity.User;

import java.util.List;

public interface DisplayUserTagsDataAccessInterface {

    /**
     * Gets the tags of the given user.
     * @param user the user whose tags to get.
     * @return the tags of the given user.
     */
    List<String> getTags(User user);

    User getUser();
}
