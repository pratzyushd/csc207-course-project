package entity;

public class CommonUserFactory implements UserFactory {
    /**
     * Create and return an instance of CommonUser.
     * @param username an alphanumeric username assigned by the user.
     * @param password the password the user will use with their username to log in.
     */
    @Override
    public User create(String username, String password) {
        return new CommonUser(username, password);
    }
}