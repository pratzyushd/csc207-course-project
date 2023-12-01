package data_access;

import entity.User;
import use_case.display_favourite_recipe.DisplayFavouriteUserDataAccessInterface;
import use_case.display_tagged_recipe.DisplayTaggedUserDataAccessInterface;
import use_case.display_user_tags.DisplayUserTagsUserDataAccessInterface;
import use_case.initial_app_launch.InitialAppLaunchUserDataAccessInterface;

/**
 * This interface functions as an aggregation of the interfaces defined by the
 * various use cases and their needs from the Data Access. The interface is
 * implemented by a Persistence class, and as a result that class implements
 * all the required functionality for the various use cases.
 */
public interface Persistence extends DisplayFavouriteUserDataAccessInterface, DisplayTaggedUserDataAccessInterface,
        DisplayUserTagsUserDataAccessInterface, InitialAppLaunchUserDataAccessInterface {
    /**
     * Getter for the User object stored in the Persistence class.
     * @return the User object
     */
    public User getUser();

    /**
     * Setter method for the User object in the Persistence class.
     * @param user the user to overwrite the current one with.
     */
    public void setUser(User user);
}
