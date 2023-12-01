package interface_adapter.initial_app_launch;

import interface_adapter.ViewManagerModel;
import use_case.initial_app_launch.InitialAppLaunchOutputBoundary;

// public class InitialAppLaunchPresenter implements LoginOutputBoundary {
public class InitialAppLaunchPresenter implements InitialAppLaunchOutputBoundary {
    private final InitialAppLaunchViewModel initialAppLaunchViewModel;
    private ViewManagerModel viewManagerModel;

    public InitialAppLaunchPresenter(ViewManagerModel viewManagerModel,
                          InitialAppLaunchViewModel initialAppLaunchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.initialAppLaunchViewModel = initialAppLaunchViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the home page in view.
        this.viewManagerModel.setActiveView("home view");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
    }
}
