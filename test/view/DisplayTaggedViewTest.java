package view;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.TaggedRecipesController;
import use_case.display_tagged_recipe.*;

import data_access.InMemoryPersistenceMock;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DisplayTaggedViewTest {
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final RecipesViewModel recipesViewModel = new RecipesViewModel(1);

    private final UserTagsViewModel userTagsViewModel = new UserTagsViewModel();
    private final DisplayTaggedUserDataAccessInterface mockDAO = new InMemoryPersistenceMock();
    private final DisplayTaggedInputData inputData = new DisplayTaggedInputData("tag1");

    @Test
    public void testDisplayTaggedView() {

        // create presenter
        DisplayTaggedOutputBoundary presenter = outputData -> {
            List<Map<String, String>> output = outputData.getRecipes();
            assertEquals(2, output.size());
            Map<String, String> recipe1 = output.get(0);
            Map<String, String> recipe2 = output.get(1);
            assertTrue(Objects.equals(recipe1.get("id"), "57201") || Objects.equals(recipe2.get("id"), "57201"));
            assertTrue(Objects.equals(recipe1.get("id"), "94782") || Objects.equals(recipe2.get("id"), "94782"));
        };

        // create interactor
        DisplayTaggedInputBoundary interactor = new DisplayTaggedInteractor(presenter, mockDAO);

        // create controller
        TaggedRecipesController controller = new TaggedRecipesController(interactor);

        // create a new DisplayTaggedView object
        DisplayTaggedView displayTaggedView = new DisplayTaggedView(controller, recipesViewModel, userTagsViewModel, viewManagerModel);

        // create a new JFrame object
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(displayTaggedView);
        frame.pack();
        frame.setVisible(true);

        // execute controller
        controller.execute("tag1");

        // set recipes
        List<Recipe> recipes = mockDAO.getTaggedRecipes(mockDAO.getUser(), "tag1");
        List<Map<String,String>> recipesAsMaps = convertFromRecipeListToHashMapList(recipes);
        recipesViewModel.setRecipes(recipesAsMaps);

        // test recipeList values
        List<String> recipeList = recipesViewModel.getRecipes();
        assertEquals(2, recipeList.size());
        System.out.println(recipeList);

        // update recipeList
        JList<String> taggedList = (JList<String>) displayTaggedView.getComponent(0);
        taggedList.setListData(recipeList.toArray(new String[0]));

        // test taggedList values
        assertEquals(2, taggedList.getModel().getSize());
        assertEquals("vegan burger", taggedList.getModel().getElementAt(0));
        assertEquals("vegan pizza", taggedList.getModel().getElementAt(1));

        // test clicking on a recipe
        taggedList.setSelectedIndex(0);
        taggedList.dispatchEvent(new MouseEvent(taggedList, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 2, false));
        assertEquals("vegan burger", recipesViewModel.getRecipeName());

        // test backToOptionsButton
        JButton backToOptionsButton = (JButton) displayTaggedView.getComponent(1);
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
