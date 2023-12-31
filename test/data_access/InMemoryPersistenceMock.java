package data_access;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPersistenceMock implements Persistence {
    private User user;

    public InMemoryPersistenceMock() {
        this.user = new CommonUserFactory().create("test");
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        this.user.addFavourite(recipeFactory.create(12589, "burger", "", "", ""));
        this.user.addFavourite(recipeFactory.create(57392, "pizza", "", "", ""));
        this.user.assignTag(recipeFactory.create(57201, "vegan burger", "", "", ""), "tag1");
        this.user.assignTag(recipeFactory.create(94782, "vegan pizza", "", "", ""), "tag1");
        this.user.assignTag(recipeFactory.create(12694, "", "", "", ""), "tag2");
    }
    @Override
    public List<Recipe> getFavouriteRecipes(User user) {
        return user.getFavourites();
    }

    @Override
    public List<Recipe> getTaggedRecipes(User user, String tag) {
        return user.getTaggedRecipes().get(tag);
    }

    @Override
    public List<String> getTags(User user) {
        return new ArrayList<>(user.getTaggedRecipes().keySet());
    }

    /* NOTE: we are never actually using a file for the InMemory mocks, so we can leave this blank. */
    @Override
    public void setFilePath(String filePath) {}

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /* NOTE: we are never actually using a file for the InMemory mocks, so we can leave this as is. */
    @Override
    public User load() {
        return this.user;
    }

    /* NOTE: we are never actually using a file for the InMemory mocks, so we can leave this blank. */
    @Override
    public void save(User user) {}
}
