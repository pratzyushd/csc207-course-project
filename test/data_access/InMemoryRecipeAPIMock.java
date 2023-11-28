package data_access;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;

public class InMemoryRecipeAPIMock implements RecipeAPI {
    private Recipe[] recipes = new Recipe[3];

    public InMemoryRecipeAPIMock() {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        this.recipes[0] = recipeFactory.create(32495, "recipe1", "", "", "");
        this.recipes[1] = recipeFactory.create(18503, "recipe2", "", "", "");
        this.recipes[2] = recipeFactory.create(68204, "recipe3", "", "", "");
    }
    @Override
    public Recipe[] searchRecipesByListOfIds(String[] ids) {
        return this.recipes;
    }

    @Override
    public Recipe searchRecipesById(int id) {
        return this.recipes[0];
    }

    @Override
    public Recipe[] searchRecipesByCuisine(String cuisine) {
        return this.recipes;
    }

    @Override
    public Recipe[] searchRecipesByName(String name) {
        return this.recipes;
    }
}
