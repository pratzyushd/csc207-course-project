package view;

import interface_adapter.search_by_name.SearchByNameController;
import interface_adapter.search_by_name.SearchByNameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchByNameView extends JPanel {
    public final String viewName = "search recipes by name";
    private final SearchByNameController searchByNameController;
    private final SearchByNameViewModel searchByNameViewModel;

    public SearchByNameView(SearchByNameController searchByNameController, SearchByNameViewModel searchByNameViewModel) {
        this.searchByNameController = searchByNameController;
        this.searchByNameViewModel = searchByNameViewModel;

        setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter the recipe Name:");
        JTextField idTextField = new JTextField(10);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipeName = idTextField.getText();
                searchByNameController.execute(recipeName);
                System.out.println("Searching for Recipe Name: " + recipeName);
            }
        });

        add(idLabel);
        add(idTextField);
        add(searchButton);

        setPreferredSize(new Dimension(600, 200));
    }
    // Add methods to update the view based on the ViewModel if needed here
}
