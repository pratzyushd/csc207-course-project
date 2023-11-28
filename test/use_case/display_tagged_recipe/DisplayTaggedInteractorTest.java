package use_case.display_tagged_recipe;

import data_access.InMemoryDAOMock;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DisplayTaggedInteractorTest {
    @Test
    public void testFirstTag() {
        DisplayTaggedUserDataAccessInterface mockDAO = new InMemoryDAOMock();
        DisplayTaggedOutputBoundary presenter = new DisplayTaggedOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayTaggedOutputData outputData) {
                List<Map<String, String>> output = outputData.getRecipes();
                assertEquals(2, output.size());
                Map<String, String> recipe1 = output.get(0);
                Map<String, String> recipe2 = output.get(1);
                assertTrue(Objects.equals(recipe1.get("id"), "57201") || Objects.equals(recipe2.get("id"), "57201"));
                assertTrue(Objects.equals(recipe1.get("id"), "94782") || Objects.equals(recipe2.get("id"), "94782"));
            }
        };

        DisplayTaggedInputData inputData = new DisplayTaggedInputData("tag1");
        /* TODO: change the type of this variable after we merge in PR #57 */
        DisplayTaggedInteractor interactor = new DisplayTaggedInteractor(presenter, mockDAO);
        interactor.execute(inputData);
    }
    @Test
    public void testSecondTag() {
        DisplayTaggedUserDataAccessInterface mockDAO = new InMemoryDAOMock();
        DisplayTaggedOutputBoundary presenter = new DisplayTaggedOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayTaggedOutputData outputData) {
                List<Map<String, String>> output = outputData.getRecipes();
                assertEquals(1, output.size());
                Map<String, String> recipe1 = output.get(0);
                assertEquals("12694", recipe1.get("id"));
            }
        };

        DisplayTaggedInputData inputData = new DisplayTaggedInputData("tag2");
        /* TODO: change the type of this variable after we merge in PR #57 */
        DisplayTaggedInteractor interactor = new DisplayTaggedInteractor(presenter, mockDAO);
        interactor.execute(inputData);
    }
}
