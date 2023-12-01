package app;

import interface_adapter.display_user_tags.*;
import interface_adapter.ViewManagerModel;
import use_case.display_user_tags.*;
import view.DisplayTagsView;

import javax.swing.*;
import java.io.IOException;

/**
 * A factory class for creating the DisplayUserTagsUseCase.
 */
public class DisplayUserTagsUseCaseFactory {

    private DisplayUserTagsUseCaseFactory() {}

    /**
     * Create the final view for the Display User Tags use case.
     * @param viewManagerModel the shared view manger between all the views.
     * @param userTagsViewModel The specific view model for this use case.
     * @param userTagsUserDataAccessObject The DAO that implements the functionality for this use case.
     * @return The fully constructed View.
     */
    public static DisplayTagsView create(
            ViewManagerModel viewManagerModel, UserTagsViewModel userTagsViewModel,
            DisplayUserTagsUserDataAccessInterface userTagsUserDataAccessObject) {

        try {
            UserTagsController userTagsController = createDisplayUserTagsUseCase(viewManagerModel, userTagsViewModel,
                    userTagsUserDataAccessObject);
            return new DisplayTagsView(userTagsController, userTagsViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static UserTagsController createDisplayUserTagsUseCase(ViewManagerModel viewManagerModel, UserTagsViewModel
            userTagsViewModel, DisplayUserTagsUserDataAccessInterface userTagsUserDataAccessObject) throws IOException {

        DisplayUserTagsOutputBoundary displayUserTagsOutputBoundary = new UserTagsPresenter(userTagsViewModel, viewManagerModel);

        DisplayUserTagsInputBoundary displayUserTagsInteractor = new DisplayUserTagsInteractor(
                displayUserTagsOutputBoundary, userTagsUserDataAccessObject);

        return new UserTagsController(displayUserTagsInteractor);
    }
}
