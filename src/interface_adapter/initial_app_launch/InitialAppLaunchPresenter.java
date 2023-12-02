package interface_adapter.initial_app_launch;

import interface_adapter.ViewManagerModel;
import use_case.initial_app_launch.InitialAppLaunchOutputBoundary;

// public class InitialAppLaunchPresenter implements LoginOutputBoundary {
public class InitialAppLaunchPresenter implements InitialAppLaunchOutputBoundary {
    private final InitialAppLaunchViewModel initialAppLaunchViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Create a new Presenter for the initial app launch view.
     * @param viewManagerModel manager to facilitate switching to another use case from this one.
     * @param initialAppLaunchViewModel the representation of the information in the view.
     */
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
    public void prepareFailView() {}
}
