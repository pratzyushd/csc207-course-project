package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import entity.CommonRecipeFactory;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TheMealDBTest {
    private TheMealDB instance;

    @Before
    public void init() {
        RecipeFactory factory = new CommonRecipeFactory();
        this.instance = new TheMealDB(factory);
    }

    @Test
    public void testCreateWithInvalidId() {
        String id = "64920";
        Recipe recipe;
        recipe = instance.getRecipeById(id);
        /* We expect a null returned if the recipe ID is invalid (i.e. doesn't exist). */
        assertNull(recipe);
    }

    @Test
    public void testCreateWithValidId() {
        String id = "52772";
        Recipe recipe;
        recipe = instance.getRecipeById(id);
        /* NOTE: we aren't testing for the contents, because that depends on the API, and may change. */
        assertNotNull(recipe);
    }

    @Test
    public void testCreateWithInvalidArea() {
        String area = "asdf";
        Recipe[] recipes;
        recipes = instance.searchRecipesByCuisine(area);
        assertNull(recipes);
    }

    @Test
    public void testCreateWithValidArea() {
        String area = "Canadian";
        Recipe[] recipes;
        recipes = instance.searchRecipesByCuisine(area);
        /* We have an array */
        assertNotNull(recipes);
        /* The array has elements */
        assertTrue(recipes.length != 0);
        /* There are no null objects in the array.
        * NOTE: this should be fine, as long as we don't have some connection error / API error. */
        for (Recipe r : recipes) {
            assertNotNull(r);
        }
    }

}
