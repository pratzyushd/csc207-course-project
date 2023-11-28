package use_case.display_favourite_recipe;

import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements the display user favourite recipes use case.
 */
public class DisplayFavouriteInteractor implements DisplayFavouriteInputBoundary {
    private final DisplayFavouriteUserDataAccessInterface dataAccess;
    private final DisplayFavouriteOutputBoundary presenter;

    /**
     * Creates a new DisplayFavouriteInteractor object with the given data access, presenter and user.
     * @param dataAccess the data access object that retrieves the relevant recipes based on user.
     * @param presenter the presenter that will update the view with the relevant recipes.
     */
    public DisplayFavouriteInteractor(DisplayFavouriteUserDataAccessInterface dataAccess, DisplayFavouriteOutputBoundary presenter, String username) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(DisplayFavouriteInputData input) {
        List<Recipe> recipes = dataAccess.getFavouriteRecipes(dataAccess.getUser());
        List<Map<String,String>> recipesAsMaps = convertFromRecipeListToHashMapList(recipes);
        DisplayFavouriteOutputData dataOutput = new DisplayFavouriteOutputData(recipesAsMaps);
        presenter.prepareSuccessView(dataOutput);
    }

    /**
     * Converts the given list of recipes to a list of hash maps.
     * @param recipes the recipes to convert.
     * @return the given list of recipes as a list of hash maps.
     */
    private List<Map<String,String>> convertFromRecipeListToHashMapList(List<Recipe> recipes) {
        List<Map<String,String>> recipesAsMaps = new ArrayList<>(recipes.size());
        for (Recipe recipe: recipes) {
            HashMap <String, String> recipeAsMap = new HashMap<>();
            recipeAsMap.put("id", String.valueOf(recipe.getMealId()));
            recipeAsMap.put("name", recipe.getName());
            recipeAsMap.put("category", recipe.getCategory());
            recipeAsMap.put("areaOfOrigin", recipe.getAreaOfOrigin());
            recipeAsMap.put("instructions", recipe.getInstructions());
            recipesAsMaps.add(recipeAsMap);
        }
        return recipesAsMaps;
    }
}