package interface_adapter.search_by_id;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchByIdViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Recipe By ID View";

    private SearchByIdState state = new SearchByIdState();

    public SearchByIdViewModel() {
        super("search recipe by id view");
    }

    public void setState(SearchByIdState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the SearchById Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchByIdState getState() {
        return state;
    }
}
