package product_listing.comparators;

import java.util.Comparator;

import product_listing.enums.SortBy;
import product_listing.models.Property;

public class PropertyComparator {
    private final SortBy sortBy;

    public PropertyComparator(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public Comparator<Property> getComparator() {
        Comparator<Property> comparator = switch (sortBy) {
            case PRICE_ASC -> Comparator.comparingDouble(Property::getPrice);
            case PRICE_DESC -> Comparator.comparingDouble(Property::getPrice).reversed();
            case ROOMS_ASC -> Comparator.comparingInt(Property::getRooms);
            case ROOMS_DESC -> Comparator.comparingInt(Property::getRooms).reversed();
        };

        return comparator;
    }
}
