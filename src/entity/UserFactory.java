package entity;

public interface UserFactory {
    /**
     * Create and return an instance of CommonUser.
     * @param username an alphanumeric username assigned by the user.
     */
    User create(String username);
}