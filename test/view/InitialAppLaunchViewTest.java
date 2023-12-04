package view;

import app.InitialAppLaunchUseCaseFactory;
import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial_app_launch.InitialAppLaunchViewModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InitialAppLaunchViewTest {

    private InitialAppLaunchView initialAppLaunchView;
    @Before
    public void init() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        CommonUserFactory userFactory = new CommonUserFactory();
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);
        Persistence persistence = new JSONPersistence(userFactory, "", recipeAPI);

        InitialAppLaunchViewModel initialAppLaunchViewModel = new InitialAppLaunchViewModel();
        InitialAppLaunchView initialAppLaunchView = InitialAppLaunchUseCaseFactory.create(viewManagerModel,
                initialAppLaunchViewModel, persistence);
        this.initialAppLaunchView = initialAppLaunchView;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(initialAppLaunchView);
        frame.pack();
        frame.setVisible(true);
    }
    @Test
    public void testButtonsPresent() {
        JButton loadButton = (JButton) initialAppLaunchView.getComponent(0);
        assertEquals("Load Existing User Data", loadButton.getText());

        JButton newUserButton = (JButton) initialAppLaunchView.getComponent(1);
        assertEquals("Create New User", newUserButton.getText());
    }
    @Test
    public void testLabelsPresent() {
        JLabel usernameLabel = (JLabel) initialAppLaunchView.getComponent(2);
        assertEquals("Choose A Username: ", usernameLabel.getText());
    }
    @Test
    public void testTextFieldsPresent() {
        JTextField usernameTextField = (JTextField) initialAppLaunchView.getComponent(3);
        assertEquals("", usernameTextField.getText());
    }
    @Test
    public void testClickingLoadButton() {
        JButton loadButton = (JButton) initialAppLaunchView.getComponent(0);
        loadButton.dispatchEvent(new MouseEvent(loadButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
    @Test
    public void testClickingNewUserButton() {
        JButton backToHomeButton = (JButton) initialAppLaunchView.getComponent(1);
        backToHomeButton.dispatchEvent(new MouseEvent(backToHomeButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
}