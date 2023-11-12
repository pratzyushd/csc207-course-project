package entity;

public interface RecipeFactory {
    Recipe create(int mealId, String name, String category, String instructions, String areaOfOrigin);
}
