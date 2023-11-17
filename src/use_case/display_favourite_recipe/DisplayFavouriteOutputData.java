package use_case.display_favourite_recipe;

import entity.Recipe;
import java.util.List;

public class DisplayFavouriteOutputData {
    private final List<Recipe> recipes;

    public DisplayFavouriteOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }


}