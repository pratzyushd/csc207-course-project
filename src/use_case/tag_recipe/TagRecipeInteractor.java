package use_case.tag_recipe;

import data_access.Persistence;
import entity.Recipe;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class TagRecipeInteractor implements TagRecipeInputBoundary {
     final TagRecipeUserDataAccessInterface tagRecipeUserDataAccessObject;
     final Persistence jsonPersistence;
     final TagRecipeOutputBoundary tagRecipePresenter;

    /**
     * Creates an instance of the FavouriteRecipeInteractor with its corresponding DAO and presenter.
     * @param tagRecipeUserDataAccessInterface the DAO used to retrieve the recipe.
     * @param tagRecipePresenter the presenter that modifies the view based on the data retrieved by the DAO
     */
    public TagRecipeInteractor(TagRecipeUserDataAccessInterface tagRecipeUserDataAccessInterface,
                                     TagRecipeOutputBoundary tagRecipePresenter,
                                     Persistence jsonPersistence) {
        this.tagRecipeUserDataAccessObject = tagRecipeUserDataAccessInterface;
        this.jsonPersistence = jsonPersistence;
        this.tagRecipePresenter = tagRecipePresenter;
    }

    /**
     * Executes the use case by interacting with the DAO and triggers the presenter to show a fail or success view.
     * @param tagRecipeInputData the input data object containing the id of the recipe the user wants to tag
     */
    @Override
    public void execute(TagRecipeInputData tagRecipeInputData) {
        User user = jsonPersistence.getUser();
        Recipe recipe = tagRecipeUserDataAccessObject.searchRecipesById(tagRecipeInputData.getMealId());
        String givenTagName = tagRecipeInputData.getTagName();
        /* Make a list of the recipes associated with the tag from the user. */
        ArrayList<Recipe> tagAddedTo = user.getTaggedRecipes().get(givenTagName);
        ArrayList<Integer> recipeIdsWithTag;
        if (tagAddedTo != null) {
            recipeIdsWithTag = new ArrayList<>(tagAddedTo.size());
            for (Recipe item : tagAddedTo) {
                recipeIdsWithTag.add(item.getMealId());
            }
        } else {
            recipeIdsWithTag = new ArrayList<>(0);
        }
        /* If we already have the recipe based on the tag, or the tag is empty */
        if (recipeIdsWithTag.contains(tagRecipeInputData.getMealId()) || givenTagName.isEmpty()) {
            tagRecipePresenter.prepareFailView(recipe.getName(), givenTagName);
        } else {
            user.assignTag(recipe, tagRecipeInputData.getTagName());
            jsonPersistence.setUser(user);
            tagRecipePresenter.prepareSuccessView(recipe.getName(), givenTagName);
        }
    }
}
