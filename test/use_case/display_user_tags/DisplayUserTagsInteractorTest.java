package use_case.display_user_tags;

import data_access.InMemoryDAOMock;
import org.junit.Test;
import use_case.display_user_tags.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DisplayUserTagsInteractorTest {
    @Test
    public void testUseCase() {
        DisplayUserTagsDataAccessInterface mockDAO = new InMemoryDAOMock();
        DisplayUserTagsOutputBoundary presenter = new DisplayUserTagsOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayUserTagsOutputData outputData) {
                List<String> output = outputData.getUserTags();
                assertEquals(2, output.size());
                assertTrue(output.contains("tag1"));
                assertTrue(output.contains("tag2"));
            }
        };

        DisplayUserTagsInputData inputData = new DisplayUserTagsInputData();
        DisplayUserTagsInputBoundary interactor = new DisplayUserTagsInteractor(presenter, mockDAO);
        interactor.execute(inputData);
    }
}
