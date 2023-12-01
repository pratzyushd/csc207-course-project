package interface_adapter.initial_app_launch;

import use_case.initial_app_launch.InitialAppLaunchInputBoundary;
import use_case.initial_app_launch.InitialAppLaunchInputData;

public class InitialAppLaunchController {

    final InitialAppLaunchInputBoundary initialAppLaunchInteractor;
    public InitialAppLaunchController(InitialAppLaunchInputBoundary initialAppLaunchInteractor) {
        this.initialAppLaunchInteractor = initialAppLaunchInteractor;
    }


    public void execute(Boolean load, String filePath) {
        InitialAppLaunchInputData initialAppLaunchInputData = new InitialAppLaunchInputData(load, filePath);

        initialAppLaunchInteractor.execute(initialAppLaunchInputData);
    }
}
