package use_case.display_tagged_recipe;

import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implements the display user tagged recipes use case.
 */
public class DisplayTaggedInteractor {

    private final DisplayTaggedOutputBoundary presenter;
    private final DisplayTaggedUserDataAccessInterface dataAccess;

    /**
     * Creates a new DisplayTaggedInteractor with the given presenter and data access.
     * @param presenter the presenter that will update the view with the relevant recipes.
     * @param dataAccess the data access object that will retrieve the relevant recipes based on given user and tag.
     */
    public DisplayTaggedInteractor(DisplayTaggedOutputBoundary presenter, DisplayTaggedUserDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(DisplayTaggedInputData input) {
        List<Recipe> recipes = dataAccess.getTaggedRecipes(dataAccess.getUser(), input.getTag());
        List<HashMap> recipesAsMaps = convertFromRecipeListToHashMapList(recipes);
        DisplayTaggedOutputData dataOutput = new DisplayTaggedOutputData(recipesAsMaps);

        presenter.prepareSuccessView(dataOutput);
    }

    /**
     * Converts the given list of recipes to a list of hash maps.
     * @param recipes the recipes to convert.
     * @return the given list of recipes as a list of hash maps.
     */
    private List<HashMap> convertFromRecipeListToHashMapList(List<Recipe> recipes) {
        List<HashMap> recipesAsMaps = new ArrayList<>(recipes.size());
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