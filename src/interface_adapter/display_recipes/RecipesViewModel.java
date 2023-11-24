package interface_adapter.display_recipes;

import entity.Recipe;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class RecipesViewModel extends ViewModel {
    private final boolean isFavourite;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RecipesViewModel(boolean isFavourite) {
        super(isFavourite ? "FavouriteRecipesView" : "TaggedRecipesView");
        this.isFavourite = isFavourite;
    }

    public boolean isFavourite() {
        return this.isFavourite;
    }


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.getViewName());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setRecipes(List<Recipe> recipes) {
        //TODO: implement set recipes method

    }
}
