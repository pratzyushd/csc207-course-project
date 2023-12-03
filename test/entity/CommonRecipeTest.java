package entity;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class CommonRecipeTest {
    @Test
    public void testEquality() {
        Recipe recipe1 = new CommonRecipe(12345, "recipe1", "test", "N/A", "testland");
        Recipe recipe2 = new CommonRecipe(12345, "recipe2", "test", "N/A", "testland");
        assertEquals(recipe1, recipe2);

        assertEquals(Objects.hash(12345), recipe1.hashCode());
    }

    @Test
    public void testGetters() {
        Recipe recipe = new CommonRecipe(12345, "recipe", "test", "nothing", "testland");

        assertEquals(12345, recipe.getMealId());
        assertEquals("recipe", recipe.getName());
        assertEquals("test", recipe.getCategory());
        assertEquals("nothing", recipe.getInstructions());
        assertEquals("testland", recipe.getAreaOfOrigin());
    }

    @Test
    public void testMapConversion() {
        Map<String, String> expected = Map.ofEntries(
                Map.entry("id", "12345"),
                Map.entry("name", "recipe"),
                Map.entry("category", "test"),
                Map.entry("areaOfOrigin", "testland"),
                Map.entry("instructions", "nothing")
        );
        Recipe recipe = new CommonRecipe(12345, "recipe", "test", "nothing", "testland");
        Map<String, String> actual = recipe.toMap();

        assertEquals(expected.size(), actual.size());
        for (String key: expected.keySet()) {
            assertEquals(expected.get(key), actual.get(key));
        }
    }
}
