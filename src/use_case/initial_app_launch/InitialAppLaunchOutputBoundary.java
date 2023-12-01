package use_case.initial_app_launch;

public interface InitialAppLaunchOutputBoundary {
    //todo edit javadoc
    /**
     * Prepares the success view for the user.
     */
    void prepareSuccessView();

    void prepareFailView();
}