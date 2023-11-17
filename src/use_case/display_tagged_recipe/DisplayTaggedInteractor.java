package use_case.display_tagged_recipe;

import entity.Recipe;

import java.util.List;

/**
 * Implements the display user tagged recipes use case.
 */
public class DisplayTaggedInteractor {

    private final DisplayTaggedOutputBoundary presenter;
    private final DisplayTaggedUserDataAccessInterface dataAccess;

    /**
     * Creates a new DisplayTaggedInteractor with the given presenter and data access.
     * @param presenter the presenter that will update the view with the relevant recipes.
     * @param dataAccess the data access object that will retrieve the relevant recipes based on given user and tag.
     */
    public DisplayTaggedInteractor(DisplayTaggedOutputBoundary presenter, DisplayTaggedUserDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(DisplayTaggedInputData input) {

        List<Recipe> recipes = dataAccess.getTaggedRecipes(input.getUser(), input.getTag());
        DisplayTaggedOutputData dataOutput = new DisplayTaggedOutputData(recipes);

        presenter.prepareSuccessView(dataOutput);
    }
}