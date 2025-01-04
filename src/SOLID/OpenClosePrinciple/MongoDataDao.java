package SOLID.OpenClosePrinciple;

import SOLID.SingleResponsibilityPrinciple.Invoice;

// Implementing the `DataDao` interface now the invoice can be saved to mondodb as well
public class MongoDataDao implements DataDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Save to mongodb");
    }
}
