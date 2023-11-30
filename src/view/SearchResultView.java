package view;

import interface_adapter.SearchResultState;
import interface_adapter.SearchResultViewModel;
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

public class SearchResultView extends JPanel implements PropertyChangeListener {
    public final String viewName = "recipe search result(s)";
    private final FavouriteRecipeController favouriteRecipeController;
    private final FavouriteRecipeViewModel favouriteRecipeViewModel;
    private final TagRecipeController tagRecipeController;
    private final TagRecipeViewModel tagRecipeViewModel;
    private final SearchResultViewModel searchResultViewModel;
    private String recipeInstructions = "";
    private String recipeId = "";
    private JLabel recipeName = new JLabel("");
    private JButton addToFavouritesButton;
    private JLabel tagRecipeLabel;
    private JButton tagRecipeButton;
    private JTextField tagNameTextField;
    private JButton viewInstructions;

    public SearchResultView(FavouriteRecipeController favouriteRecipeController, TagRecipeController tagRecipeController,
                            FavouriteRecipeViewModel favouriteRecipeViewModel, TagRecipeViewModel tagRecipeViewModel,
                            SearchResultViewModel searchResultViewModel) {
        this.favouriteRecipeController = favouriteRecipeController;
        this.favouriteRecipeViewModel = favouriteRecipeViewModel;
        this.favouriteRecipeViewModel.addPropertyChangeListener(this);
        this.searchResultViewModel = searchResultViewModel;
        this.searchResultViewModel.addPropertyChangeListener(this);
        this.tagRecipeController = tagRecipeController;
        this.tagRecipeViewModel = tagRecipeViewModel;
        this.tagRecipeViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 800));

        recipeName = new JLabel("");
        addToFavouritesButton = new JButton("Add to Favourites");
        tagRecipeLabel = new JLabel("Tag Name: ");
        tagRecipeButton = new JButton("Tag Recipe");
        tagNameTextField = new JTextField(10);
        viewInstructions = new JButton("View Instructions");

        addToFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // favouriteRecipeViewModel.setState("");
                favouriteRecipeController.execute(Integer.parseInt(recipeId));
            }
        });

        tagRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tagName = tagNameTextField.getText();
                tagRecipeController.execute(Integer.parseInt(recipeId), tagName);
            }
        });

        viewInstructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea = new JTextArea(recipeInstructions);
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(800, 500));  // Adjust size as needed

                JOptionPane.showMessageDialog(SearchResultView.this, scrollPane, "Message", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Configure GridBagConstraints for idLabel
        GridBagConstraints gbcIdLabel = new GridBagConstraints();
        gbcIdLabel.gridx = 0;
        gbcIdLabel.gridy = 0;
        gbcIdLabel.anchor = GridBagConstraints.WEST; // Align to the left
        gbcIdLabel.insets = new Insets(5, 5, 5, 5); // Add some padding
        add(recipeName, gbcIdLabel);

        // Configure GridBagConstraints for viewInstructions
        GridBagConstraints gbcViewInstructions = new GridBagConstraints();
        gbcViewInstructions.gridx = 1;
        gbcViewInstructions.gridy = 0;
        gbcViewInstructions.insets = new Insets(3, 3, 3, 3); // Add some padding
        add(viewInstructions, gbcViewInstructions);

        // Configure GridBagConstraints for addToFavouritesButton
        GridBagConstraints gbcAddToFavourites = new GridBagConstraints();
        gbcAddToFavourites.gridx = 2;
        gbcAddToFavourites.gridy = 0;
        gbcAddToFavourites.insets = new Insets(3, 3, 3, 3); // Add some padding
        add(addToFavouritesButton, gbcAddToFavourites);

        // Configure GridBagConstraints for tagRecipeLabel
        GridBagConstraints gbcTagRecipeLabel = new GridBagConstraints();
        gbcTagRecipeLabel.gridx = 3;
        gbcTagRecipeLabel.gridy = 0;
        gbcTagRecipeLabel.insets = new Insets(3, 3, 3, 3); // Add some padding
        add(tagRecipeLabel, gbcTagRecipeLabel);

        // Configure GridBagConstraints for tagNameTextField
        GridBagConstraints gbcTagNameTextField = new GridBagConstraints();
        gbcTagNameTextField.gridx = 4;
        gbcTagNameTextField.gridy = 0;
        gbcTagNameTextField.insets = new Insets(3, 3, 3, 3); // Add some padding
        add(tagNameTextField, gbcTagNameTextField);

        // Configure GridBagConstraints for tagRecipeButton
        GridBagConstraints gbcTagRecipe = new GridBagConstraints();
        gbcTagRecipe.gridx = 5;
        gbcTagRecipe.gridy = 0;
        gbcTagRecipe.insets = new Insets(3, 3, 3, 3); // Add some padding
        add(tagRecipeButton, gbcTagRecipe);

        // TODO - add a button that takes the user back to the home page
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchResultState) {
            SearchResultState state = (SearchResultState) evt.getNewValue();
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
        // Update the text in the label
        recipeName.setText("Name: " + state.getRecipeName()
                + " | Category: " + state.getRecipeCategory()
                + " | Cuisine: " + state.getRecipeAreaOfOrigin());

        // Update the recipe instructions
        recipeInstructions = state.getRecipeInstructions();
        recipeId = Integer.toString(state.getRecipeId());
    }
}
