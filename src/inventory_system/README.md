## Inventory system
Develop a program to manage the inventory for an e-commerce company. In this e-commerce website once the user initiates his payment, inventory will be blocked for him for some time (say 5min) If the user finishes the payment and comes back within 5min the blocked inventory will be allocated to him else the inventory will be released back into the app for everyone else to order. To support the above features implement the following methods. Store all the data in memory and appropriate data structures. Write modular and thread-safe code.


Methods to implement:

1. create product with given productid, name and inventory count
createProduct(String productld, String name, Integer count)

 

2. return the available quantity for given product
getinventory(String productid)


3. Will be called when the user initiates payment for an order. Block the inventory for the given product and for the given order reference for 5min
blockinventory(String productld, Integer count, String orderld)
 

4. Will be called when the user completes payment for his order. Reduce the ordered quantity permanently for the product corresponding to given orderld. If this method is not called within 5min from blockInventory, inventory should be released back.
confirmOrder(String orderld)