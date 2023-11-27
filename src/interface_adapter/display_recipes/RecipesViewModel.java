package interface_adapter.display_recipes;

import entity.Recipe;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

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
     * Sets the list of recipes in the view model.
     *
     * @param recipes The list of recipes to be displayed.
     */
    public void setRecipes(List<Recipe> recipes) {
        //TODO: implement set recipes method

    }


}
