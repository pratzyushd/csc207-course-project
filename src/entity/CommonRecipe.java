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
     * @param mealId
     * @param name
     * @param category
     * @param instructions
     * @param areaOfOrigin
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
