package data_access;

import entity.Recipe;

public interface RecipeAPI {
    Recipe getRecipeById(String id);
    Recipe[] searchRecipesByCuisine(String cuisine);
    Recipe[] searchRecipesByName(String name);
}
