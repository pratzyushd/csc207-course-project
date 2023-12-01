package view;

import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.display_user_tags.UserTagsController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

/**
 * The user tags view for the application.
 */
public class DisplayTagsView extends JPanel implements PropertyChangeListener{

    public final String viewName = "user tags";
    private final UserTagsController userTagsController;
    private final UserTagsViewModel userTagsViewModel;

    /**
     * Create a new view to display the tags that a user has.
     * @param userTagsController the controller for this use case.
     * @param userTagsViewModel the view model that stores a representation of this view.
     */
    public DisplayTagsView(UserTagsController userTagsController, UserTagsViewModel userTagsViewModel) {
        this.userTagsController = userTagsController;
        this.userTagsViewModel = userTagsViewModel;

        setLayout(new FlowLayout());

        JButton tagsButton = new JButton("User Tags");

        tagsButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                displayUserTags();
            }
        });

        add(tagsButton);

        setPreferredSize(new Dimension(600, 200));
    }

    public void displayUserTags() {
        userTagsController.execute();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
