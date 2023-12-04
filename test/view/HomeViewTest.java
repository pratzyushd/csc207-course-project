package view;

import interface_adapter.ViewManagerModel;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;

public class HomeViewTest {

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    @Test
    public void testHomeView() {

        // create a new HomeView object
        HomeView homeView = new HomeView(viewManagerModel);

        // create a new JFrame object
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(homeView);
        frame.pack();
        frame.setVisible(true);

        // test searchByIDButton
        JButton searchByIDButton = (JButton) homeView.getComponent(0);
        assertEquals("Search by ID", searchByIDButton.getText());
        searchByIDButton.doClick();
        assertEquals("search recipe by id", viewManagerModel.getActiveView());

        // test searchByCuisineButton
        JButton searchByCuisineButton = (JButton) homeView.getComponent(1);
        assertEquals("Search by Cuisine", searchByCuisineButton.getText());
        searchByCuisineButton.doClick();
        assertEquals("search recipes by cuisine", viewManagerModel.getActiveView());

        // test searchByNameButton
        JButton searchByNameButton = (JButton) homeView.getComponent(2);
        assertEquals("Search by Name", searchByNameButton.getText());
        searchByNameButton.doClick();
        assertEquals("search recipes by name", viewManagerModel.getActiveView());

        // test displayUserRecipesButton
        JButton displayUserRecipesButton = (JButton) homeView.getComponent(3);
        assertEquals("Display User Recipes Collection", displayUserRecipesButton.getText());
        displayUserRecipesButton.doClick();
        assertEquals("user recipes", viewManagerModel.getActiveView());

        // test quitButton
        JButton quitButton = (JButton) homeView.getComponent(4);
        assertEquals("QUIT", quitButton.getText());

    }
}
