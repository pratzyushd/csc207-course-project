package use_case.display_user_tags;

public class DisplayUserTagsInteractor implements DisplayUserTagsInputBoundary {

    private final DisplayUserTagsOutputBoundary presenter;
    private final DisplayUserTagsDataAccessInterface dataAccess;

    /**
     * Creates a new DisplayUserTagsInteractor with the given presenter and data access.
     * @param presenter the presenter that will update the view with the relevant tags.
     * @param dataAccess the DAO that will retrieve the tags.
     */
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
