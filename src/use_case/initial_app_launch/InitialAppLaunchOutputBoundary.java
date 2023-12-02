package use_case.initial_app_launch;

public interface InitialAppLaunchOutputBoundary {
    /**
     * Modify the view to reflect a successful interactor execution.
     */
    void prepareSuccessView();

    /**
     * Modify the view to reflect a unsuccessful interactor execution.
     */
    void prepareFailView();
}