package interface_adapter.display_user_tags;

import use_case.display_user_tags.DisplayUserTagsInputBoundary;
import use_case.display_user_tags.DisplayUserTagsInputData;

public class UserTagsController {

    final DisplayUserTagsInputBoundary displayUserTagsInteractor;

    /**
     * Constructor for the UserTagsController class.
     *
     * @param displayUserTagsInteractor The interactor to be used for displaying user tags.
     */
    public UserTagsController(DisplayUserTagsInputBoundary displayUserTagsInteractor) {
        this.displayUserTagsInteractor = displayUserTagsInteractor;
    }

    /**
     * Executes the display user tags use case.
     *
     */
    public Object[] execute() {
        DisplayUserTagsInputData displayUserTagsInputData = new DisplayUserTagsInputData();
        displayUserTagsInteractor.execute(displayUserTagsInputData);
        return new Object[]{};
    }
}
