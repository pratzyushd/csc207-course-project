package app;

import interface_adapter.display_user_tags.*;
import interface_adapter.ViewManagerModel;
import use_case.display_user_tags.*;
import view.DisplayTagsView;

import javax.swing.*;
import java.io.IOException;

public class DisplayUserTagsUseCaseFactory {

    private DisplayUserTagsUseCaseFactory() {}

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
