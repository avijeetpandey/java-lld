
Meesho Coupon System
Description 

Design and implement a Coupon Management System for Meesho's e-commerce platform. The system should handle product purchases, coupon applications, and payment processing. The goal is to create an efficient and user-friendly system that encourages purchases through strategic discount offerings.

Features Required:

Users can browse available products with their prices

Users can add multiple items to their cart

Users can view available coupons for their purchase

Coupons should have an upper limit if giving a percentage discount.

Users can apply a coupon to their cart

System should validate if the coupon is:
Not expired

Applicable to the current purchase amount

Not already used by the user

System should calculate final payment amount after coupon application

Users can complete the purchase with the discounted amount

Users can view their purchase history with applied coupons

Users can remove items from cart

System should maintain coupon usage history


Entities:

User: <username, used_coupons>
Product: <product_id, name, price>
Coupon: <coupon_code, discount_type(FLAT/PERCENTAGE), discount_value(or max_discount_value if percentage based), min_purchase_value, expiry_date>
Cart: <cart_id, username, items(list of Products with count>, applied_coupon, total_amount, final_amount>
Order: <order_id, cart>
Expected Deliverables:

Working code implementation with proper class structure
Basic test cases covering main scenarios
Proper error handling
Clean and maintainable code
 

Guidelines:

Focus on object-oriented design
Consider scalability in your design
Use appropriate data structures
Handle edge cases
You can use in-memory data structures instead of actual databases
Input/Output Format:

Input: ADD_PRODUCT <product_id> <name> <price> <quantity>

Output: Product <product_id> added successfully

 

Input Format 1: CREATE_COUPON <coupon_code> <discount_type: percentage> <percentage> <max_discount_value> <min_purchase_value> <expiry_date>

Output: Coupon <coupon_code> created successfully

 

Input Format 2: CREATE_COUPON <coupon_code> <discount_type: flat> <discount_value> <min_purchase_value> <expiry_date>

Output: Coupon <coupon_code> created successfully

 

 

Input: ADD_TO_CART <cart_id> <product_id> <quantity>

Output Format 1 (if successful): Products added to cart

Output Format 2 (if quantity exceeds stock): Error: Requested quantity exceeds available stock

 

Input: APPLY_COUPON <cart_id> <coupon_code>

Output Format 1 (if successful):Original Amount: ₹<original_amount>Discount Applied: ₹<discount_amount>Final Amount: ₹<final_amount>

Output Format 2 (if coupon expired): Error: Coupon has expired

Output Format 3 (if minimum purchase value not met): Error: Minimum purchase amount not met. Required: ₹<min_purchase_value>

 

Input: VIEW_CART <cart_id>

Output:

Cart ID: <cart_id>

1. Product: <product_name>, Quantity: <quantity>, Price: ₹<price>

Total Amount: ₹<total_amount>

 

Input: COMPLETE_PURCHASE <username> <cart_id>

Output: Order placed successfully

 

Sample input

ADD_PRODUCT P1 Tshirt 1000 50
ADD_PRODUCT P2 Jeans 2000 30
CREATE_COUPON SUMMER30 percentage 30 500 1500 2024-12-31
CREATE_COUPON FLAT200 flat 200 1000 2024-12-31
ADD_TO_CART CART1 P1 2
ADD_TO_CART CART1 P2 1
APPLY_COUPON CART1 SUMMER30
COMPLETE_PURCHASE user1 CART1
Sample output

Product added successfully
Product added successfully
Coupon created successfully
Coupon created successfully
Products added to cart
Products added to cart
Original Amount: ₹4000
Discount Applied: ₹500
Final Amount: ₹3500
Order placed successfully


