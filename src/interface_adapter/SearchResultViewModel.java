package interface_adapter;

import interface_adapter.search_by_id.SearchByIdState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchResultViewModel extends ViewModel {

    private SearchResultState state = new SearchResultState();

    public SearchResultViewModel() {
        super("recipe search result(s)");
    }

    public void setState(SearchResultState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the SearchById, SearchByCuisine, or SearchByName Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchResultState getState() {
        return state;
    }
}
