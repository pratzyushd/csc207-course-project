package use_case.display_user_tags;

import java.util.List;

public class DisplayUserTagsOutputData {
        private final List<String> tags;
        /**
         * Creates an output data object with the given tags.
         * @param tags the tags to display.
         */
        public DisplayUserTagsOutputData(List<String> tags) { this.tags = tags; }
        public List<String> getUserTags() { return tags; }
}
