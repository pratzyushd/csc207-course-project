package use_case.display_favourite_recipe;

import entity.Recipe;
import java.util.List;
public interface DisplayFavouriteOutputBoundary {
    void present(List<Recipe> recipes);

}