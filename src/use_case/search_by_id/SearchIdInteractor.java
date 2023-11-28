package use_case.search_by_id;

import entity.Recipe;

/**
 * Implements the search by id use case. Accesses the DAO to retrieve the relevant recipe
 * and formats the output data. 
 */
public class SearchIdInteractor implements SearchIdInputBoundary {
    private final SearchIdUserDataAccessInterface userDataAccessObject;
    private final SearchIdOutputBoundary searchIdPresenter;

    /**
     * Creates an instance of the SearchIdInteractor with its corresponding DAO and presenter.
     * @param userDataAccessObject the DAO that retrieves the recipe relevant to the given id.
     * @param searchIdPresenter the presenter that modifies the view based on the data retrieved by the DAO.
     */
    public SearchIdInteractor(SearchIdUserDataAccessInterface userDataAccessObject,
                              SearchIdOutputBoundary searchIdPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.searchIdPresenter = searchIdPresenter;
    }

    @Override
    public void execute(SearchIdInputData searchIdInputData) {
        System.out.println("executing");
        /* Special case for ID of incorrect length.
         * This also helps us avoid any Exceptions created by trying to use the Integer.parseInt method
         * on a String that results in a value greater than 2^31, which is the max representable integer.
         */
        if (searchIdInputData.getId().length() != 5) {
            System.out.println("Malformed ID");
            searchIdPresenter.prepareFailView("ID is invalid.");
        }
        int id = Integer.parseInt(searchIdInputData.getId());
        Recipe recipe = userDataAccessObject.searchRecipesById(id);
        SearchIdOutputData outputData = new SearchIdOutputData(id, recipe);

        if (recipe == null) {
            System.out.println("null recipe");
            searchIdPresenter.prepareFailView("No recipe with given ID could be found.");
        } else {
            System.out.println("The interactor is preparing the success view");
            searchIdPresenter.prepareSuccessView(outputData);
        }
    }
}
