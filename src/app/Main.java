package app;

import data_access.TheMealDB;
import entity.CommonRecipeFactory;
import interface_adapter.SearchByIdViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search_by_id.SearchIdUserDataAccessInterface;
import view.SearchByIdView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("MyRecipeMate");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        // 'views' will serve as a container for various "views" (other panels) that you can switch between
        // using the CardLayout.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        SearchByIdViewModel searchByIdViewModel = new SearchByIdViewModel();
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        SearchIdUserDataAccessInterface searchIdUserDataAccessObject = new TheMealDB(recipeFactory);

        SearchByIdView searchByIdView = SearchByIdUseCaseFactory.create(viewManagerModel, searchByIdViewModel,
                searchIdUserDataAccessObject);

        views.add(searchByIdView, searchByIdView.viewName);

        viewManagerModel.setActiveView(searchByIdView.viewName);
//        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}