package interface_adapter.initial_app_launch;

import use_case.initial_app_launch.InitialAppLaunchInputBoundary;
import use_case.initial_app_launch.InitialAppLaunchInputData;

public class InitialAppLaunchController {

    final InitialAppLaunchInputBoundary initialAppLaunchInteractor;
    public InitialAppLaunchController(InitialAppLaunchInputBoundary initialAppLaunchInteractor) {
        this.initialAppLaunchInteractor = initialAppLaunchInteractor;
    }


    public void execute(Boolean load, String filePath, String username) {
        InitialAppLaunchInputData initialAppLaunchInputData = new InitialAppLaunchInputData(load, filePath, username);

        initialAppLaunchInteractor.execute(initialAppLaunchInputData);
    }
}
