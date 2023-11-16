package use_case.display_favourite_recipe;

import entity.Recipe;
import entity.User;
import java.util.List;

public class DisplayFavouriteInteractor {
    private DisplayFavouriteOutputBoundary outputBoundary;

    public DisplayFavouriteInteractor(DisplayFavouriteOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


}