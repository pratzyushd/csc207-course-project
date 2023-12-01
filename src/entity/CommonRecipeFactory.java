package entity;

public class CommonRecipeFactory implements RecipeFactory {
    @Override
    public Recipe create(int mealId, String name, String category, String instructions, String areaOfOrigin) {
        return new CommonRecipe(mealId, name, category, instructions, areaOfOrigin);
    }
}
