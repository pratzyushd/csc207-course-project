package view;

import interface_adapter.SearchByIdController;
import interface_adapter.SearchByIdViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchByIdView {
    private final SearchByIdController searchByIdController;
    private final SearchByIdViewModel searchByIdViewModel;  // TODO is this necessary?

    public SearchByIdView(SearchByIdController searchByIdController, SearchByIdViewModel searchByIdViewModel) {
        this.searchByIdController = searchByIdController;
        this.searchByIdViewModel = searchByIdViewModel;
    }

    // TODO delete this main method - all views should be created and displayed from app/Main.java
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> createAndShowGUI());
//    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Recipe Search by ID");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter the recipe ID:");
        JTextField idTextField = new JTextField(10);
        JButton searchButton = new JButton("Search");

        // ActionListener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipeId = idTextField.getText();
                // You can perform the search operation here using the entered recipe ID
                // For simplicity, this example just prints the entered ID to the console
                // TODO - later call the appropriate method from the controller
                // searchByIdController.execute(params)
                System.out.println("Searching for Recipe ID: " + recipeId);
            }
        });

        // Adding components to the frame
        frame.add(idLabel);
        frame.add(idTextField);
        frame.add(searchButton);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
