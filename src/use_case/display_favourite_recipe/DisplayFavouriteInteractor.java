package use_case.display_favourite_recipe;

import entity.Recipe;
import java.util.List;

/**
 * Implements the display user favourite recipes use case.
 */
public class DisplayFavouriteInteractor implements DisplayFavouriteInputBoundary {
    private final DisplayFavouriteUserDataAccessInterface dataAccess;
    private final DisplayFavouriteOutputBoundary presenter;

    /**
     * Creates a new DisplayFavouriteInteractor object with the given data access, presenter and user.
     * @param dataAccess the data access object that retrieves the relevant recipes based on user.
     * @param presenter the presenter that will update the view with the relevant recipes.
     */
    public DisplayFavouriteInteractor(DisplayFavouriteUserDataAccessInterface dataAccess, DisplayFavouriteOutputBoundary presenter, String username) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(DisplayFavouriteInputData input) {
        List<Recipe> recipes = dataAccess.getFavouriteRecipes(dataAccess.getUser());
        DisplayFavouriteOutputData dataOutput = new DisplayFavouriteOutputData(recipes);
        presenter.prepareSuccessView(dataOutput);
    }

}