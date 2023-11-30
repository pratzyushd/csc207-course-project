package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;

public class HomeView extends JPanel implements PropertyChangeListener {

    public final String viewName = "home view";

    private ViewManagerModel viewManagerModel;

    public HomeView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        // Create buttons for different use cases
        JButton button1 = new JButton("Search by ID");
        JButton button2 = new JButton("Search by Cuisine");
        JButton button3 = new JButton("Search by Name");

        JButton button4 = new JButton("Display Favourite Recipes");
        JButton button5 = new JButton("Display Tagged Recipes");

        // Set layout manager
        setLayout(new GridLayout(3, 1));

        // Add buttons to the frame
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);

        // Add action listeners to handle button clicks
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Search by ID");
                // change view to SearchByIdView
                viewManagerModel.setActiveView("search recipe by id");
                viewManagerModel.firePropertyChanged();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Search by Cuisine");
                // change view to SearchByCuisineView
                viewManagerModel.setActiveView("search recipes by cuisine");
                viewManagerModel.firePropertyChanged();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Search by Name");
                // change view to SearchByNameView
                viewManagerModel.setActiveView("search recipes by name");
                viewManagerModel.firePropertyChanged();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Display Favourite Recipes");
                // change view to DisplayFavouriteRecipesView
                viewManagerModel.setActiveView("display favourite recipes");
                viewManagerModel.firePropertyChanged();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomeView.this, "Selected Display Tagged Recipes");
                // change view to DisplayTaggedRecipesView
                viewManagerModel.setActiveView("display tagged recipes");
                viewManagerModel.firePropertyChanged();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

