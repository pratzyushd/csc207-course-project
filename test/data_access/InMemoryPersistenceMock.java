package data_access;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPersistenceMock implements Persistence {
    private User user;

    public InMemoryPersistenceMock() {
        this.user = new CommonUserFactory().create("test");
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        this.user.addFavourite(recipeFactory.create(12589, "", "", "", ""));
        this.user.addFavourite(recipeFactory.create(57392, "", "", "", ""));
        this.user.assignTag(recipeFactory.create(57201, "", "", "", ""), "tag1");
        this.user.assignTag(recipeFactory.create(94782, "", "", "", ""), "tag1");
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

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
