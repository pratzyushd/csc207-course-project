package view;

import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.display_user_tags.UserTagsController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

public class DisplayTagsView extends JPanel implements PropertyChangeListener{

    public final String viewName = "user tags";
    private final UserTagsController userTagsController;
    private final UserTagsViewModel userTagsViewModel;

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
