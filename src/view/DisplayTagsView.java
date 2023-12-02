package view;

import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.display_user_tags.UserTagsController;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
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

        JList<String> tagsList = new JList<String>();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                userTagsController.execute();
                tagsList.setListData(userTagsViewModel.getTags());
                System.out.println("working");

            }
        });

        setPreferredSize(new Dimension(600, 200));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
