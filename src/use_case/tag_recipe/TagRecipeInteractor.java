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
        HashMap<String, ArrayList<Recipe>> currentUserTags = user.getTaggedRecipes();

        if (currentUserTags.get(givenTagName).contains(recipe)) {
            tagRecipePresenter.prepareFailView(recipe.getName(), givenTagName);
        } else {
            user.assignTag(recipe, tagRecipeInputData.getTagName());
            tagRecipePresenter.prepareSuccessView(recipe.getName(), givenTagName);
        }
    }
}
