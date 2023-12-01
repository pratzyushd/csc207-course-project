package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import interface_adapter.ViewManagerModel;

/**
 * The home page view for the application.
 */
public class HomeView extends JPanel implements PropertyChangeListener {

    public final String viewName = "home view";
    private ViewManagerModel viewManagerModel;

    /**
     * Create a new Home Page view.
     * @param viewManagerModel representation of the information in this view.
     */
    public HomeView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        // Create buttons for different use cases
        JButton IDButton = new JButton("Search by ID");
        JButton cuisineButton = new JButton("Search by Cuisine");
        JButton nameButton = new JButton("Search by Name");
        JButton recipeButton = new JButton("Display User Recipes Collection");
        JButton quitButton = new JButton("QUIT");

        setPreferredSize(new Dimension(1400, 800));
        setLayout(null);

        // Add buttons to the frame
        IDButton.setBounds(220, 250, 450, 35);
        add(IDButton);

        cuisineButton.setBounds(220, 300, 450, 35);
        add(cuisineButton);

        nameButton.setBounds(220, 350, 450, 35);
        add(nameButton);

        recipeButton.setBounds(220, 400, 450, 35);
        add(recipeButton);

        quitButton.setBounds(220, 450, 450, 35);
        add(quitButton);

        // Add action listeners to handle button clicks
        IDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change view to SearchByIdView
                viewManagerModel.setActiveView("search recipe by id");
                viewManagerModel.firePropertyChanged();
            }
        });

        cuisineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change view to SearchByCuisineView
                viewManagerModel.setActiveView("search recipes by cuisine");
                viewManagerModel.firePropertyChanged();
            }
        });

        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change view to SearchByNameView
                viewManagerModel.setActiveView("search recipes by name");
                viewManagerModel.firePropertyChanged();
            }
        });

        recipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change view to RecipesView
                viewManagerModel.setActiveView("user recipes");
                viewManagerModel.firePropertyChanged();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the background image
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("assets/myrecipemate_home.png"));
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

