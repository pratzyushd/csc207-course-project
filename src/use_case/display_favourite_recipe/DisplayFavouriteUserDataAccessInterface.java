package use_case.display_favourite_recipe;

import entity.Recipe;
import java.util.List;

public interface DisplayFavouriteUserDataAccessInterface {

    /**
     * Gets the favourite recipes of the given user.
     * @param username the user whose favourite recipes to get.
     * @return the favourite recipes of the given user.
     */
    List<Recipe> getFavouriteRecipes(String username);

}
