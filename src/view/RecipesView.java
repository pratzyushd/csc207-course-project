package view;

import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_recipes.TaggedRecipesController;
import interface_adapter.display_recipes.FavouriteRecipesController;

import interface_adapter.display_user_tags.UserTagsViewModel;
import interface_adapter.display_user_tags.UserTagsController;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class RecipesView extends JPanel{

    public final String viewName = "user recipes";

    private final TaggedRecipesController taggedRecipesController;
    private final FavouriteRecipesController favouriteRecipesController;

    private final UserTagsController userTagsController;
    private final RecipesViewModel recipesViewModel;

    private final UserTagsViewModel userTagsViewModel;


    public RecipesView(TaggedRecipesController taggedRecipesController, FavouriteRecipesController favouriteRecipesController, UserTagsController userTagsController, RecipesViewModel recipesViewModel, UserTagsViewModel UserTagsViewModel) {
        this.taggedRecipesController = taggedRecipesController;
        this.favouriteRecipesController = favouriteRecipesController;
        this.userTagsController = userTagsController;
        this.recipesViewModel = recipesViewModel;
        this.userTagsViewModel = UserTagsViewModel;

        setLayout(new FlowLayout());

        JLabel optionLabel = new JLabel("Select an option: ");
        JButton favouriteButton = new JButton("Favourite Recipes");
        JButton taggedButton = new JButton("Tagged Recipes");

        favouriteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayFavouriteRecipes();
            }
        });

        taggedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayUserTags();
            }
        });


    }

    // Want to implement so that when taggedButton is clicked, it will first display all the tags that the user has,
    // selecting a tag will then display all the recipes that are tagged with that tag
    public void displayFavouriteRecipes() {
        //TODO: implement
        favouriteRecipesController.execute();
    }

    public void displayUserTags() {
        //TODO: implement
        userTagsController.execute();
    }

    public void displayTaggedRecipes(String tag) {
        //TODO: implement
        taggedRecipesController.execute(tag);
    }
}
