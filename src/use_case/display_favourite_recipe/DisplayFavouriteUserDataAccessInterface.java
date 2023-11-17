package use_case.display_favourite_recipe;

import entity.Recipe;
import entity.User;

import java.util.List;

public interface DisplayFavouriteUserDataAccessInterface {

    List<Recipe> displayFavouriteRecipes(User user);

}
