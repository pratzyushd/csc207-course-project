package data_access;

import entity.Recipe;
import use_case.favourite_recipe.FavouriteRecipeUserDataAccessInterface;
import use_case.search_by_cuisine.SearchCuisineUserDataAccessInterface;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import use_case.search_by_name.SearchNameUserDataAccessInterface;

/* This interface acts as an aggregation of the other interfaces that we have
 * from the various use cases. */
public interface RecipeAPI extends FavouriteRecipeUserDataAccessInterface, SearchCuisineUserDataAccessInterface,
        SearchIdUserDataAccessInterface, SearchNameUserDataAccessInterface {
}
