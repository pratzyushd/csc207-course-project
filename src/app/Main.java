package app;

import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.SearchResultViewModel;
import interface_adapter.favourite_recipe.FavouriteRecipeViewModel;
import interface_adapter.search_by_id.SearchByIdViewModel;
import interface_adapter.ViewManagerModel;
import use_case.favourite_recipe.FavouriteRecipeUserDataAccessInterface;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import view.SearchByIdView;
import view.SearchResultView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * The entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("MyRecipeMate");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        // 'views' serves as a container for various "views" (other JPanels).
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create and add SearchById view
        SearchByIdViewModel searchByIdViewModel = new SearchByIdViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        SearchIdUserDataAccessInterface searchIdUserDataAccessObject = new TheMealDB(recipeFactory);
        SearchByIdView searchByIdView = SearchByIdUseCaseFactory.create(viewManagerModel, searchByIdViewModel,
                searchResultViewModel, searchIdUserDataAccessObject);
        views.add(searchByIdView, searchByIdView.viewName);

        // Create and add SearchResultView (for favouriting recipes and adding tags)
        FavouriteRecipeViewModel favouriteRecipeViewModel = new FavouriteRecipeViewModel();
        FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface = new TheMealDB(recipeFactory);

        CommonUserFactory userFactory = new CommonUserFactory();
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);
        // TODO - provide jsonPersistence with a valid file path. Currently empty path.
        Persistence jsonPersistence = new JSONPersistence(userFactory, "", recipeAPI);
        SearchResultView searchResultView = SearchResultUseCaseFactory.create(viewManagerModel, favouriteRecipeViewModel,
                searchResultViewModel, favouriteRecipeUserDataAccessInterface, jsonPersistence);
        views.add(searchResultView, searchResultView.viewName);

        // Set the active view to start with search by id
        // TODO - replace the initial active view with the create new user / load user from file screen.
        viewManagerModel.setActiveView(searchByIdView.viewName);
        viewManagerModel.firePropertyChanged();

        // Pack the application and center the frame to the screen
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}