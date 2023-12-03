package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.display_user_tags.UserTagsController;
import interface_adapter.ViewManagerModel;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * The user tags view for the application.
 */
public class DisplayTagsView extends JPanel implements PropertyChangeListener{

    public final String viewName = "user tags";
    private final UserTagsController userTagsController;
    private final UserTagsViewModel userTagsViewModel;
    private final ViewManagerModel viewManagerModel;


    /**
     * Create a new view to display the tags that a user has.
     * @param userTagsController the controller for this use case.
     * @param userTagsViewModel the view model that stores a representation of this view.
     */
    public DisplayTagsView(UserTagsController userTagsController, UserTagsViewModel userTagsViewModel, ViewManagerModel viewManagerModel) {
        this.userTagsController = userTagsController;
        this.userTagsViewModel = userTagsViewModel;
        this.viewManagerModel = viewManagerModel;

        setLayout(new FlowLayout());
        Color bgColour = new Color(200,210,223);
        setBackground(bgColour);

        JList<String> tagsList = new JList<>();
        JButton backToOptionsButton = new JButton("Back to Options");

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                userTagsController.execute();
                tagsList.setListData(userTagsViewModel.getTags());
            }
        });

        tagsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = tagsList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object o = tagsList.getModel().getElementAt(index);
                        System.out.println("Selected: " + o.toString());
                        userTagsViewModel.setSelectedTag(o.toString());
                        viewManagerModel.setActiveView("user tagged recipes");
                        viewManagerModel.firePropertyChanged();
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

        add(tagsList);
        add(backToOptionsButton);

        setPreferredSize(new Dimension(600, 200));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
