package interface_adapter.tag_recipe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TagRecipeViewModel extends ViewModel {
    private TagRecipeState state = new TagRecipeState();

    public TagRecipeViewModel() {
        super("tag recipe view");
    }

    public void setState(TagRecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the presenter will call to let the ViewModel know to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Add an observer to this ViewModel.
     * @param listener the observer to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public TagRecipeState getState() {
        return state;
    }
}
