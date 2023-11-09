package data_access;

import data_access.TheMealDB;
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
        /* TODO: add assertion here */
    }

}
