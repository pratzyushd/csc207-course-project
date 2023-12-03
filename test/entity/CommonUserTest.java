package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUserTest {

    private User user;

    @Before
    public void init() {
        this.user = new CommonUser("test");
    }
    @Test
    public void testGetters() {
        assertEquals("test", user.getUsername());
        assertEquals(0, user.getFavourites().size());
        assertEquals(0, user.getTaggedRecipes().size());
    }

    @Test
    public void testAddingNewTag() {
        Recipe recipe = new CommonRecipe(12345, "test", "temp", "nothing", "testland");
        user.assignTag(recipe, "tag1");
        assertEquals(1, user.getTaggedRecipes().size());
        assertEquals(1, user.getTaggedRecipes().get("tag1").size());
    }

    @Test
    public void testAddingTExistingTag() {
        Recipe recipe1 = new CommonRecipe(12345, "test", "temp", "nothing", "testland");
        user.assignTag(recipe1, "tag1");
        Recipe recipe2 = new CommonRecipe(67890, "test", "temp", "nothing", "testland");
        user.assignTag(recipe2, "tag1");
        assertEquals(1, user.getTaggedRecipes().size());
        assertEquals(2, user.getTaggedRecipes().get("tag1").size());
    }

    @Test
    public void testAddingFavourites() {
        Recipe recipe = new CommonRecipe(12345, "test", "temp", "nothing", "testland");
        user.addFavourite(recipe);
        assertEquals(1, user.getFavourites().size());
    }
}
