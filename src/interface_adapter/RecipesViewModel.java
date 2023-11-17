package interface_adapter;

public class RecipesViewModel extends ViewModel {
    private final boolean isFavourite;

    public RecipesViewModel(boolean isFavourite) {
        super(isFavourite ? "FavouriteRecipesView" : "TaggedRecipesView");
        this.isFavourite = isFavourite;
    }

    public boolean isFavourite() {
        return this.isFavourite;
    }

}