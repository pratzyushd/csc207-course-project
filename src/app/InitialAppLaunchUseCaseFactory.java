package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial_app_launch.InitialAppLaunchController;
import interface_adapter.initial_app_launch.InitialAppLaunchPresenter;
import interface_adapter.initial_app_launch.InitialAppLaunchViewModel;
import use_case.initial_app_launch.InitialAppLaunchInputBoundary;
import use_case.initial_app_launch.InitialAppLaunchInteractor;
import use_case.initial_app_launch.InitialAppLaunchOutputBoundary;
import use_case.initial_app_launch.InitialAppLaunchUserDataAccessInterface;
import view.InitialAppLaunchView;

import javax.swing.*;
import java.io.IOException;

/**
 * A factory class for creating the InitialAppLaunchUseCase.
 */
public class InitialAppLaunchUseCaseFactory {

    private InitialAppLaunchUseCaseFactory() {}

    /**
     * Create the view for the Initial App Launch use case.
     * @param viewManagerModel the shared view manger between all the views.
     * @param initialAppLaunchViewModel The specific view model for this use case.
     * @param initialAppLaunchUserDataAccessObject The DAO that implements the functionality for this use case.
     * @return The fully constructed View.
     */
    public static InitialAppLaunchView create(
            ViewManagerModel viewManagerModel, InitialAppLaunchViewModel initialAppLaunchViewModel,
            InitialAppLaunchUserDataAccessInterface initialAppLaunchUserDataAccessObject) {

        try {
            InitialAppLaunchController initialAppLaunchController = createInitialAppLaunchUseCase(viewManagerModel, initialAppLaunchViewModel,
                    initialAppLaunchUserDataAccessObject);
            return new InitialAppLaunchView(initialAppLaunchController, initialAppLaunchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static InitialAppLaunchController createInitialAppLaunchUseCase(ViewManagerModel viewManagerModel, InitialAppLaunchViewModel
            initialAppLaunchViewModel, InitialAppLaunchUserDataAccessInterface initialAppLaunchUserDataAccessObject) throws IOException {

        InitialAppLaunchOutputBoundary initialAppLaunchOutputBoundary = new InitialAppLaunchPresenter(viewManagerModel, initialAppLaunchViewModel);

        InitialAppLaunchInputBoundary initialAppLaunchInteractor = new InitialAppLaunchInteractor(
                initialAppLaunchUserDataAccessObject, initialAppLaunchOutputBoundary);

        return new InitialAppLaunchController(initialAppLaunchInteractor);
    }
}

