package entity;

public class CommonUserFactory implements UserFactory {
    /**
     * Create and return an instance of CommonUser.
     * @param username an alphanumeric username assigned by the user.
     */
    @Override
    public User create(String username) {
        return new CommonUser(username);
    }
}