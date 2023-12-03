package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonRecipeFactoryTest {
    @Test
    public void testConstruction() {
        Recipe recipe1 = new CommonRecipe(12345, "recipe1", "test", "N/A", "testland");
        Recipe recipe2 = new CommonRecipeFactory().create(12345, "recipe1", "test", "N/A", "testland");
        assertEquals(recipe1, recipe2);
    }
}
