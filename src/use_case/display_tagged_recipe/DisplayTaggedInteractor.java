package use_case.display_tagged_recipe;

public class DisplayTaggedInteractor {

    private final DisplayTaggedInputBoundary inputBoundary;
    private final DisplayTaggedOutputBoundary outputBoundary;
    private final DisplayTaggedUserDataAccessInterface dataAccess;

    public DisplayTaggedInteractor(DisplayTaggedInputBoundary inputBoundary, DisplayTaggedOutputBoundary outputBoundary, DisplayTaggedUserDataAccessInterface dataAccess) {
        this.inputBoundary = inputBoundary;
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    public void execute(DisplayTaggedInputData input) {
        outputBoundary.present(dataAccess.displayTaggedRecipes(input.getUsername(), input.getTag()));
    }
}