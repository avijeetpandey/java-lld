package product_listing.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import product_listing.models.Property;

public class PropertyRepository {
    private final Map<String, Property> properties = new ConcurrentHashMap<>();

    public void save(Property property) {
        properties.put(property.getId(), property);
    }

    public Property get(String id) {
        return properties.get(id);
    }

    public List<Property> getAllProperties() {
        return new ArrayList<>(properties.values());
    }
}
