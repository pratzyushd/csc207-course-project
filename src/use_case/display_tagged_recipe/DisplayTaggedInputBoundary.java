package use_case.display_tagged_recipe;

public interface DisplayTaggedInputBoundary {
        /**
         * Executes the use case
         * @param input the user and tag whose recipes we want to display.
         */
        void execute(DisplayTaggedInputData input);
}