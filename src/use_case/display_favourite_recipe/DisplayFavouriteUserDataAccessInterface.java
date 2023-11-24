package use_case.display_favourite_recipe;

import entity.User;
import entity.Recipe;
import java.util.List;

public interface DisplayFavouriteUserDataAccessInterface {

    /**
     * Gets the favourite recipes of the given user.
     * @param user the user whose favourite recipes to get.
     * @return the favourite recipes of the given user.
     */
    List<Recipe> getFavouriteRecipes(User user);

    User getUser();

}
