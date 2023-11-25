package view;

import interface_adapter.search_by_id.SearchByIdController;
import interface_adapter.search_by_id.SearchByIdState;
import interface_adapter.search_by_id.SearchByIdViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchByIdView extends JPanel implements PropertyChangeListener {
    public final String viewName = "search recipe by id";
    private final SearchByIdController searchByIdController;
    private final SearchByIdViewModel searchByIdViewModel;

    public SearchByIdView(SearchByIdController searchByIdController, SearchByIdViewModel searchByIdViewModel) {
        this.searchByIdController = searchByIdController;
        this.searchByIdViewModel = searchByIdViewModel;
        searchByIdViewModel.addPropertyChangeListener(this);

        setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter the recipe ID:");
        JTextField idTextField = new JTextField(10);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipeId = idTextField.getText();
                searchByIdController.execute(recipeId);
                System.out.println("Searching for Recipe ID: " + recipeId);
            }
        });

        add(idLabel);
        add(idTextField);
        add(searchButton);

        setPreferredSize(new Dimension(600, 200));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchByIdState state = (SearchByIdState) evt.getNewValue();
        if (state.getRecipeIdError() != null) {
            JOptionPane.showMessageDialog(this, state.getRecipeIdError());
        }
    }
    // Add methods to update the view based on the ViewModel if needed here
}
