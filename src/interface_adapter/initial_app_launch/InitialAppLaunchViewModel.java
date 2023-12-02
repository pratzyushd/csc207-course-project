package interface_adapter.initial_app_launch;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InitialAppLaunchViewModel extends ViewModel {

    public InitialAppLaunchViewModel() {
        super("Initial App Launch View");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /* NOTE: we don't actually use this method, but it is a required part of the ViewModel parent class. */
    @Override
    public void firePropertyChanged() {}

    /**
     * Add an observer to this ViewModel.
     * @param listener the observer to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

