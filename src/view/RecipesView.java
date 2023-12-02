package view;

import interface_adapter.ViewManagerModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

/**
 * The view to select whether to view user's favourite recipes or tagged recipes.
 */
public class RecipesView extends JPanel implements PropertyChangeListener {

    public final String viewName = "user recipes";
    private ViewManagerModel viewManagerModel;

    /**
     * Create a new view to see a given list of recipes.
     * @param viewManagerModel handles switching views as required.
     */
    public RecipesView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        setLayout(new FlowLayout());

        JLabel optionLabel = new JLabel("Select an option: ");
        JButton favouriteButton = new JButton("Favourite Recipes");
        JButton taggedButton = new JButton("Tagged Recipes");

        add(optionLabel);
        add(favouriteButton);
        add(taggedButton);

        favouriteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                viewManagerModel.setActiveView("user favourite recipes");
                viewManagerModel.firePropertyChanged();
            }
        });

        taggedButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                // changes to display tags view instead of tagged recipes because user needs to select a tag first
                viewManagerModel.setActiveView("user tags");
                viewManagerModel.firePropertyChanged();
            }
        });

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
