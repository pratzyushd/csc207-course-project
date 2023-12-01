package interface_adapter.initial_app_launch;

import interface_adapter.ViewModel;
import interface_adapter.initial_app_launch.InitialAppLaunchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InitialAppLaunchViewModel extends ViewModel {
    private InitialAppLaunchState state = new InitialAppLaunchState();

    public InitialAppLaunchViewModel() {
        super("Initial App Launch View");
    }

    public void setState(InitialAppLaunchState state) {
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

    public InitialAppLaunchState getState() {
        return state;
    }
}

