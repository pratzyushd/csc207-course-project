package use_case.display_user_tags;

public class DisplayUserTagsInteractor implements DisplayUserTagsInputBoundary{

    private final DisplayUserTagsOutputBoundary presenter;
    private final DisplayUserTagsDataAccessInterface dataAccess;

    public DisplayUserTagsInteractor(DisplayUserTagsOutputBoundary presenter, DisplayUserTagsDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(DisplayUserTagsInputData input) {
        DisplayUserTagsOutputData outputData = new DisplayUserTagsOutputData(dataAccess.getTags(dataAccess.getUser()));
        presenter.prepareSuccessView(outputData);
    }
}
