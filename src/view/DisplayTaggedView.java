package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.TaggedRecipesController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

public class DisplayTaggedView extends JPanel implements PropertyChangeListener{

    public final String viewName = "user tagged recipes";
    private final TaggedRecipesController taggedRecipesController;
    private final RecipesViewModel recipesViewModel;

    public DisplayTaggedView(TaggedRecipesController taggedRecipesController, RecipesViewModel recipesViewModel) {
        this.taggedRecipesController = taggedRecipesController;
        this.recipesViewModel = recipesViewModel;

        setLayout(new FlowLayout());

        JButton taggedButton = new JButton("Tagged Recipes");

        taggedButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                displayTaggedRecipes();
            }
        });

        add(taggedButton);

        setPreferredSize(new Dimension(600, 200));
    }

    public void displayTaggedRecipes() {
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
