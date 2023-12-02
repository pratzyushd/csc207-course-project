package interface_adapter.display_user_tags;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class UserTagsViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<String> tags;

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
    public void setTags(List<String> tags){
        support.firePropertyChange("tags", null, tags);
        this.tags = tags;

    }
    public String[] getTags() {
        support.firePropertyChange("tags", null, null);
        return tags.toArray(new String[0]);
    }
}
