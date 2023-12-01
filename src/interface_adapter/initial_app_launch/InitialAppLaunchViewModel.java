package interface_adapter.initial_app_launch;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InitialAppLaunchViewModel extends ViewModel {

    public InitialAppLaunchViewModel() {
        super("Initial App Launch View");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        // todo we don't use this but it is part of the view model abstract class
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

