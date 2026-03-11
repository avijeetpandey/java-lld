package expense_sharing.models;

public class User {
    private final String id;
    private final String name;
    private final String email;
    private final long mobile;

    public User(String id, String name, String email, long mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getMobile() {
        return mobile;
    }
}
