package interface_adapter.display_user_tags;

import interface_adapter.ViewManagerModel;
import use_case.display_user_tags.DisplayUserTagsOutputBoundary;
import use_case.display_user_tags.DisplayUserTagsOutputData;

public class UserTagsPresenter implements DisplayUserTagsOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final UserTagsViewModel userTagsViewModel;

    /**
     * Constructor for the UserTagsPresenter class.
     *
     * @param viewManagerModel The view manager model to be used for displaying the user tags.
     * @param userTagsViewModel The view model for the user tags.
     */
    public UserTagsPresenter(UserTagsViewModel userTagsViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.userTagsViewModel = userTagsViewModel;
    }

    /**
     * Prepares the success view for the user.
     *
     * @param outputData the tags to display.
     */
    @Override
    public void prepareSuccessView(DisplayUserTagsOutputData outputData) {
        userTagsViewModel.setTags(outputData.getUserTags());
        viewManagerModel.setActiveView(userTagsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
