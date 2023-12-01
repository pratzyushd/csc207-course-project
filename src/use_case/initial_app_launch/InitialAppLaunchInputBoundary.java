package use_case.initial_app_launch;

public interface InitialAppLaunchInputBoundary {
    //todo edit javadoc
    /**
     * Executes the use case
     * An empty DisplayFavouriteInputData object is passed as parameter to remain consistent with Clean Architecture and
     * remain extensible in the future even though input data is not needed for this use case.
     */
    void execute(InitialAppLaunchInputData input);
}