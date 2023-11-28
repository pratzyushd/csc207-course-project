package use_case.favourite_recipe;

import data_access.InMemoryPersistenceMock;
import data_access.InMemoryRecipeAPIMock;
import data_access.Persistence;
import entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class FavouriteRecipeInteractorTest {
    @Test
    public void testSuccess() {
        FavouriteRecipeUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();
        Persistence mockDAO = new InMemoryPersistenceMock();

        FavouriteRecipeOutputBoundary presenter = new FavouriteRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(String recipeName) {
                assertEquals(mockAPI.searchRecipesById(0).getName(), recipeName);
            }

            @Override
            public void prepareFailView(String recipeName) {
                /* We should never reach the failure case in this test */
                fail();
            }
        };

        FavouriteRecipeInputData input = new FavouriteRecipeInputData(0);
        FavouriteRecipeInputBoundary interactor = new FavouriteRecipeInteractor(mockAPI, presenter, mockDAO);
        interactor.execute(input);
    }

    @Test
    public void testFailure() {
        FavouriteRecipeUserDataAccessInterface mockAPI = new InMemoryRecipeAPIMock();
        Persistence mockDAO = new InMemoryPersistenceMock();

        /* Modify the user entity in the mock DAO so that we already have the Recipe in it */
        User user = mockDAO.getUser();
        user.addFavourite(mockAPI.searchRecipesById(0));
        mockDAO.setUser(user);

        FavouriteRecipeOutputBoundary presenter = new FavouriteRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(String recipeName) {
                /* We should never have a success view in this test */
                fail();
            }

            @Override
            public void prepareFailView(String recipeName) {
                assertEquals(mockAPI.searchRecipesById(0).getName(), recipeName);
            }
        };

        FavouriteRecipeInputData input = new FavouriteRecipeInputData(0);
        FavouriteRecipeInputBoundary interactor = new FavouriteRecipeInteractor(mockAPI, presenter, mockDAO);
        interactor.execute(input);
    }
}
