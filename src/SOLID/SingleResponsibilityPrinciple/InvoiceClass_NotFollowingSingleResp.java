package SOLID.SingleResponsibilityPrinciple;

/**
 * In this class the class does not hold the single responsibility principle, as it has got multiple reasons to change
 * Example a change can be due to saving in database, can be due to calculation, can be due to printing
 */
public class InvoiceClass_NotFollowingSingleResp {
    private Marker marker;
    private int quantity;

    public InvoiceClass_NotFollowingSingleResp(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculateTotal() {
        return marker.price * quantity;
    }

    public void printInvoice() {
        System.out.println("Print invoice");
    }

    public void saveToDB() {
        System.out.println("Saving to database");
    }
}
