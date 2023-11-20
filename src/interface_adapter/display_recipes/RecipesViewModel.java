package interface_adapter.display_recipes;

import interface_adapter.ViewModel;

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