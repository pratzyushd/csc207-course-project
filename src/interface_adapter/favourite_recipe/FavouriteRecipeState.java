package interface_adapter.favourite_recipe;

public class FavouriteRecipeState {
    private String favouriteRecipeMessage = null;

    public FavouriteRecipeState(FavouriteRecipeState copy) {
        favouriteRecipeMessage = copy.favouriteRecipeMessage;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public FavouriteRecipeState() {}

    public String getFavouriteRecipeMessage() {
        return favouriteRecipeMessage;
    }

    public void setFavouriteRecipeMessage(String favouriteRecipeMessage) {
        this.favouriteRecipeMessage = favouriteRecipeMessage;
    }
}
