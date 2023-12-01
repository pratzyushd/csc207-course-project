package entity;

public class CommonUserFactory implements UserFactory {
    @Override
    public User create(String username) {
        return new CommonUser(username);
    }
}