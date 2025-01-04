package SOLID.OpenClosePrinciple;

import SOLID.SingleResponsibilityPrinciple.Invoice;

// Implementing the `DataDao` interface now the invoice can be saved to file as well
public class FileDataDao implements DataDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Save to file");
    }
}
