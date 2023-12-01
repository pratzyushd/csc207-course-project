package interface_adapter.tag_recipe;

public class TagRecipeState {
    private String tagRecipeMessage = null;

    public TagRecipeState(TagRecipeState copy) {
        tagRecipeMessage = copy.tagRecipeMessage;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public TagRecipeState() {}

    public String getTagRecipeMessage() {
        return tagRecipeMessage;
    }

    public void setTagRecipeMessage(String tagRecipeMessage) {
        this.tagRecipeMessage = tagRecipeMessage;
    }
}
