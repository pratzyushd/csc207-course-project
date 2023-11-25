package interface_adapter.display_user_tags;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class UserTagsViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new UserTagsViewModel.
     */
    public UserTagsViewModel() {
        super("UserTagsView");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.getViewName());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
