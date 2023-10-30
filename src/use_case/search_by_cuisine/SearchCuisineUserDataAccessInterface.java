package use_case.search_by_cuisine;

import entity.Recipe;

public interface SearchCuisineUserDataAccessInterface {
    Recipe[] searchRecipesByCuisine(String cuisine);
}
