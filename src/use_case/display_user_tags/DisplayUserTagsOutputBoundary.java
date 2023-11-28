package use_case.display_user_tags;

public interface DisplayUserTagsOutputBoundary {
    /**
     * Prepares the success view for the user.
     * @param outputData an object containing the tags to display.
     */
    void prepareSuccessView(DisplayUserTagsOutputData outputData);
}
