package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.FavouriteRecipesController;

import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * The favourite recipes view for the application.
 */
public class DisplayFavouriteView extends JPanel implements PropertyChangeListener{
    public final String viewName = "user favourite recipes";
    private final FavouriteRecipesController favouriteRecipesController;
    private final RecipesViewModel recipesViewModel;

    /**
     * Create a new view for displaying favourites, with the associated controller and view model.
     * @param favouriteRecipesController the controller that we call with this view.
     * @param recipesViewModel the representation this view is based on.
     */
    public DisplayFavouriteView(FavouriteRecipesController favouriteRecipesController, RecipesViewModel recipesViewModel) {
        this.favouriteRecipesController = favouriteRecipesController;
        this.recipesViewModel = recipesViewModel;

        setLayout(new FlowLayout());

        JList favouritesList = new JList();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                favouriteRecipesController.execute();
                favouritesList.setListData(recipesViewModel.getRecipes().toArray());
            }
        });

        favouritesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = favouritesList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object o = favouritesList.getModel().getElementAt(index);
                        System.out.println("Selected: " + o.toString());
                        recipesViewModel.setSelectedRecipeName(o.toString());
                        openRecipeView();
                    }
                }
            }
        });

        add(favouritesList);

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