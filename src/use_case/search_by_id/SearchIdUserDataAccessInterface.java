package use_case.search_by_id;

import entity.Recipe;

public interface SearchIdUserDataAccessInterface {
    Recipe[] searchRecipesById(String id);
}
