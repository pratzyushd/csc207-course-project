package use_case.search_by_name;

import data_access.InMemoryRecipeAPIMock;
import entity.Recipe;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchNameInteractorTest {
    @Test
    public void testSuccess() {
        SearchNameUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();

        SearchNameOutputBoundary presenter = new SearchNameOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchNameOutputData searchNameOutputData) {
                Recipe[] output = searchNameOutputData.getRecipes();
                assertEquals(3, output.length);
                String[] recipeNames = new String[3];
                int[] recipeIds = new int[3];
                for (int i = 0; i < output.length; i++) {
                    recipeNames[i] = output[i].getName();
                    recipeIds[i] = output[i].getMealId();
                }
                assertArrayEquals(new String[]{"recipe1", "recipe2", "recipe3"}, recipeNames);
                assertArrayEquals(new int[]{32495, 18503, 68204}, recipeIds);
            }

            @Override
            public void prepareFailView(SearchNameOutputData searchNameOutputData) {
                fail();
            }
        };

        SearchNameInputData input = new SearchNameInputData("name");
        SearchNameInputBoundary interactor = new SearchNameInteractor(mockAPI, presenter);
        interactor.execute(input);
    }

    @Test
    public void testFailure() {
        /* Create mock API that returns an empty array to trigger the failure case */
        SearchNameUserDataAccessInterface mockAPI = new SearchNameUserDataAccessInterface() {
            @Override
            public Recipe[] searchRecipesByName(String name) {
                return new Recipe[0];
            }
        };

        SearchNameOutputBoundary presenter = new SearchNameOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchNameOutputData searchNameOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(SearchNameOutputData searchNameOutputData) {
                assertEquals("name", searchNameOutputData.getName());
                assertEquals(0, searchNameOutputData.getRecipes().length);
            }
        };

        SearchNameInputData input = new SearchNameInputData("name");
        SearchNameInputBoundary interactor = new SearchNameInteractor(mockAPI, presenter);
        interactor.execute(input);
    }
}
