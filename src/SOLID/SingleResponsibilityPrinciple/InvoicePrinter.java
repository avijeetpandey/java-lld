package SOLID.SingleResponsibilityPrinciple;

public class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    // print
    public void print() {
        System.out.println("print invoice",);
    }
}
