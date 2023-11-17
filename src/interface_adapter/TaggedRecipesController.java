package interface_adapter;

import entity.User;
import use_case.display_tagged_recipe.DisplayTaggedInputBoundary;
import use_case.display_tagged_recipe.DisplayTaggedInputData;

public class TaggedRecipesController {

    final DisplayTaggedInputBoundary displayTaggedInteractor;
    public TaggedRecipesController(DisplayTaggedInputBoundary displayTaggedInteractor) {
        this.displayTaggedInteractor = displayTaggedInteractor;
    }

    public void execute(User user, String tag) {
        DisplayTaggedInputData displayTaggedInputData = new DisplayTaggedInputData(user, tag);
        displayTaggedInteractor.execute(displayTaggedInputData);
    }


}
