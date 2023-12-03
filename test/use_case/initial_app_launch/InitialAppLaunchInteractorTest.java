package use_case.initial_app_launch;

import data_access.InMemoryPersistenceMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InitialAppLaunchInteractorTest {

    @Test
    public void testCreateNewUser() {
        InitialAppLaunchUserDataAccessInterface mockDAO = new InMemoryPersistenceMock();

        InitialAppLaunchOutputBoundary presenter = new InitialAppLaunchOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertEquals(mockDAO.getUser().getUsername(), "test_user_1");
            }

            @Override
            public void prepareFailView() {
                /* We should never reach the failure case in this test */
                fail();
            }
        };

        /* An empty string is passed in for the file path argument of the input data.
        This should not break the functionality of the code. */
        InitialAppLaunchInputData input = new InitialAppLaunchInputData(false, "", "test_user_1");
        InitialAppLaunchInputBoundary interactor = new InitialAppLaunchInteractor(mockDAO, presenter);
        interactor.execute(input);
    }
}
