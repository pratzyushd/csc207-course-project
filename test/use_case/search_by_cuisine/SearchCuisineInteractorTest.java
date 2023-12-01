package use_case.search_by_cuisine;

import data_access.InMemoryPersistenceMock;
import data_access.InMemoryRecipeAPIMock;
import entity.Recipe;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchCuisineInteractorTest {
    @Test
    public void testSuccess() {
        SearchCuisineUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();

        SearchCuisineOutputBoundary presenter = new SearchCuisineOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchCuisineOutputData outputData) {
                List<Map<String, String>> output = outputData.getRecipes();
                assertEquals(3, output.size());
                String[] recipeNames = new String[3];
                String[] recipeIds = new String[3];
                for (int i = 0; i < output.size(); i++) {
                    recipeNames[i] = output.get(i).get("name");
                    recipeIds[i] = output.get(i).get("id");
                }
                assertArrayEquals(new String[]{"recipe1", "recipe2", "recipe3"}, recipeNames);
                assertArrayEquals(new String[]{"32495", "18503", "68204"}, recipeIds);
            }

            @Override
            public void prepareFailView(String error) {
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
            public void prepareFailView(String error) {
                assertTrue(error.contains("results"));
            }
        };

        SearchCuisineInputData input = new SearchCuisineInputData("cuisine");
        SearchCuisineInputBoundary interactor = new SearchCuisineInteractor(mockAPI, presenter);
        interactor.execute(input);
    }
}
