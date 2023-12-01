package interface_adapter.search_by_cuisine;

import interface_adapter.ViewModel;
import interface_adapter.search_by_name.SearchByNameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchByCuisineViewModel extends ViewModel {
    private SearchByCuisineState state = new SearchByCuisineState();

    public SearchByCuisineViewModel() {
        super("search recipe by cuisine view");
    }

    public void setState(SearchByCuisineState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the SearchByCuisine Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchByCuisineState getState() {
        return state;
    }
}
