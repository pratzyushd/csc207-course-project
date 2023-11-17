package use_case.display_favourite_recipe;

public class DisplayFavouriteInputData {
    private final String username;

    public DisplayFavouriteInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
