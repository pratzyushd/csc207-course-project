package entity;

import java.util.Map;

public interface Recipe {
    int getMealId();

    String getName();

    String getCategory();

    String getInstructions();

    String getAreaOfOrigin();

    /**
     * Method to convert this current recipe into a Map of String keys to String values.
     * @return Map of string keys to string values, representing the contents of this recipe.
     */
    Map<String, String> toMap();
}
