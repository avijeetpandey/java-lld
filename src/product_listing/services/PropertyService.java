package product_listing.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import product_listing.comparators.PropertyComparator;
import product_listing.enums.PropertyStatus;
import product_listing.enums.SortBy;
import product_listing.exceptions.InvalidStateException;
import product_listing.exceptions.PropertyNotFoundException;
import product_listing.exceptions.UserNotFoundException;
import product_listing.models.Property;
import product_listing.models.SearchCriteria;
import product_listing.models.User;
import product_listing.repository.PropertyRepository;
import product_listing.repository.UserRepository;

public class PropertyService {
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public PropertyService(UserRepository userRepository, PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public void listProperty(String userId, String name, double price, int rooms) {
        // check if user exisits
        User user = userRepository.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        Property property = new Property("PROP1", "Grandeur park", 2500, 3, PropertyStatus.AVAILABLE);
        propertyRepository.save(property);

        System.out.println("Property with id " + property.getId() + "registered successfully");
    }

    public List<Property> getAllListedProperties() {
        return propertyRepository.getAllProperties();
    }

    public void markAsSold(String propertyId) {
        Property property = propertyRepository.get(propertyId);

        if (property == null) {
            throw new PropertyNotFoundException("Property not found");
        }
        property.getLock().lock();
        try {
            if (property.getStatus() == PropertyStatus.SOLD) {
                throw new InvalidStateException("Property is already sold");
            }

            property.setPropertyStatus(PropertyStatus.SOLD);
            System.out.println("Property with id " + propertyId + " sold");
        } finally {
            property.getLock().unlock();
        }
    }

    public void shortlistProperty(String userId, String propertyId) {
        // check for user
        // lock that property so that it cant be sold instantly
        // add to shortlist
        User user = userRepository.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        Property property = propertyRepository.get(propertyId);
        if (property == null) {
            throw new PropertyNotFoundException("Property not found");
        }

        property.getLock().lock();

        try {
            if (property.getStatus() != PropertyStatus.SOLD) {
                user.addShortlistedProperty(propertyId);
                System.out.println("Property shortlisted");
            } else {
                throw new InvalidStateException("Unable to shortlist property");
            }
        } finally {
            property.getLock().unlock();
        }
    }

    public List<Property> searchProperties(SearchCriteria criteria, SortBy sortBy) {
        // fetch all the list of the available properties
        Stream<Property> availableProperties = propertyRepository.getAllProperties()
                .stream()
                .filter(property -> property.getStatus() == PropertyStatus.AVAILABLE);

        // filter by search criteria

        if (criteria.minPrice != null)
            availableProperties = availableProperties.filter(p -> p.getPrice() >= criteria.minPrice);
        if (criteria.maxPrice != null)
            availableProperties = availableProperties.filter(p -> p.getPrice() <= criteria.maxPrice);
        if (criteria.minRooms != null)
            availableProperties = availableProperties.filter(p -> p.getRooms() >= criteria.minRooms);
        if (criteria.maxRooms != null)
            availableProperties = availableProperties.filter(p -> p.getRooms() >= criteria.maxRooms);

        // sort by
        PropertyComparator propertyComparator = new PropertyComparator(sortBy);
        return availableProperties.sorted(propertyComparator.getComparator()).collect(Collectors.toList());
    }
}
