package SOLID.OpenClosePrinciple;

import SOLID.SingleResponsibilityPrinciple.Invoice;

/**
 * Here the class is saving the invoice to database,
 * but let's say I want to save this file to disk now ,
 * then I will be modifying the code , which violates open close principle.
 */
public class BadExampleForOpenClose {
    private Invoice invoice;

    public BadExampleForOpenClose(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        System.out.println("Save to DB");
    }

    public void saveToFile() {
        System.out.println("Save to file");
    }
}
