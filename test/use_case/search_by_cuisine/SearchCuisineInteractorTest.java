package use_case.search_by_cuisine;

import data_access.InMemoryPersistenceMock;
import data_access.InMemoryRecipeAPIMock;
import entity.Recipe;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchCuisineInteractorTest {
    @Test
    public void testSuccess() {
        SearchCuisineUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();

        SearchCuisineOutputBoundary presenter = new SearchCuisineOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchCuisineOutputData outputData) {
                Recipe[] output = outputData.getRecipes();
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
            public void prepareFailView(SearchCuisineOutputData outputData) {
                fail();
            }
        };

        SearchCuisineInputData input = new SearchCuisineInputData("cuisine");
        SearchCuisineInputBoundary interactor = new SearchCuisineInteractor(mockAPI, presenter);
        interactor.execute(input);
    }

    @Test
    public void testFailure() {
        /* We make a mock API that returns an empty array to test for failures */
        SearchCuisineUserDataAccessInterface mockAPI = new SearchCuisineUserDataAccessInterface() {
            @Override
            public Recipe[] searchRecipesByCuisine(String cuisine) {
                return new Recipe[0];
            }
        };

        SearchCuisineOutputBoundary presenter = new SearchCuisineOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchCuisineOutputData outputData) {
                fail();
            }

            @Override
            public void prepareFailView(SearchCuisineOutputData outputData) {
                Recipe[] output = outputData.getRecipes();
                assertEquals(0, output.length);
            }
        };

        SearchCuisineInputData input = new SearchCuisineInputData("cuisine");
        SearchCuisineInputBoundary interactor = new SearchCuisineInteractor(mockAPI, presenter);
        interactor.execute(input);
    }
}
