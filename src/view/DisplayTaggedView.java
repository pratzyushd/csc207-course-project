package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.TaggedRecipesController;
import interface_adapter.display_user_tags.UserTagsViewModel;

import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

/**
 * The tagged recipes view for the application.
 */
public class DisplayTaggedView extends JPanel implements PropertyChangeListener{

    public final String viewName = "user tagged recipes";
    private final TaggedRecipesController taggedRecipesController;
    private final RecipesViewModel recipesViewModel;
    private final UserTagsViewModel userTagsViewModel;

    /**
     * Create a new view to display tagged recipes.
     * @param taggedRecipesController the controller for this view.
     * @param recipesViewModel the model representation of this view.
     */
    public DisplayTaggedView(TaggedRecipesController taggedRecipesController, RecipesViewModel recipesViewModel, UserTagsViewModel userTagsViewModel) {
        this.taggedRecipesController = taggedRecipesController;
        this.recipesViewModel = recipesViewModel;
        this.userTagsViewModel = userTagsViewModel;

        setLayout(new FlowLayout());


        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                taggedRecipesController.execute("tag");
            }
        });

        setPreferredSize(new Dimension(600, 200));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
