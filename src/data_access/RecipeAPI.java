package data_access;

import entity.Recipe;
import use_case.favourite_recipe.FavouriteRecipeUserDataAccessInterface;
import use_case.search_by_cuisine.SearchCuisineUserDataAccessInterface;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import use_case.search_by_name.SearchNameUserDataAccessInterface;
import use_case.tag_recipe.TagRecipeUserDataAccessInterface;

/* This interface acts as an aggregation of the other interfaces that we have
 * from the various use cases. */
public interface RecipeAPI extends FavouriteRecipeUserDataAccessInterface, TagRecipeUserDataAccessInterface,
        SearchCuisineUserDataAccessInterface, SearchIdUserDataAccessInterface, SearchNameUserDataAccessInterface {
    /**
     * Search for recipes given a list of IDs.
     * @param ids the list of IDs to search for.
     * @return array containing Recipe objects (or null of invalid ID)
     */
    public Recipe[] searchRecipesByListOfIds(String[] ids);
}
