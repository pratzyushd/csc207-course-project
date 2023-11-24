package use_case.display_user_tags;

public interface DisplayUserTagsOutputBoundary {
    /**
     * Prepares the success view for the user.
     * @param outputData the tags to display.
     */
    void prepareSuccessView(DisplayUserTagsOutputData outputData);
}
