package use_case.display_tagged_recipe;

public interface DisplayTaggedOutputBoundary {
    /**
     * Prepares the success view for the user.
     * @param outputData the recipes to display.
     */
    void prepareSuccessView(DisplayTaggedOutputData outputData);
}