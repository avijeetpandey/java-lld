package SOLID.OpenClosePrinciple;

import SOLID.SingleResponsibilityPrinciple.Invoice;

// this interface describes the functionality to be inmplemented
public interface DataDao {
    public void save(Invoice invoice);
}
