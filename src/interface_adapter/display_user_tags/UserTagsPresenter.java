package interface_adapter.display_user_tags;

import interface_adapter.ViewManagerModel;
import use_case.display_user_tags.DisplayUserTagsOutputBoundary;
import use_case.display_user_tags.DisplayUserTagsOutputData;

public class UserTagsPresenter implements DisplayUserTagsOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for the UserTagsPresenter class.
     *
     * @param viewManagerModel The view manager model to be used for displaying the user tags.
     */
    public UserTagsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the user.
     *
     * @param outputData the tags to display.
     */
    @Override
    public void prepareSuccessView(DisplayUserTagsOutputData outputData) {

    }
}
