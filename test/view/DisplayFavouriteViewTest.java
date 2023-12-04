package view;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.FavouriteRecipesController;
import use_case.display_favourite_recipe.*;

import data_access.InMemoryPersistenceMock;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DisplayFavouriteViewTest {
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final RecipesViewModel recipesViewModel = new RecipesViewModel(0);
    private final DisplayFavouriteUserDataAccessInterface mockDAO = new InMemoryPersistenceMock();
    private final DisplayFavouriteInputData inputData = new DisplayFavouriteInputData();


    @Test
    public void testDisplayFavouriteView() {

        // create presenter
        DisplayFavouriteOutputBoundary presenter = outputData -> {
            List<Map<String, String>> output = outputData.getRecipes();
            assertEquals(2, output.size());
            Map<String, String> recipe1 = output.get(0);
            Map<String, String> recipe2 = output.get(1);
            assertTrue(Objects.equals(recipe1.get("id"), "12589") || Objects.equals(recipe2.get("id"), "12589"));
            assertTrue(Objects.equals(recipe1.get("id"), "57392") || Objects.equals(recipe2.get("id"), "57392"));
        };

        // create interactor
        DisplayFavouriteInputBoundary interactor = new DisplayFavouriteInteractor(mockDAO, presenter);

        // create controller
        FavouriteRecipesController controller = new FavouriteRecipesController(interactor);

        // create a new DisplayFavouriteView object
        DisplayFavouriteView displayFavouriteView = new DisplayFavouriteView(controller, recipesViewModel, viewManagerModel);

        // create a new JFrame object
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(displayFavouriteView);
        frame.pack();
        frame.setVisible(true);

        // execute controller
        controller.execute();

        // set recipes
        List<Recipe> recipes = mockDAO.getFavouriteRecipes(mockDAO.getUser());
        List<Map<String,String>> recipesAsMaps = convertFromRecipeListToHashMapList(recipes);
        recipesViewModel.setRecipes(recipesAsMaps);

        // test recipeList values
        List<String> recipeList = recipesViewModel.getRecipes();
        assertEquals(2, recipeList.size());
        System.out.println(recipeList);

        // update recipeList
        JList<String> favouritesList = (JList<String>) displayFavouriteView.getComponent(0);
        favouritesList.setListData(recipeList.toArray(new String[0]));

        // test favouritesList values
        assertEquals(2, favouritesList.getModel().getSize());
        assertEquals("burger", favouritesList.getModel().getElementAt(0));
        assertEquals("pizza", favouritesList.getModel().getElementAt(1));

        // test clicking on a recipe
        favouritesList.setSelectedIndex(0);
        favouritesList.dispatchEvent(new MouseEvent(favouritesList, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 2, false));
        assertEquals("burger", recipesViewModel.getRecipeName());

        // test backToOptionsButton
        JButton backToOptionsButton = (JButton) displayFavouriteView.getComponent(1);
        assertEquals("Back to Options", backToOptionsButton.getText());
        backToOptionsButton.dispatchEvent(new MouseEvent(backToOptionsButton, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 1, false));
        assertEquals("user recipes", viewManagerModel.getActiveView());
    }

    private List<Map<String, String>> convertFromRecipeListToHashMapList(List<Recipe> recipes) {
        List<Map<String, String>> recipesAsMaps = new java.util.ArrayList<>(recipes.size());
        for (Recipe recipe: recipes) {
            Map<String, String> recipeAsMap = recipe.toMap();
            recipesAsMaps.add(recipeAsMap);
        }
        return recipesAsMaps;
    }
}




