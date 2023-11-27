package interface_adapter.favourite_recipe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FavouriteRecipeViewModel extends ViewModel {
    private FavouriteRecipeState state = new FavouriteRecipeState();

    public FavouriteRecipeViewModel() {
        super("favourite recipe view");
    }

    public void setState(FavouriteRecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the presenter will call to let the ViewModel know to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FavouriteRecipeState getState() {
        return state;
    }
}
