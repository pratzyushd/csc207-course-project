package use_case.initial_app_launch;

import entity.Recipe;
import entity.User;

import java.util.List;

public interface InitialAppLaunchUserDataAccessInterface {
    /**
     * Set the file path for the Persistence class to use.
     * @param filePath the file path for the file we want to use.
     */
    public void setFilePath(String filePath);
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

    /**
     * Load a user from the file path in the DAO.
     * @return the user object for the file at the file path.
     */
    public User load();

    /**
     * Save the given user to the file path in the DAO.
     * @param user the user to save.
     */
    public void save(User user);
}
