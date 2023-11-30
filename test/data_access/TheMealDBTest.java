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

    /**
     * Attempt a query with an invalid recipe ID for the API. This should return a null object.
     */
    @Test
    public void testCreateWithInvalidId() {
        int id = 64920;
        Recipe recipe;
        recipe = instance.searchRecipesById(id);
        /* We expect a null returned if the recipe ID is invalid (i.e. doesn't exist). */
        assertNull(recipe);
    }

    @Test
    public void testCreateWithValidId() {
        int id = 52772;
        Recipe recipe;
        recipe = instance.searchRecipesById(id);
        /* NOTE: we aren't testing for the contents, because that depends on the API, and may change. */
        assertNotNull(recipe);
    }

    /**
     * Test query with an invalid area parameter. We expect this to return nothing from the API,
     * and so the class should return an empty array.
     */
    @Test
    public void testCreateWithInvalidArea() {
        String area = "asdf";
        Recipe[] recipes;
        recipes = instance.searchRecipesByCuisine(area);
        assertEquals(0, recipes.length);
    }

    @Test
    public void testCreateWithValidArea() {
        String area = "Canadian";
        Recipe[] recipes;
        recipes = instance.searchRecipesByCuisine(area);
        /* The array has elements */
        assertTrue(recipes.length != 0);
        /* There are no null objects in the array.
         * NOTE: this should be fine, as long as we don't have some connection error / API error. */
        for (Recipe r : recipes) {
            assertNotNull(r);
        }
    }

    /**
     * Test query with an invalid name. We expect this to return nothing from the API,
     * and so the class should return an empty array.
     */
    @Test
    public void testCreateWithInvalidName() {
        String name = "asdf";
        Recipe[] recipes;
        recipes = instance.searchRecipesByName(name);
        assertEquals(0, recipes.length);

    }

    /**
     * Test the case of the API only having a single recipe with a given name.
     */
    @Test
    public void testCreateSingleWithValidName() {
        String name = "Arrabiata";
        Recipe[] recipes;
        recipes = instance.searchRecipesByName(name);
        assertEquals(1, recipes.length);

    }

    /**
     * Test the case of the API having multiple recipes with a given name.
     */
    @Test
    public void testCreateMultipleWithValidName() {
        String name = "beef";
        Recipe[] recipes;
        recipes = instance.searchRecipesByName(name);
        assertTrue(recipes.length > 1);

    }

}
