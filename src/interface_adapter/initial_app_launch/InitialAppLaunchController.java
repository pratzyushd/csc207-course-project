package interface_adapter.initial_app_launch;

import use_case.initial_app_launch.InitialAppLaunchInputBoundary;
import use_case.initial_app_launch.InitialAppLaunchInputData;

public class InitialAppLaunchController {

    final InitialAppLaunchInputBoundary initialAppLaunchInteractor;

    /**
     * Create a new Controller for the initial app launch use case.
     * @param initialAppLaunchInteractor the interactor that this controller should execute.
     */
    public InitialAppLaunchController(InitialAppLaunchInputBoundary initialAppLaunchInteractor) {
        this.initialAppLaunchInteractor = initialAppLaunchInteractor;
    }


    /**
     * Execute the interactor with the given parameters.
     * @param load whether the user chose to load a file.
     * @param filePath the file path they chose to save to (and optionally load from).
     * @param username the username that the user chose.
     */
    public void execute(Boolean load, String filePath, String username) {
        InitialAppLaunchInputData initialAppLaunchInputData = new InitialAppLaunchInputData(load, filePath, username);

        initialAppLaunchInteractor.execute(initialAppLaunchInputData);
    }
}
