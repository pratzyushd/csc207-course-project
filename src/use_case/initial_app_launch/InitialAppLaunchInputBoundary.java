package use_case.initial_app_launch;

public interface InitialAppLaunchInputBoundary {
    /**
     * Executes the initial app launch use case.
     * @param input the information required for the initialisation of the application.
     */
    void execute(InitialAppLaunchInputData input);
}