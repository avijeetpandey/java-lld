package product_listing;

import java.util.List;

import product_listing.enums.SortBy;
import product_listing.models.Property;
import product_listing.models.SearchCriteria;
import product_listing.repository.PropertyRepository;
import product_listing.repository.UserRepository;
import product_listing.services.PropertyService;
import product_listing.services.UserService;

public class ProductListingDriverCode {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        PropertyRepository propertyRepository = new PropertyRepository();

        PropertyService productService = new PropertyService(userRepository, propertyRepository);
        UserService userService = new UserService(userRepository);

        // register the users
        userService.registerUser("AV1", "Avijeet");

        // list the properties
        productService.listProperty("AV1", "Grandeur park", 3000, 4);
        productService.listProperty("AV1", "Grandeur park1", 4000, 6);
        productService.listProperty("AV1", "Grandeur park2", 7000, 3);
        productService.listProperty("AV1", "Grandeur park3", 9000, 2);
        productService.listProperty("AV1", "Grandeur park4", 1000, 1);
        productService.listProperty("AV1", "Grandeur park5", 2000, 4);
        productService.listProperty("AV1", "Grandeur park6", 4000, 8);


        // search the properties
        SearchCriteria criteria = new SearchCriteria(3000.0, 10000.0, 2, 4);
        
        List<Property> properties = productService.searchProperties(criteria, SortBy.PRICE_ASC);
        for(Property property: properties) {
            System.out.println(property.toString());
        }

        // short list the properties
        productService.shortlistProperty("AV1", "PP3");
        productService.shortlistProperty("AV1", "PP7");

        // sold the properties
        productService.markAsSold("PP7");
    }
}
