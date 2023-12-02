package view;

import interface_adapter.display_recipes.RecipesViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import interface_adapter.ViewManagerModel;

public class DisplayRecipeView extends JPanel implements PropertyChangeListener {

    public final String viewName = "display recipe";

    private ViewManagerModel viewManagerModel;

    /**
     * Create a new view to display a recipe.
     */
    public DisplayRecipeView(RecipesViewModel recipesViewModel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel recipeName = new JLabel(recipesViewModel.getRecipeName());
        recipeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel recipeID = new JLabel("ID: " + recipesViewModel.getRecipeID());
        recipeID.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel recipeOrigin = new JLabel("Cuisine: " + recipesViewModel.getRecipeOrigin());
        recipeOrigin.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel recipeCategory = new JLabel("Category: " + recipesViewModel.getRecipeCategory());
        recipeCategory.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea recipeInstructions = new JTextArea(recipesViewModel.getRecipeInstructions());
        recipeInstructions.setLineWrap(true);
        recipeInstructions.setWrapStyleWord(true);
        recipeInstructions.setEditable(false);


        add(recipeName);
        add(recipeID);
        add(recipeOrigin);
        add(recipeCategory);
        add(recipeInstructions);

        setPreferredSize(new Dimension(800, 600));

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
