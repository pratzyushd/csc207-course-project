package use_case.favourite_recipe;
import entity.Recipe;
import entity.User;

public class FavouriteRecipeInputData {
    final private User user;
    final private Recipe recipe;

    public FavouriteRecipeInputData(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    Recipe getRecipe() {
        return recipe;
    }

    User getUser() {
        return user;
    }
}
