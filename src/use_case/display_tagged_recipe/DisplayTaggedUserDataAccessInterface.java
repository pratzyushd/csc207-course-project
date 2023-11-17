package use_case.display_tagged_recipe;

import java.util.List;
import entity.Recipe;
import entity.User;

public interface DisplayTaggedUserDataAccessInterface {

    List<Recipe> displayTaggedRecipes(String username, String tag);

}