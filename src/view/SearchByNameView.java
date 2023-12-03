package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_name.SearchByNameController;
import interface_adapter.search_by_name.SearchByNameState;
import interface_adapter.search_by_name.SearchByNameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchByNameView extends JPanel implements PropertyChangeListener {
    public final String viewName = "search recipes by name";
    private final SearchByNameController searchByNameController;
    private final SearchByNameViewModel searchByNameViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * View for the search by name use case.
     * @param searchByNameController controller to execute this use case.
     * @param searchByNameViewModel representation of the information required for this use case.
     * @param viewManagerModel manager to handle switching to another view from this one.
     */
    public SearchByNameView(SearchByNameController searchByNameController, SearchByNameViewModel searchByNameViewModel, ViewManagerModel viewManagerModel) {
        this.searchByNameController = searchByNameController;
        this.searchByNameViewModel = searchByNameViewModel;
        this.viewManagerModel = viewManagerModel;
        searchByNameViewModel.addPropertyChangeListener(this);

        setLayout(new FlowLayout());
        Color bgColour = new Color(200,210,223);
        setBackground(bgColour);

        JLabel idLabel = new JLabel("Enter the recipe Name:");
        JTextField idTextField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        JButton backToHomeButton = new JButton("Back to Home");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipeName = idTextField.getText();
                searchByNameController.execute(recipeName);
                System.out.println("Searching for Recipe Name: " + recipeName);
            }
        });

        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("home view");
                viewManagerModel.firePropertyChanged();
            }
        });

        add(idLabel);
        add(idTextField);
        add(searchButton);
        add(backToHomeButton);

        setPreferredSize(new Dimension(600, 200));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchByNameState state = (SearchByNameState) evt.getNewValue();
        if (state.getRecipeNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getRecipeNameError());
        }
    }
    // Add methods to update the view based on the ViewModel if needed here
}
