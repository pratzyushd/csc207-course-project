package use_case.display_favourite_recipe;

public class DisplayFavouriteInputData {

    private final String username;

    /**
     * Creates an input data object with the given user.
     * @param username the user whose favourite recipes we want to display.
     */
    public DisplayFavouriteInputData(String username) { this.username = username; }

}
