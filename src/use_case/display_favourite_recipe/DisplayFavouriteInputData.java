package use_case.display_favourite_recipe;

import entity.User;
public class DisplayFavouriteInputData {

    private final User user;

    /**
     * Creates an input data object with the given user.
     * @param user the user whose favourite recipes we want to display.
     */
    public DisplayFavouriteInputData(User user) { this.user = user; }

}
