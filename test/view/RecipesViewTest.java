package view;

import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.MouseEvent;

import static org.junit.Assert.assertEquals;

public class RecipesViewTest {
    private RecipesView recipesView;
    @Before
    public void init() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        CommonUserFactory userFactory = new CommonUserFactory();
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);
        Persistence persistence = new JSONPersistence(userFactory, "", recipeAPI);
        RecipesView recipesView = new RecipesView(viewManagerModel);
        this.recipesView = recipesView;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(recipesView);
        frame.pack();
        frame.setVisible(true);
    }
    @Test
    public void testButtonsPresent() {
        JButton favouriteButton = (JButton) recipesView.getComponent(1);
        assertEquals("Favourite Recipes", favouriteButton.getText());

        JButton taggedButton = (JButton) recipesView.getComponent(2);
        assertEquals("Tagged Recipes", taggedButton.getText());

        JButton backToHomeButton = (JButton) recipesView.getComponent(3);
        assertEquals("Back to Home", backToHomeButton.getText());
    }
    @Test
    public void testLabelsPresent() {
        JLabel selectOptionLabel = (JLabel) recipesView.getComponent(0);
        assertEquals("Select an option: ", selectOptionLabel.getText());
    }
    @Test
    public void testClickingFavouriteButton() {
        JButton favouriteButton = (JButton) recipesView.getComponent(1);
        favouriteButton.dispatchEvent(new MouseEvent(favouriteButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
    @Test
    public void testClickingTaggedButton() {
        JButton taggedButton = (JButton) recipesView.getComponent(2);
        taggedButton.dispatchEvent(new MouseEvent(taggedButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
    @Test
    public void testClickingBackToHomeButton() {
        JButton backToHomeButton = (JButton) recipesView.getComponent(3);
        backToHomeButton.dispatchEvent(new MouseEvent(backToHomeButton, MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, 1, false));
    }
}