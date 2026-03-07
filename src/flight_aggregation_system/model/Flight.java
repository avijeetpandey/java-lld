package flight_aggregation_system.model;

public class Flight {
    private final String id;
    private final String airline;
    private final double price;
    private final int durationMinutes;

    public Flight(String id, String airline, double price, int durationMinutes) {
        this.id = id;
        this.airline = airline;
        this.price = price;
        this.durationMinutes = durationMinutes;
    }

    public double getPrice() {
        return price;
    }

    public int getDurationInMinutes() {
        return durationMinutes;
    }

    public String getAirline() {
        return airline;
    }

    @Override
    public String toString() {
        return "Flight [id=" + id + ", airline=" + airline + ", price=" + price + ", durationMinutes=" + durationMinutes
                + ", getPrice()=" + getPrice() + ", getDurationInMinutes()=" + getDurationInMinutes()
                + ", getAirline()=" + getAirline() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}
