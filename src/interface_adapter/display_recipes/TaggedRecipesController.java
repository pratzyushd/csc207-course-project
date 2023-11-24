package interface_adapter.display_recipes;

import entity.User;
import use_case.display_tagged_recipe.DisplayTaggedInputBoundary;
import use_case.display_tagged_recipe.DisplayTaggedInputData;
import use_case.display_tagged_recipe.DisplayTaggedOutputData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
     * @param tag The tag for which recipes should be displayed.
     */
    public void execute(String tag) {
        DisplayTaggedInputData displayTaggedInputData = new DisplayTaggedInputData(tag);
        displayTaggedInteractor.execute(displayTaggedInputData);
    }

}
