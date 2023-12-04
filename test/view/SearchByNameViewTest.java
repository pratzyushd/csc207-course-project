package view;

import app.*;
import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_name.SearchByNameViewModel;
import interface_adapter.search_result.SearchResultViewModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.MouseEvent;

import static org.junit.Assert.assertEquals;

public class SearchByNameViewTest {
    private SearchByNameView searchByNameView;
    @Before
    public void init() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        CommonUserFactory userFactory = new CommonUserFactory();
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);
        Persistence persistence = new JSONPersistence(userFactory, "", recipeAPI);

        SearchByNameViewModel searchByNameViewModel = new SearchByNameViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        SearchByNameView searchByNameView = SearchByNameUseCaseFactory.create(viewManagerModel,
                searchByNameViewModel, searchResultViewModel, recipeAPI);
        this.searchByNameView = searchByNameView;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(searchByNameView);
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    public void testButtonsPresent() {
        JButton searchButton = (JButton) searchByNameView.getComponent(2);
        assertEquals("Search", searchButton.getText());

        JButton backToHomeButton = (JButton) searchByNameView.getComponent(3);
        assertEquals("Back to Home", backToHomeButton.getText());
    }
    @Test
    public void testLabelPresent() {
        JLabel recipeName = (JLabel) searchByNameView.getComponent(0);
        assertEquals("Enter the recipe Name:", recipeName.getText());
    }
    @Test
    public void testTextFieldPresent() {
        JTextField nameTextField = (JTextField) searchByNameView.getComponent(1);
        assertEquals("", nameTextField.getText());
    }
    @Test
    public void testClickingSearchButton() {
        JButton searchButton = (JButton) searchByNameView.getComponent(2);
        searchButton.dispatchEvent(new MouseEvent(searchButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
    @Test
    public void testClickingBackToHomeButton() {
        JButton backToHomeButton = (JButton) searchByNameView.getComponent(3);
        backToHomeButton.dispatchEvent(new MouseEvent(backToHomeButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
}