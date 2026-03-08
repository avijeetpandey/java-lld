package product_listing.models;

public class SearchCriteria {
    public final Double minPrice;
    public final Double maxPrice;
    public final Integer minRooms;
    public final Integer maxRooms;

    public SearchCriteria(Double minPrice, Double maxPrice, Integer minRooms, Integer maxRooms) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
    }
}
