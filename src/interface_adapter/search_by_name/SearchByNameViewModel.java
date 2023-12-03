package interface_adapter.search_by_name;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchByNameViewModel extends ViewModel {
    private SearchByNameState state = new SearchByNameState();

    public SearchByNameViewModel() {
        super("search recipe by name view");
    }

    public void setState(SearchByNameState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the SearchByName Presenter will call to let the ViewModel know
    // to alert the View
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

    public SearchByNameState getState() {
        return state;
    }
}
