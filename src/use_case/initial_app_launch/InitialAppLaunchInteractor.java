package use_case.initial_app_launch;

import entity.CommonUserFactory;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//todo edit javadoc
/**
 * Implements the display user favourite recipes use case.
 */
public class InitialAppLaunchInteractor implements InitialAppLaunchInputBoundary {
    private final InitialAppLaunchUserDataAccessInterface dataAccess;
    private final InitialAppLaunchOutputBoundary presenter;

    /**
     * Creates a new DisplayFavouriteInteractor object with the given data access, presenter and user.
     * @param dataAccess the data access object that retrieves the relevant recipes based on user.
     * @param presenter the presenter that will update the view with the relevant recipes.
     */
    public InitialAppLaunchInteractor(InitialAppLaunchUserDataAccessInterface dataAccess, InitialAppLaunchOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(InitialAppLaunchInputData input) {
        if (input.getLoad()){
            // Get file path from user
            String filePath = input.getFilePath();

            // Use the dataAccess.setFilePath(...)
            dataAccess.setFilePath(filePath);

            // Call dataAccess.load() to load the contents of the file.
            dataAccess.load();
            presenter.prepareSuccessView();
        }
        else{
            // setfilepath
            dataAccess.setFilePath(input.getFilePath());
            // make a new user object
            // call dataaccess.setuser()
            // call dataaccess.save(<user you created>)
            CommonUserFactory commonUserFactory = new CommonUserFactory();
            User user = commonUserFactory.create(input.getUsername());
            dataAccess.setUser(user);
            dataAccess.save(user);
            presenter.prepareSuccessView();
        }
    }
}