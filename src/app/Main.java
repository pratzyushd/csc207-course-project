package app;

import data_access.JSONPersistence;
import data_access.Persistence;
import data_access.RecipeAPI;
import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.SearchResultViewModel;
import interface_adapter.favourite_recipe.FavouriteRecipeViewModel;
import interface_adapter.search_by_cuisine.SearchByCuisineViewModel;
import interface_adapter.search_by_id.SearchByIdViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_by_name.SearchByNameViewModel;
import interface_adapter.tag_recipe.TagRecipeViewModel;
import use_case.favourite_recipe.FavouriteRecipeUserDataAccessInterface;
import use_case.search_by_cuisine.SearchCuisineUserDataAccessInterface;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import use_case.search_by_name.SearchNameUserDataAccessInterface;
import use_case.tag_recipe.TagRecipeUserDataAccessInterface;
import view.*;

import view.HomeView;
import view.RecipesView;
import view.DisplayTagsView;
import interface_adapter.display_recipes.RecipesViewModel;
import interface_adapter.display_user_tags.UserTagsViewModel;
import view.DisplayFavouriteView;
import view.DisplayTaggedView;
import view.DisplayTagsView;
import use_case.display_favourite_recipe.DisplayFavouriteUserDataAccessInterface;
import use_case.display_tagged_recipe.DisplayTaggedUserDataAccessInterface;
import use_case.display_user_tags.DisplayUserTagsUserDataAccessInterface;

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

        // Create and add SearchById view
        SearchByIdViewModel searchByIdViewModel = new SearchByIdViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        SearchIdUserDataAccessInterface searchIdUserDataAccessObject = new TheMealDB(recipeFactory);
        SearchByIdView searchByIdView = SearchByIdUseCaseFactory.create(viewManagerModel, searchByIdViewModel,
                searchResultViewModel, searchIdUserDataAccessObject);
        views.add(searchByIdView, searchByIdView.viewName);

        // Create and add SearchByCuisine view
        SearchByCuisineViewModel searchByCuisineViewModel = new SearchByCuisineViewModel();
        SearchCuisineUserDataAccessInterface searchCuisineUserDataAccessObject = new TheMealDB(recipeFactory);
        SearchByCuisineView searchByCuisineView = SearchByCuisineUseCaseFactory.create(viewManagerModel, searchByCuisineViewModel,
                searchResultViewModel, searchCuisineUserDataAccessObject);
        views.add(searchByCuisineView, searchByCuisineView.viewName);

        // Create and add SearchByName view
        SearchByNameViewModel searchByNameViewModel = new SearchByNameViewModel();
        SearchNameUserDataAccessInterface searchNameUserDataAccessObject = new TheMealDB(recipeFactory);
        SearchByNameView searchByNameView = SearchByNameUseCaseFactory.create(viewManagerModel, searchByNameViewModel,
                searchResultViewModel, searchNameUserDataAccessObject);
        views.add(searchByNameView, searchByNameView.viewName);

        // Create and add SearchResultView (for favouriting recipes and adding tags)
        FavouriteRecipeViewModel favouriteRecipeViewModel = new FavouriteRecipeViewModel();
        TagRecipeViewModel tagRecipeViewModel = new TagRecipeViewModel();
        // TODO, tag and favourite recipe user DA interfaces are the same thing, can we do type casting and use just 1?
        FavouriteRecipeUserDataAccessInterface favouriteRecipeUserDataAccessInterface = new TheMealDB(recipeFactory);
        TagRecipeUserDataAccessInterface tagRecipeUserDataAccessInterface = new TheMealDB(recipeFactory);

        CommonUserFactory userFactory = new CommonUserFactory();
        RecipeAPI recipeAPI = new TheMealDB(recipeFactory);
        // TODO - provide jsonPersistence with a valid file path. Currently empty path.
        Persistence jsonPersistence = new JSONPersistence(userFactory, "", recipeAPI);
        SearchResultView searchResultView = SearchResultUseCaseFactory.create(viewManagerModel, favouriteRecipeViewModel,
                tagRecipeViewModel, searchResultViewModel, favouriteRecipeUserDataAccessInterface,
                tagRecipeUserDataAccessInterface, jsonPersistence);
//        JScrollPane scrollPane = new JScrollPane(searchResultView);
//        scrollPane.setViewportView(searchResultView);
//        views.add(scrollPane, searchResultView.viewName);
        // TODO - we currently only support up to 15 results. Try implementing scrolling in search result view?
        views.add(searchResultView, searchResultView.viewName);

        // Create and add DisplayFavouriteView
        RecipesViewModel DisplayFavouriteViewModel = new RecipesViewModel(true);
        DisplayFavouriteUserDataAccessInterface favouriteRecipeUserDataAccessInterface2 = new JSONPersistence(userFactory, "", recipeAPI);
        DisplayFavouriteView displayFavouriteView = DisplayFavouriteUseCaseFactory.create(viewManagerModel, DisplayFavouriteViewModel,
                favouriteRecipeUserDataAccessInterface2);
        views.add(displayFavouriteView, displayFavouriteView.viewName);

        // Create and add DisplayTaggedView
        RecipesViewModel DisplayTaggedViewModel = new RecipesViewModel(false);
        DisplayTaggedUserDataAccessInterface taggedRecipeUserDataAccessInterface = new JSONPersistence(userFactory, "", recipeAPI);
        DisplayTaggedView displayTaggedView = DisplayTaggedUseCaseFactory.create(viewManagerModel, DisplayTaggedViewModel,
                taggedRecipeUserDataAccessInterface);
        views.add(displayTaggedView, displayTaggedView.viewName);

        // Create and add DisplayTagsView
        UserTagsViewModel userTagsViewModel = new UserTagsViewModel();
        DisplayUserTagsUserDataAccessInterface userTagsUserDataAccessInterface = new JSONPersistence(userFactory, "", recipeAPI);
        DisplayTagsView displayTagsView = DisplayUserTagsUseCaseFactory.create(viewManagerModel, userTagsViewModel,
                userTagsUserDataAccessInterface);
        views.add(displayTagsView, displayTagsView.viewName);

        // Create and add RecipesView
        RecipesView recipesView = new RecipesView(viewManagerModel);
        views.add(recipesView, recipesView.viewName);

        // Create and add HomeView
        HomeView homeView = new HomeView(viewManagerModel);
        views.add(homeView, homeView.viewName);

        // Set the active view to start with search by id
        // TODO - replace the initial active view with the create new user / load user from file screen.
        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

        // Pack the application and center the frame to the screen
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}