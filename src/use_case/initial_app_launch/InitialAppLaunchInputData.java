package use_case.initial_app_launch;

public class InitialAppLaunchInputData {
    private final Boolean load;
    private final String filePath;
    private final String username;
//todo change javadoc
    /**
     * Creates an input data object with the given cuisine name.
     * @param load area the recipe is originated from.
     * @param filePath the.
     * @param username the.
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