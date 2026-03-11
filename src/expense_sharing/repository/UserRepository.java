package expense_sharing.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import expense_sharing.models.User;

public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
