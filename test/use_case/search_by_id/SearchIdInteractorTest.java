package use_case.search_by_id;

import data_access.InMemoryRecipeAPIMock;
import entity.Recipe;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class SearchIdInteractorTest {
    @Test
    public void testSuccess() {
        SearchIdUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();

        SearchIdOutputBoundary presenter = new SearchIdOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchIdOutputData searchIdOutputData) {
                assertEquals(32495, searchIdOutputData.getId());
                Map<String, String> output = searchIdOutputData.getRecipe();
                assertEquals("testrecipe", output.get("name"));
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };

        SearchIdInputData input = new SearchIdInputData("32495");
        SearchIdInputBoundary interactor = new SearchIdInteractor(mockAPI, presenter);
        interactor.execute(input);
    }

    @Test
    public void testFailure() {
        /* Mock API that returns null to trigger failure case */
        SearchIdUserDataAccessInterface mockAPI = new SearchIdUserDataAccessInterface() {
            @Override
            public Recipe searchRecipesById(int id) {
                return null;
            }
        };

        SearchIdOutputBoundary presenter = new SearchIdOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchIdOutputData searchIdOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("ID"));
            }
        };

        SearchIdInputData input = new SearchIdInputData("0");
        SearchIdInputBoundary interactor = new SearchIdInteractor(mockAPI, presenter);
        interactor.execute(input);
    }
}