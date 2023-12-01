package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;

/**
 * The home page view for the application.
 */
public class HomeView extends JPanel implements PropertyChangeListener {

    public final String viewName = "home view";
    private ViewManagerModel viewManagerModel;

    public HomeView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        // Create buttons for different use cases
        JButton IDButton = new JButton("Search by ID");
        JButton CuisineButton = new JButton("Search by Cuisine");
        JButton NameButton = new JButton("Search by Name");
        JButton RecipeButton = new JButton("Display User Recipes Collection");

        // Set layout manager
        setLayout(new GridLayout(3, 1));

        // Add buttons to the frame
        add(IDButton);
        add(CuisineButton);
        add(NameButton);
        add(RecipeButton);

        // Add action listeners to handle button clicks
        IDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Search by ID");
                // change view to SearchByIdView
                viewManagerModel.setActiveView("search recipe by id");
                viewManagerModel.firePropertyChanged();
            }
        });

        CuisineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Search by Cuisine");
                // change view to SearchByCuisineView
                viewManagerModel.setActiveView("search recipes by cuisine");
                viewManagerModel.firePropertyChanged();
            }
        });

        NameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Search by Name");
                // change view to SearchByNameView
                viewManagerModel.setActiveView("search recipes by name");
                viewManagerModel.firePropertyChanged();
            }
        });

        RecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Display User Recipes Collection");
                // change view to RecipesView
                viewManagerModel.setActiveView("user recipes");
                viewManagerModel.firePropertyChanged();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

