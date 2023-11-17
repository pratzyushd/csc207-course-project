package use_case.display_favourite_recipe;

import entity.Recipe;
import entity.User;

import java.util.List;

public interface DisplayFavouriteUserDataAccessInterface {

    /**
     * Gets the favourite recipes of the given user.
     * @param user the user whose favourite recipes to get.
     * @return the favourite recipes of the given user.
     */
    List<Recipe> getFavouriteRecipes(User user);

}
