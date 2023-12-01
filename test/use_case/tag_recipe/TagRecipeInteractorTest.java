package use_case.tag_recipe;

import data_access.InMemoryPersistenceMock;
import data_access.InMemoryRecipeAPIMock;
import data_access.Persistence;
import entity.CommonRecipeFactory;
import entity.Recipe;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TagRecipeInteractorTest {
    @Test
    public void testAddToExistingTag() {
        TagRecipeUserDataAccessInterface mockAPI = new TagRecipeUserDataAccessInterface() {
            @Override
            public Recipe searchRecipesById(int id) {
                return new CommonRecipeFactory().create(id, "", "", "", "");
            }
        };
        Persistence mockDAO = new InMemoryPersistenceMock();

        TagRecipeOutputBoundary presenter = new TagRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(String recipeName, String tagName) {
            }

            @Override
            public void prepareFailView(String recipeName, String tagName) {
                fail();
            }
        };

        TagRecipeInputData inputData = new TagRecipeInputData(12345, "tag1");
        TagRecipeInputBoundary interactor = new TagRecipeInteractor(mockAPI, presenter, mockDAO);
        interactor.execute(inputData);
        assertTrue(mockDAO.getTags(mockDAO.getUser()).contains("tag1"));

        List<Recipe> recipeList = mockDAO.getTaggedRecipes(mockDAO.getUser(), "tag1");
        boolean recipeFound = false;
        for (Recipe recipe: recipeList) {
            if (recipe.getMealId() == 12345) {
                recipeFound = true;
            }
        }
        assertTrue(recipeFound);
    }

    @Test
    public void testAddToNewTag() {
        TagRecipeUserDataAccessInterface mockAPI = new TagRecipeUserDataAccessInterface() {
            @Override
            public Recipe searchRecipesById(int id) {
                return new CommonRecipeFactory().create(id, "", "", "", "");
            }
        };
        Persistence mockDAO = new InMemoryPersistenceMock();

        TagRecipeOutputBoundary presenter = new TagRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(String recipeName, String tagName) {
            }

            @Override
            public void prepareFailView(String recipeName, String tagName) {
                fail();
            }
        };

        TagRecipeInputData inputData = new TagRecipeInputData(12345, "new_tag");
        TagRecipeInputBoundary interactor = new TagRecipeInteractor(mockAPI, presenter, mockDAO);
        interactor.execute(inputData);
        assertTrue(mockDAO.getTags(mockDAO.getUser()).contains("new_tag"));

        List<Recipe> recipeList = mockDAO.getTaggedRecipes(mockDAO.getUser(), "new_tag");
        boolean recipeFound = false;
        for (Recipe recipe: recipeList) {
            if (recipe.getMealId() == 12345) {
                recipeFound = true;
            }
        }
        assertTrue(recipeFound);
    }

    @Test
    public void testFail() {
        TagRecipeUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();
        Persistence mockDAO = new InMemoryPersistenceMock();

        TagRecipeOutputBoundary presenter = new TagRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(String recipeName, String tagName) {
                fail();
            }

            @Override
            public void prepareFailView(String recipeName, String tagName) {
            }
        };

        TagRecipeInputData inputData = new TagRecipeInputData(57201, "tag1");
        TagRecipeInputBoundary interactor = new TagRecipeInteractor(mockAPI, presenter, mockDAO);
        interactor.execute(inputData);
    }
}
