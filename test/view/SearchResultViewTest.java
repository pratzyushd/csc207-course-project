package view;

import interface_adapter.search_result.SearchResultState;
import use_case.favourite_recipe.*;
import use_case.tag_recipe.*;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.favourite_recipe.*;
import interface_adapter.tag_recipe.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SearchResultViewTest {

    @Test
    public void testSearchResultView() {
        FavouriteRecipeInputBoundary favouriteRecipeInputBoundary = new FavouriteRecipeInputBoundary() {
            @Override
            public void execute(FavouriteRecipeInputData favouriteRecipeInputData) {
            }
        };

        TagRecipeInputBoundary tagRecipeInputBoundary = new TagRecipeInputBoundary() {
            @Override
            public void execute(TagRecipeInputData tagRecipeInputData) {
            }
        };
        FavouriteRecipeState favouriteRecipeState = new FavouriteRecipeState();
        FavouriteRecipeController favouriteRecipeController = new FavouriteRecipeController(favouriteRecipeInputBoundary);
        FavouriteRecipeViewModel favouriteRecipeViewModel = new FavouriteRecipeViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TagRecipeController tagRecipeController = new TagRecipeController(tagRecipeInputBoundary);
        TagRecipeViewModel tagRecipeViewModel = new TagRecipeViewModel();
        SearchResultView searchResultView = new SearchResultView(favouriteRecipeController, tagRecipeController,
                favouriteRecipeViewModel, tagRecipeViewModel, searchResultViewModel, viewManagerModel);



        // create a new JFrame object
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(searchResultView);
        frame.pack();
        frame.setVisible(true);

        // updateUI
        searchResultView.updateUI();

        // test UI
        assertEquals("recipe search result(s)", searchResultView.viewName);
        assertEquals(0, searchResultView.getComponentCount());

        // set recipes
        List<String> recipeNames = List.of("recipe1", "recipe2");
        ArrayList<String> recipeNamesArrayList = new ArrayList<>(recipeNames);
        List<String> recipeCategories = List.of("category1", "category2");
        ArrayList<String> recipeCategoriesArrayList = new ArrayList<>(recipeCategories);
        List<String> recipeInstructions = List.of("instruction1", "instruction2");
        ArrayList<String> recipeInstructionsArrayList = new ArrayList<>(recipeInstructions);
        List<String> recipeAreaOfOrigins = List.of("area1", "area2");
        ArrayList<String> recipeAreaOfOriginsArrayList = new ArrayList<>(recipeAreaOfOrigins);
        List<Integer> recipeIds = List.of(1, 2);
        ArrayList<Integer> recipeIdsArrayList = new ArrayList<>(recipeIds);

        // update state
        SearchResultState searchResultState = new SearchResultState();
        searchResultState.setRecipeNames(recipeNamesArrayList);
        searchResultState.setRecipeCategories(recipeCategoriesArrayList);
        searchResultState.setRecipeInstructions(recipeInstructionsArrayList);
        searchResultState.setRecipeAreaOfOrigins(recipeAreaOfOriginsArrayList);
        searchResultState.setRecipeIds(recipeIdsArrayList);
        searchResultViewModel.setState(searchResultState);
        searchResultViewModel.firePropertyChanged();

        // check for loop
        for (int i = 0; i < recipeNames.size(); i++) {
            // test recipeNames
            assertEquals(recipeNames.get(i), searchResultState.getRecipeNames().get(i));
            // test recipeCategories
            assertEquals(recipeCategories.get(i), searchResultState.getRecipeCategories().get(i));
            // test recipeInstructions
            assertEquals(recipeInstructions.get(i), searchResultState.getRecipeInstructions().get(i));
            // test recipeAreaOfOrigins
            assertEquals(recipeAreaOfOrigins.get(i), searchResultState.getRecipeAreaOfOrigins().get(i));
            // test recipeIds
            assertEquals(recipeIds.get(i), searchResultState.getRecipeIds().get(i));

            // add and check button
            JButton addToFavouritesButton = (JButton) searchResultView.getComponent(3);
            assertEquals("Add to Favourites", addToFavouritesButton.getText());
            addToFavouritesButton.dispatchEvent(new MouseEvent(addToFavouritesButton, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 1, false));
        }



    }
}

