package use_case.display_tagged_recipe;

import java.util.List;
import entity.Recipe;

public interface DisplayTaggedUserDataAccessInterface {
    /**
     * Gets the tagged recipes of the given user.
     * @param username the user whose tagged recipes to get.
     * @param tag the tag to search for.
     * @return the tagged recipes of the given user.
     */
    List<Recipe> getTaggedRecipes(String username, String tag);

}