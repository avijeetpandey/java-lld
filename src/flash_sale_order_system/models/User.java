package flash_sale_order_system.models;

import java.util.UUID;

import flash_sale_order_system.enums.Role;


public class User {
    private final String id;
    private final String name;
    private final Role role;
    private final String email;

    // constructor
    public User(String id,String name, Role role, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
    }

    // getters
    public String getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String name() {
        return name;
    }
}
