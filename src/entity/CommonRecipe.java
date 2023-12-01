package entity;

import java.util.HashMap;
import java.util.Map;

class CommonRecipe implements Recipe {

    private final int mealId;
    private final String name;
    private final String category;
    private final String instructions;
    private final String areaOfOrigin;

    /**
     * Create a new Recipe object.
     * @param mealId the ID that uniquely identifies this recipe (sourced from the API).
     * @param name the name of this recipe.
     * @param category the category this recipe falls under (e.g. vegetarian).
     * @param instructions the instructions to make the recipe.
     * @param areaOfOrigin the area of origin / cuisine of the recipe (e.g. Canadian).
     */
    CommonRecipe(int mealId, String name, String category, String instructions, String areaOfOrigin) {
        this.mealId = mealId;
        this.name = name;
        this.category = category;
        this.instructions = instructions;
        this.areaOfOrigin = areaOfOrigin;
    }

    @Override
    public int getMealId() {
        return this.mealId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getInstructions() {
        return instructions;
    }

    @Override
    public String getAreaOfOrigin() {
        return areaOfOrigin;
    }

    @Override
    public Map<String, String> toMap() {
        HashMap<String, String> recipeAsMap = new HashMap<>();
        recipeAsMap.put("id", String.valueOf(this.getMealId()));
        recipeAsMap.put("name", this.getName());
        recipeAsMap.put("category", this.getCategory());
        recipeAsMap.put("areaOfOrigin", this.getAreaOfOrigin());
        recipeAsMap.put("instructions", this.getInstructions());
        return recipeAsMap;
    }
}
