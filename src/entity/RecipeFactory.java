package entity;

public interface RecipeFactory {
    /**
     * Create a new Recipe object.
     * @param mealId the ID that uniquely identifies this recipe (sourced from the API).
     * @param name the name of this recipe.
     * @param category the category this recipe falls under (e.g. vegetarian).
     * @param instructions the instructions to make the recipe.
     * @param areaOfOrigin the area of origin / cuisine of the recipe (e.g. Canadian).
     */
    Recipe create(int mealId, String name, String category, String instructions, String areaOfOrigin);
}
