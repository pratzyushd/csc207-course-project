package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;

// The intiial view of the application tht allows user to load previous data or create a new user
public class InitialView {
    public final String viewName = "initial view";

    public InitialView() {
        JFrame frame = new JFrame("MyRecipeMate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new InitialPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public class InitialPanel extends JPanel implements PropertyChangeListener {
        public InitialPanel() {
            setLayout(new FlowLayout());

            JLabel optionLabel = new JLabel("Select an option: ");
            JButton loadButton = new JButton("Load Previous Data");
            JButton createButton = new JButton("Create New User");

            loadButton.addActionListener(e -> {
                System.out.println("Loading previous data");
            });

            createButton.addActionListener(e -> {
                System.out.println("Creating new user");
            });

            add(optionLabel);
            add(loadButton);
            add(createButton);

            setPreferredSize(new Dimension(600, 200));
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            // Add methods to update the view based on the ViewModel if needed here
        }
    }
}
