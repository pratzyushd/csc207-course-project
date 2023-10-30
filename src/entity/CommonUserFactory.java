package entity;

public class CommonUserFactory implements UserFactory {
    /**
     * @param name
     * @param password
     */

    @Override
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }
}