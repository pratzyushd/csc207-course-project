package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.FavouriteRecipesController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

public class DisplayFavouriteView extends JPanel implements PropertyChangeListener{
    public final String viewName = "user favourite recipes";
    private final FavouriteRecipesController favouriteRecipesController;
    private final RecipesViewModel recipesViewModel;

    public DisplayFavouriteView(FavouriteRecipesController favouriteRecipesController, RecipesViewModel recipesViewModel) {
        this.favouriteRecipesController = favouriteRecipesController;
        this.recipesViewModel = recipesViewModel;

        setLayout(new FlowLayout());

        JButton favouriteButton = new JButton("Favourite Recipes");

        favouriteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                displayFavouriteRecipes();
                System.out.println("Displaying favourite recipes");
            }
        });

        add(favouriteButton);

        setPreferredSize(new Dimension(600, 200));
    }

    public void displayFavouriteRecipes() {
        favouriteRecipesController.execute();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}