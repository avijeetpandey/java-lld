package product_listing.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import product_listing.models.User;

public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public User get(String id) {
        return users.get(id);
    }
}
