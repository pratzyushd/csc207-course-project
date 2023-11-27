package use_case.display_user_tags;

import java.util.List;

public class DisplayUserTagsOutputData {

        private final List<String> tags;

        public DisplayUserTagsOutputData(List<String> tags) { this.tags = tags; }

        public List<String> getUserTags() { return tags; }
}
