package app;

import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.favourite_recipe.FavouriteRecipeViewModel;
import interface_adapter.initial_app_launch.InitialAppLaunchViewModel;
import interface_adapter.search_by_cuisine.SearchByCuisineViewModel;
import interface_adapter.search_by_id.SearchByIdViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_name.SearchByNameViewModel;
import interface_adapter.tag_recipe.TagRecipeViewModel;
import view.*;

import view.HomeView;
import view.RecipesView;
import view.DisplayTagsView;
import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_user_tags.UserTagsViewModel;
import view.DisplayFavouriteView;
import view.DisplayTaggedView;

import javax.swing.*;
import java.awt.*;

/**
 * The entry point of the application.
 */
public class Main {
    public static class MyCardLayout extends CardLayout {
        @Override
        public Dimension preferredLayoutSize(Container parent) {

            Component current = findCurrentComponent(parent);
            if (current != null) {
                Insets insets = parent.getInsets();
                Dimension pref = current.getPreferredSize();
                pref.width += insets.left + insets.right;
                pref.height += insets.top + insets.bottom;
                return pref;
            }
            return super.preferredLayoutSize(parent);
        }

        public Component findCurrentComponent(Container parent) {
            for (Component comp : parent.getComponents()) {
                if (comp.isVisible()) {
                    return comp;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame application = new JFrame("MyRecipeMate");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        // 'views' serves as a container for various "views" (other JPanels).
        MyCardLayout myCardLayout = new MyCardLayout();
        JPanel views = new JPanel(myCardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, myCardLayout, viewManagerModel, application);

        // Make instance of CommonRecipeFactory
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();

        // Make instance of CommonUserFactory
        CommonUserFactory userFactory = new CommonUserFactory();

        // Make instance of RecipeAPI
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);

        // Make instance of JSONPersistence, let variable have Persistence as type
        Persistence persistence = new JSONPersistence(userFactory, "", recipeAPI);

        // Create and add SearchById view
        SearchByIdViewModel searchByIdViewModel = new SearchByIdViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        SearchByIdView searchByIdView = SearchByIdUseCaseFactory.create(viewManagerModel, searchByIdViewModel,
                searchResultViewModel, recipeAPI);
        views.add(searchByIdView, searchByIdView.viewName);

        // Create and add SearchByCuisine view
        SearchByCuisineViewModel searchByCuisineViewModel = new SearchByCuisineViewModel();
        SearchByCuisineView searchByCuisineView = SearchByCuisineUseCaseFactory.create(viewManagerModel, searchByCuisineViewModel,
                searchResultViewModel, recipeAPI);
        views.add(searchByCuisineView, searchByCuisineView.viewName);

        // Create and add SearchByName view
        SearchByNameViewModel searchByNameViewModel = new SearchByNameViewModel();
        SearchByNameView searchByNameView = SearchByNameUseCaseFactory.create(viewManagerModel, searchByNameViewModel,
                searchResultViewModel, recipeAPI);
        views.add(searchByNameView, searchByNameView.viewName);

        // Create and add SearchResultView (for favouriting recipes and adding tags)
        FavouriteRecipeViewModel favouriteRecipeViewModel = new FavouriteRecipeViewModel();
        TagRecipeViewModel tagRecipeViewModel = new TagRecipeViewModel();
        // TODO, tag and favourite recipe user DA interfaces are the same thing, can we do type casting and use just 1?

        // TODO - provide jsonPersistence with a valid file path. Currently empty path.
        SearchResultView searchResultView = SearchResultUseCaseFactory.create(viewManagerModel, favouriteRecipeViewModel,
                tagRecipeViewModel, searchResultViewModel, recipeAPI,
                recipeAPI, persistence);
//        JScrollPane scrollPane = new JScrollPane(searchResultView);
//        scrollPane.setViewportView(searchResultView);
//        views.add(scrollPane, searchResultView.viewName);
        // TODO - we currently only support up to 15 results. Try implementing scrolling in search result view?
        views.add(searchResultView, searchResultView.viewName);



        // Create and add DisplayFavouriteView
        RecipesViewModel DisplayFavouriteViewModel = new RecipesViewModel(0);
        DisplayFavouriteView displayFavouriteView = DisplayFavouriteUseCaseFactory.create(viewManagerModel, DisplayFavouriteViewModel,
                persistence);
        views.add(displayFavouriteView, displayFavouriteView.viewName);

        // Create and add DisplayTagsView
        UserTagsViewModel userTagsViewModel = new UserTagsViewModel();
        DisplayTagsView displayTagsView = DisplayUserTagsUseCaseFactory.create(viewManagerModel, userTagsViewModel,
                persistence);
        views.add(displayTagsView, displayTagsView.viewName);

        // Create and add DisplayTaggedView
        RecipesViewModel displayTaggedViewModel = new RecipesViewModel(1);
        DisplayTaggedView displayTaggedView = DisplayTaggedUseCaseFactory.create(viewManagerModel, displayTaggedViewModel,
                persistence, userTagsViewModel);
        views.add(displayTaggedView, displayTaggedView.viewName);

        // Create and add RecipesView
        RecipesView recipesView = new RecipesView(viewManagerModel);
        views.add(recipesView, recipesView.viewName);

        // Create and add DisplayRecipeView
        RecipesView displayRecipeView = new RecipesView(viewManagerModel);
        views.add(displayRecipeView, displayRecipeView.viewName);

        // Create and add HomeView
        HomeView homeView = new HomeView(viewManagerModel);
        views.add(homeView, homeView.viewName);

        // Create and add InitialAppLaunchView
        InitialAppLaunchViewModel initialAppLaunchViewModel = new InitialAppLaunchViewModel();
        InitialAppLaunchView initialAppLaunchView = InitialAppLaunchUseCaseFactory.create(viewManagerModel,
                initialAppLaunchViewModel, persistence);
        views.add(initialAppLaunchView, initialAppLaunchView.viewName);

        // Set the active view to start with search by id
        // TODO - replace the initial active view with the create new user / load user from file screen.
        viewManagerModel.setActiveView(initialAppLaunchView.viewName);
        viewManagerModel.firePropertyChanged();

        // Pack the application and center the frame to the screen
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}