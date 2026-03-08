package product_listing.models;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String id;
    private final String name;
    private final Set<String> shortlistedPropertyIds;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.shortlistedPropertyIds = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addShortlistedProperty(String propertyId) {
        shortlistedPropertyIds.add(propertyId);
    }
}
