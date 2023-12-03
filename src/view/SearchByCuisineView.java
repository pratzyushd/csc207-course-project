package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_cuisine.SearchByCuisineController;
import interface_adapter.search_by_cuisine.SearchByCuisineState;
import interface_adapter.search_by_cuisine.SearchByCuisineViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchByCuisineView extends JPanel implements PropertyChangeListener {
    public final String viewName = "search recipes by cuisine";
    private final SearchByCuisineController searchByCuisineController;
    private final SearchByCuisineViewModel searchByCuisineViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * View for the search by cuisine use case.
     * @param searchByCuisineController the controller to start this ues case.
     * @param searchByCuisineViewModel the representation of the information for this use case.
     * @param viewManagerModel the manager for switching to another view from this one.
     */
    public SearchByCuisineView(SearchByCuisineController searchByCuisineController, SearchByCuisineViewModel searchByCuisineViewModel, ViewManagerModel viewManagerModel) {
        this.searchByCuisineController = searchByCuisineController;
        this.searchByCuisineViewModel = searchByCuisineViewModel;
        this.viewManagerModel = viewManagerModel;
        searchByCuisineViewModel.addPropertyChangeListener(this);

        setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter the Cuisine to Search For:");
        JTextField idTextField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        JButton backToHomeButton = new JButton("Back to Home");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipeCuisine = idTextField.getText();
                searchByCuisineController.execute(recipeCuisine);
                System.out.println("Searching for Recipe Cuisine: " + recipeCuisine);
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
        Color bgColour = new Color(200,210,223);
        setBackground(bgColour);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchByCuisineState state = (SearchByCuisineState) evt.getNewValue();
        if (state.getRecipeCuisineError() != null) {
            JOptionPane.showMessageDialog(this, state.getRecipeCuisineError());
        }
    }
    // Add methods to update the view based on the ViewModel if needed here
}

