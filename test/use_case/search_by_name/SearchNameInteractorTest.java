package use_case.search_by_name;

import data_access.InMemoryRecipeAPIMock;
import entity.Recipe;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchNameInteractorTest {
    @Test
    public void testSuccess() {
        SearchNameUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();

        SearchNameOutputBoundary presenter = new SearchNameOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchNameOutputData searchNameOutputData) {
                List<Map<String, String>> output = searchNameOutputData.getRecipes();
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
            public void prepareFailView(String error) {
                assertTrue(error.contains("results"));
            }
        };

        SearchNameInputData input = new SearchNameInputData("name");
        SearchNameInputBoundary interactor = new SearchNameInteractor(mockAPI, presenter);
        interactor.execute(input);
    }
}
