package view;

import app.*;
import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_cuisine.SearchByCuisineViewModel;
import interface_adapter.search_result.SearchResultViewModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.MouseEvent;

import static org.junit.Assert.assertEquals;

public class SearchByCuisineViewTest {
    private SearchByCuisineView searchByCuisineView;
    @Before
    public void init() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        CommonUserFactory userFactory = new CommonUserFactory();
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);
        Persistence persistence = new JSONPersistence(userFactory, "", recipeAPI);

        SearchByCuisineViewModel searchByCuisineViewModel = new SearchByCuisineViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        SearchByCuisineView searchByCuisineView = SearchByCuisineUseCaseFactory.create(viewManagerModel,
                searchByCuisineViewModel, searchResultViewModel, recipeAPI);
        this.searchByCuisineView = searchByCuisineView;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(searchByCuisineView);
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    public void testButtonsPresent() {
        JButton searchButton = (JButton) searchByCuisineView.getComponent(2);
        assertEquals("Search", searchButton.getText());

        JButton backToHomeButton = (JButton) searchByCuisineView.getComponent(3);
        assertEquals("Back to Home", backToHomeButton.getText());
    }
    @Test
    public void testLabelPresent() {
        JLabel recipeCuisine = (JLabel) searchByCuisineView.getComponent(0);
        assertEquals("Enter the Cuisine to Search For:", recipeCuisine.getText());
    }
    @Test
    public void testTextFieldPresent() {
        JTextField cuisineTextField = (JTextField) searchByCuisineView.getComponent(1);
        assertEquals("", cuisineTextField.getText());
    }
    @Test
    public void testClickingSearchButton() {
        JButton searchButton = (JButton) searchByCuisineView.getComponent(2);
        searchButton.dispatchEvent(new MouseEvent(searchButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
    @Test
    public void testClickingBackToHomeButton() {
        JButton backToHomeButton = (JButton) searchByCuisineView.getComponent(3);
        backToHomeButton.dispatchEvent(new MouseEvent(backToHomeButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
}