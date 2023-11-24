package interface_adapter.display_recipes;

import entity.User;
import use_case.display_tagged_recipe.DisplayTaggedInputBoundary;
import use_case.display_tagged_recipe.DisplayTaggedInputData;

public class TaggedRecipesController {

    final DisplayTaggedInputBoundary displayTaggedInteractor;

    /**
     * Constructs a new TaggedRecipesController with the specified displayTaggedInteractor.
     *
     * @param displayTaggedInteractor The interactor to be used for displaying tagged recipes.
     */
    public TaggedRecipesController(DisplayTaggedInputBoundary displayTaggedInteractor) {
        this.displayTaggedInteractor = displayTaggedInteractor;
    }

    /**
     * Executes the display tagged recipes use case for the given user and tag.
     *
     * @param user The user for whom tagged recipes should be displayed.
     * @param tag The tag for which recipes should be displayed.
     */
    public void execute(User user, String tag) {
        DisplayTaggedInputData displayTaggedInputData = new DisplayTaggedInputData(user.getUsername(), tag);
        displayTaggedInteractor.execute(displayTaggedInputData);
    }


}
