package product_listing.services;

import product_listing.models.User;
import product_listing.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String id, String name) {
        User user = new User(id, name);
        userRepository.save(user);
        return user;
    }
}
