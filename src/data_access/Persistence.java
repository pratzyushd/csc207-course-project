package data_access;

import use_case.display_favourite_recipe.DisplayFavouriteUserDataAccessInterface;
import use_case.display_tagged_recipe.DisplayTaggedUserDataAccessInterface;

/**
 * This interface functions as an aggregation of the interfaces defined by the
 * various use cases and their needs from the Data Access. The interface is
 * implemented by a Persistence class, and as a result that class implements
 * all the required functionality for the various use cases.
 */
public interface Persistence extends DisplayFavouriteUserDataAccessInterface, DisplayTaggedUserDataAccessInterface {
}
