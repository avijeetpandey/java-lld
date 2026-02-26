package flash_sale_order_system.repository;

import java.util.HashMap;
import java.util.Map;

import flash_sale_order_system.exceptions.DuplicateUserException;
import flash_sale_order_system.models.User;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public String save(User user) {
        if(users.containsKey(user.getId())) {
            throw new DuplicateUserException("User already registered");
        } else {
            users.put(user.getId(), user);
            return user.getId();
        }
    }

    public User get(String id) {
        return users.get(id);
    }
}
