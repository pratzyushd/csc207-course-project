package use_case.display_favourite_recipe;

import entity.Recipe;
import entity.User;
import java.util.List;

public class DisplayFavouriteInteractor implements DisplayFavouriteInputBoundary {
    private final DisplayFavouriteUserDataAccessInterface dataAccess;
    private final DisplayFavouriteOutputBoundary presenter;
    private final User user;

    public DisplayFavouriteInteractor(DisplayFavouriteUserDataAccessInterface dataAccess, DisplayFavouriteOutputBoundary presenter, User user) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.user = user;
    }

    @Override
    public void execute(DisplayFavouriteInputData input) {
        List<Recipe> recipes = dataAccess.displayFavouriteRecipes(user);
        presenter.present(recipes);
    }

}