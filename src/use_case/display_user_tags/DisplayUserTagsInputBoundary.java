package use_case.display_user_tags;

public interface DisplayUserTagsInputBoundary {

    /**
     * Executes the use case
     * An empty DisplayUserTagsInputData object is passed as parameter to remain consistent with Clean Architecture and
     * remain extensible in the future even though input data is not needed for this use case.
     */
    void execute(DisplayUserTagsInputData input);
}
