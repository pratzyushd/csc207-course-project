package use_case.search_by_name;

import entity.Recipe;

public interface SearchNameUserDataAccessInterface {
    Recipe[] searchRecipesByName(String name);
}
