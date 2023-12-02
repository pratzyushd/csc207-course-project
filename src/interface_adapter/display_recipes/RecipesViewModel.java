package interface_adapter.display_recipes;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipesViewModel extends ViewModel {
    private List<Map<String, String>> recipesStored;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Map<String, String> selectedRecipe;

    /**
     * Constructs a new RecipesViewModel with the specified isFavourite status.
     *
     * @param specifyView 0 if the view is for favourite recipes, 1 if the view is for tagged recipes, 2 if the view is for a single recipe.
     */
    public RecipesViewModel(int specifyView) {
        super(switch (specifyView) {
            case 0 -> "DisplayFavouriteView";
            case 1 -> "DisplayTaggedView";
            case 2 -> "DisplayRecipeView";
            default -> throw new IllegalStateException("Unexpected value: " + specifyView);
        });
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.getViewName());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the list of recipes in the view model given a list of recipes as a list of hashmaps.
     * @param recipes the list of recipes to set.
     */
    public void setRecipes(List<Map<String, String>> recipes) {
        this.recipesStored = recipes;
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Map<String, String> recipe : recipes) {
            recipeNames.add(recipe.get("name"));
        }
        support.firePropertyChange("recipes", new ArrayList<String>(), recipeNames);
    }

    // getter for the recipes
    public List<String> getRecipes() {
        support.firePropertyChange("recipes", null, null);
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Map<String, String> recipe : recipesStored) {
            recipeNames.add(recipe.get("name"));
        }
        return recipeNames;
    }
    public void setTags(List<String> tags){
        support.firePropertyChange("tags", null, tags);
    }

    public void getTag() {
        support.firePropertyChange("tag", null, null);
    }

    public void setSelectedRecipeName(String name) {
        support.firePropertyChange("selectedRecipeName", null, name);
        for (Map<String, String> recipe : recipesStored) {
            if (recipe.get("name").equals(name)) {
                selectedRecipe = recipe;
            }
        }
    }

    public void setSelectedRecipe(Map<String, String> recipe) {
        support.firePropertyChange("selectedRecipe", null, recipe);
    }

    public String getRecipeName() {
        support.firePropertyChange("recipeName", null, null);
        return selectedRecipe.get("name");
    }

    public String getRecipeID() {
        support.firePropertyChange("recipeID", null, null);
        return selectedRecipe.get("id");
    }

    public String getRecipeOrigin() {
        support.firePropertyChange("recipeOrigin", null, null);
        return selectedRecipe.get("areaOfOrigin");
    }

    public String getRecipeInstructions() {
        support.firePropertyChange("recipeInstructions", null, null);
        return selectedRecipe.get("instructions");
    }

    public String getRecipeCategory() {
        support.firePropertyChange("recipeCategory", null, null);
        return selectedRecipe.get("category");
    }
}
