package use_case.initial_app_launch;

public class InitialAppLaunchInputData {
    private final Boolean load;
    private final String filePath;
    private final String username;

    /**
     * Create an input data object with the given information.
     * @param load whether the user opted to load a file or not.
     * @param filePath the path to the file that they want to use (save to, and optionally load from depending on
     *                 the "load" parameter.
     * @param username the username the user chose.
     */
    public InitialAppLaunchInputData(Boolean load, String filePath, String username) {

        this.load = load;
        this.filePath = filePath;
        this.username = username;
    }
    Boolean getLoad() {
        return load;
    }
    String getFilePath() { return filePath; }
    String getUsername() { return username; }

}