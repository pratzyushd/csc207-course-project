package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.TaggedRecipesController;
import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.ViewManagerModel;

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

    private ViewManagerModel viewManagerModel;

    /**
     * Create a new view to display tagged recipes.
     * @param taggedRecipesController the controller for this view.
     * @param recipesViewModel the model representation of this view.
     */
    public DisplayTaggedView(TaggedRecipesController taggedRecipesController, RecipesViewModel recipesViewModel, UserTagsViewModel userTagsViewModel, ViewManagerModel viewManagerModel) {
        this.taggedRecipesController = taggedRecipesController;
        this.recipesViewModel = recipesViewModel;
        this.userTagsViewModel = userTagsViewModel;
        this.viewManagerModel = viewManagerModel;

        setLayout(new FlowLayout());

        JList<String> taggedList = new JList<>();
        JButton backToOptionsButton = new JButton("Back to Options");


        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                taggedRecipesController.execute(userTagsViewModel.getTag());
                taggedList.setListData(recipesViewModel.getRecipes().toArray(new String[0]));
            }
        });

        taggedList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = taggedList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object o = taggedList.getModel().getElementAt(index);
                        System.out.println("Selected: " + o.toString());
                        recipesViewModel.setSelectedRecipeName(o.toString());
                        openRecipeView();
                    }
                }
            }
        });

        backToOptionsButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                viewManagerModel.setActiveView("user recipes");
                viewManagerModel.firePropertyChanged();
            }
        });

        add(taggedList);
        add(backToOptionsButton);

        setPreferredSize(new Dimension(600, 200));
    }

    public void openRecipeView() {
        JFrame frame = new JFrame("Recipe");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new DisplayRecipeView(recipesViewModel));
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
