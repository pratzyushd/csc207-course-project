package interface_adapter.display_recipes;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Map;

public class RecipesViewModel extends ViewModel {
    private final boolean isFavourite;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new RecipesViewModel with the specified isFavourite status.
     *
     * @param isFavourite true if the recipes are for the favorites view, false for tagged recipes.
     */
    public RecipesViewModel(boolean isFavourite) {
        super(isFavourite ? "FavouriteRecipesView" : "TaggedRecipesView");
        this.isFavourite = isFavourite;
    }

    public boolean isFavourite() {
        return this.isFavourite;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.getViewName());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the list of recipes in the view model given a list of recipes as a list of hashmaps.
     * @param recipes the list of recipes to set.
     */
    public void setRecipes(List<Map<String, String>> recipes) {
        //TODO: implement set recipes method

    }


}
