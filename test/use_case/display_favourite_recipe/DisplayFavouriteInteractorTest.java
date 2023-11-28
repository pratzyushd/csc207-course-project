package use_case.display_favourite_recipe;

import data_access.InMemoryPersistenceMock;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DisplayFavouriteInteractorTest {
    @Test
    public void testUseCase() {
        DisplayFavouriteUserDataAccessInterface mockDAO = new InMemoryPersistenceMock();
        DisplayFavouriteOutputBoundary presenter = new DisplayFavouriteOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayFavouriteOutputData outputData) {
                List<Map<String, String>> output = outputData.getRecipes();
                assertEquals(2, output.size());
                Map<String, String> recipe1 = output.get(0);
                Map<String, String> recipe2 = output.get(1);
                assertTrue(Objects.equals(recipe1.get("id"), "12589") || Objects.equals(recipe2.get("id"), "12589"));
                assertTrue(Objects.equals(recipe1.get("id"), "57392") || Objects.equals(recipe2.get("id"), "57392"));
            }
        };

        DisplayFavouriteInputData inputData = new DisplayFavouriteInputData();
        DisplayFavouriteInputBoundary interactor = new DisplayFavouriteInteractor(mockDAO, presenter, "test");
        interactor.execute(inputData);
    }
}
