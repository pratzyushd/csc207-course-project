package entity;

import java.time.LocalDateTime;

public interface RecipeFactory {
    Recipe create(int mealId, String name, String category, String instructions, String areaOfOrigin);
}
