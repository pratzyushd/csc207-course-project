package use_case.initial_app_launch;

import entity.User;

import java.util.List;
import java.util.Map;

public class InitialAppLaunchOutputData {
    //todo edit javadoc
    private final User user;

    /**
     * Creates an output data object with the given recipes.
     * @param user the recipes to display.
     */
    public InitialAppLaunchOutputData(User user) {this.user = user;}

    public User getUser() {return user;}

}