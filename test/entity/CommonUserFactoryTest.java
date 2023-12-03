package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUserFactoryTest {
    @Test
    public void testConstruction() {
        User user1 = new CommonUser("test");
        User user2 = new CommonUserFactory().create("test");
        assertEquals(user1.getUsername(), user2.getUsername());
        assertEquals(user1.getFavourites().size(), user2.getFavourites().size());
        assertEquals(user1.getTaggedRecipes().size(), user2.getTaggedRecipes().size());
    }
}
