package view;

import interface_adapter.SearchResultState;
import interface_adapter.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.favourite_recipe.FavouriteRecipeController;
import interface_adapter.favourite_recipe.FavouriteRecipeState;
import interface_adapter.favourite_recipe.FavouriteRecipeViewModel;
import interface_adapter.tag_recipe.TagRecipeController;
import interface_adapter.tag_recipe.TagRecipeState;
import interface_adapter.tag_recipe.TagRecipeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SearchResultView extends JPanel implements PropertyChangeListener {
    public final String viewName = "recipe search result(s)";
    private final FavouriteRecipeController favouriteRecipeController;
    private final FavouriteRecipeViewModel favouriteRecipeViewModel;
    private final TagRecipeController tagRecipeController;
    private final TagRecipeViewModel tagRecipeViewModel;
    private final SearchResultViewModel searchResultViewModel;
    private final ViewManagerModel viewManagerModel;
    private ArrayList<String> recipeNames = new ArrayList<>();
    private ArrayList<String> recipeCategories = new ArrayList<>();
    private ArrayList<String> recipeInstructions = new ArrayList<>();
    private ArrayList<String> recipeAreaOfOrigins = new ArrayList<>();
    private ArrayList<Integer> recipeIds = new ArrayList<>();
    private JLabel searchTerm = new JLabel("");
    private JLabel recipeName = new JLabel("");
    private JButton addToFavouritesButton;
    private JLabel tagRecipeLabel;
    private JButton tagRecipeButton;
    private JButton viewInstructions;
    private JButton backToHomeButton;

    public SearchResultView(FavouriteRecipeController favouriteRecipeController, TagRecipeController tagRecipeController,
                            FavouriteRecipeViewModel favouriteRecipeViewModel, TagRecipeViewModel tagRecipeViewModel,
                            SearchResultViewModel searchResultViewModel, ViewManagerModel viewManagerModel) {
        this.favouriteRecipeController = favouriteRecipeController;
        this.favouriteRecipeViewModel = favouriteRecipeViewModel;
        this.favouriteRecipeViewModel.addPropertyChangeListener(this);
        this.searchResultViewModel = searchResultViewModel;
        this.searchResultViewModel.addPropertyChangeListener(this);
        this.tagRecipeController = tagRecipeController;
        this.tagRecipeViewModel = tagRecipeViewModel;
        this.tagRecipeViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        setPreferredSize(new Dimension(1200, 800));
        setLayout(null);
    }
    public void updateUI() {
        if (recipeIds == null) {
            ; // If there are no results to show, do not populate the JPanel with anything.
        } else {
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(null);

            final JTextField[] tagNameTextFields = new JTextField[recipeIds.size()];
            for (int i = 0; i < Math.min(recipeIds.size(), 15); i++) {
                final int currentRecipeId = recipeIds.get(i);
                final String currentRecipeName = recipeNames.get(i);
                final String currentRecipeCategory = recipeCategories.get(i);
                final String currentRecipeAreaOfOrigin = recipeAreaOfOrigins.get(i);
                final String currentRecipeInstructions = recipeInstructions.get(i);

                recipeName = new JLabel("");
                addToFavouritesButton = new JButton("Add to Favourites");
                tagRecipeLabel = new JLabel("Tag Name: ");
                tagRecipeButton = new JButton("Tag Recipe");
                JTextField tagNameTextField = new JTextField(10);
                viewInstructions = new JButton("Instructions");
                backToHomeButton = new JButton("Back to Home");

                // Update the main label with recipe name, category, and cuisine
                recipeName.setText("Name: " + currentRecipeName
                        + " | Category: " + currentRecipeCategory
                        + " | Cuisine: " + currentRecipeAreaOfOrigin);
                addToFavouritesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // favouriteRecipeViewModel.setState("");
                        favouriteRecipeController.execute(currentRecipeId);
                    }
                });

                tagRecipeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String tagName = tagNameTextField.getText();
                        tagRecipeController.execute(currentRecipeId, tagName);
                    }
                });

                viewInstructions.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JTextArea textArea = new JTextArea(currentRecipeInstructions);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        scrollPane.setPreferredSize(new Dimension(800, 500));  // Adjust size as needed

                        JOptionPane.showMessageDialog(SearchResultView.this, scrollPane, "Message", JOptionPane.PLAIN_MESSAGE);
                    }
                });

                backToHomeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewManagerModel.setActiveView("home view");
                        viewManagerModel.firePropertyChanged();
                    }
                });

                backToHomeButton.setBounds(20, 10, 115, 30);
                add(backToHomeButton);
                // TODO - add an action listener to make back to home a functional button

                searchTerm.setBounds(140, 9, 450, 35);
                searchTerm.setFont(new Font("SansSerif", Font.PLAIN, 13));
                add(searchTerm);

                recipeName.setBounds(25, 50 + i*50, 450, 35);
                recipeName.setFont(new Font("SansSerif", Font.PLAIN, 13));
                add(recipeName);

                viewInstructions.setBounds(550, 50 + i*50, 120, 35);
                add(viewInstructions);

                addToFavouritesButton.setBounds(680, 50 + i*50, 140, 35);
                add(addToFavouritesButton);

                tagRecipeLabel.setBounds(840, 50 + i*50, 120, 35);
                add(tagRecipeLabel);

                tagNameTextField.setBounds(910, 50 + i*50, 160, 30);
                add(tagNameTextField);

                tagRecipeButton.setBounds(1075, 50 + i*50, 120, 35);
                add(tagRecipeButton);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchResultState) {
            SearchResultState state = (SearchResultState) evt.getNewValue();
            this.removeAll();
            setFields(state);
        } else if (evt.getNewValue() instanceof FavouriteRecipeState) {
            FavouriteRecipeState state = (FavouriteRecipeState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getFavouriteRecipeMessage());
        } else if (evt.getNewValue() instanceof TagRecipeState) {
            TagRecipeState state = (TagRecipeState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getTagRecipeMessage());
        }
    }

    private void setFields(SearchResultState state) {
        // Update the recipe names, categories, instructions, areas of origin, and ids from state
        recipeNames = state.getRecipeNames();
        recipeCategories = state.getRecipeCategories();
        recipeInstructions = state.getRecipeInstructions();
        recipeAreaOfOrigins = state.getRecipeAreaOfOrigins();
        recipeIds = state.getRecipeIds();
        searchTerm.setText("Search results for " + "\"" + state.getSearchTerm() + "\"");
        updateUI();
    }
}
