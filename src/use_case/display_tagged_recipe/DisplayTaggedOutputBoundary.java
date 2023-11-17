package use_case.display_tagged_recipe;

import entity.Recipe;
import java.util.List;

public interface DisplayTaggedOutputBoundary {

    void present(List<Recipe> recipes);
}