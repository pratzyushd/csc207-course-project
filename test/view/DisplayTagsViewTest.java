package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_user_tags.UserTagsController;
import interface_adapter.display_user_tags.UserTagsViewModel;
import org.junit.Assert;
import use_case.display_user_tags.*;

import data_access.InMemoryPersistenceMock;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.util.List;


public class DisplayTagsViewTest {
private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final UserTagsViewModel userTagsViewModel = new UserTagsViewModel();
    private final DisplayUserTagsUserDataAccessInterface mockDAO = new InMemoryPersistenceMock();
    private final DisplayUserTagsInputData inputData = new DisplayUserTagsInputData();

    @Test
    public void testDisplayTagsView() {

        // create presenter
        DisplayUserTagsOutputBoundary presenter = outputData -> {
            List<String> output = outputData.getUserTags();
            assertEquals(2, output.size());
            assertTrue(output.contains("tag1"));
            assertTrue(output.contains("tag2"));
        };

        // create interactor
        DisplayUserTagsInputBoundary interactor = new DisplayUserTagsInteractor(presenter, mockDAO);

        // create controller
        UserTagsController controller = new UserTagsController(interactor);

        // create a new DisplayTagsView object
        DisplayTagsView displayTagsView = new DisplayTagsView(controller, userTagsViewModel, viewManagerModel);

        // create a new JFrame object
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(displayTagsView);
        frame.pack();
        frame.setVisible(true);

        // execute controller
        controller.execute();

        // set tags
        List<String> tags = mockDAO.getTags(mockDAO.getUser());
        userTagsViewModel.setTags(tags);

        // check values
        String[] tagsList2 = userTagsViewModel.getTags();
        assertEquals(2, tagsList2.length);
        assertTrue(tagsList2[0].equals("tag1") || tagsList2[1].equals("tag1"));
        assertTrue(tagsList2[0].equals("tag2") || tagsList2[1].equals("tag2"));
        System.out.println(tagsList2[0]);
        System.out.println(tagsList2[1]);

        // updates tags JList
        JList<String> tagsList = (JList<String>) displayTagsView.getComponent(0);
        tagsList.setListData(tagsList2);

        // check tagsList values
        assertEquals(2, tagsList.getModel().getSize());
        assertTrue(tagsList.getModel().getElementAt(0).equals("tag1") || tagsList.getModel().getElementAt(1).equals("tag1"));
        assertTrue(tagsList.getModel().getElementAt(0).equals("tag2") || tagsList.getModel().getElementAt(1).equals("tag2"));

        // test clicking on a tag
        tagsList.setSelectedIndex(0);
        tagsList.dispatchEvent(new java.awt.event.MouseEvent(tagsList, java.awt.event.MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 1, false));

        // test backToOptionsButton
        JButton backToOptionsButton = (JButton) displayTagsView.getComponent(1);
        backToOptionsButton.dispatchEvent(new java.awt.event.MouseEvent(backToOptionsButton, java.awt.event.MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 1, false));
        assertEquals("user recipes", viewManagerModel.getActiveView());
    }
}
