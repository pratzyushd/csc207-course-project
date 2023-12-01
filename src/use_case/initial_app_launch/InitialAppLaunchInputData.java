package use_case.initial_app_launch;

public class InitialAppLaunchInputData {
    private final Boolean load;
    private final String filePath;
//todo change javadoc
    /**
     * Creates an input data object with the given cuisine name.
     * @param load area the recipe is originated from.
     * @param filePath the.
     */
    public InitialAppLaunchInputData(Boolean load, String filePath) {

        this.load = load;
        this.filePath = filePath;
    }
    Boolean getLoad() {
        return load;
    }

    String getFilePath() { return filePath; }

}