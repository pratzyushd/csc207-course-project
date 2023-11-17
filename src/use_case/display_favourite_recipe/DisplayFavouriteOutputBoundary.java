package use_case.display_favourite_recipe;

import entity.Recipe;
import java.util.List;
public interface DisplayFavouriteOutputBoundary {
    /**
     * Prepares the success view for the user.
     * @param recipes the recipes to display.
     */
    void prepareSuccessView(List<Recipe> recipes);

}