Scenario
You are tasked with developing an Online Shopping System using Java 17 concepts. The system should support managing products, customers, and orders. Implement the system using the following requirements:

Java 17 Features to use
Sealed Classes: Use sealed classes to ensure that only the specified types can be created.
Records: Use records to represent Product, Customer, and Order entities, ensuring immutability and ease of use.
Pattern Matching: Implement pattern matching to handle different operations like checking out orders.
Switch Expressions: Use switch expressions for flow control and decision making.
Text Blocks: Utilize text blocks for any multiline strings or data output.
Functionality:
Create sealed classes for Product, Customer, and Order.
Implement methods for operations such as:
Adding products to the inventory.
Displaying available products.
Allowing a customer to place an order.
Calculating the total price of an order.
Displaying customer information and their orders.
Use pattern matching to handle order processing and total price calculation.
Demonstrate switch expressions for decision making logic, such as applying discounts based on    customer type.
Objectives
Options to be displayed as given below. Please use the exact inputs as given in the objectives for every option, in the same order and display the exact message after ever successful options selection.

Add product to inventory: Users can add new products to the inventory by providing details such as Enter product name, Enter product price, and Enter product quantity. Message to be displayed(Product added to inventory successfully).
Display available products: The system can display a list of available products in the inventory just by the selection of option. Message to be displayed(Available Products).
Add customer: Users can add new customers to the system by providing their name and email address. Message to be displayed(Customer added successfully).
Place an order: Customers can place orders by specifying the customer email, selecting a product from the available list, and specifying the quantity. Message to be displayed(Order placed successfully).
Calculate total price of an order: The system calculates the total price of an order based on the customer email and product name. Message to be displayed(Total price of the order).
Display customer information and orders: Users can view customer information and their corresponding orders by providing the customer's email address. Message to be displayed(Orders placed by this customer).
Exit: The system provides an option to exit the program.