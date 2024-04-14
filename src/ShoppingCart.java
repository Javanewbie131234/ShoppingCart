import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

sealed interface Entity permits Customer,Order,Product{}


record Customer (String customerName, String customerEmail) implements Entity{}
record Order (Customer customer, Product product, int quantity ) implements  Entity {}
record Product (String productName, double price , int quantity) implements Entity {}

public class ShoppingCart {
    private static final List<Entity> inventory = new ArrayList<>();
    private static final List<Customer> customer = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static Boolean flag = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("""
                    Menu:
                    1. Add product to inventory
                    2. Display available products
                    3. Add customer
                    4. Place an order
                    5. Calculate total price of an order
                    6. Display customer information and order
                    7. Exit           
                    
                    Enter your choice - """);
            int choice = scanner.nextInt();
            scanner.nextLine(); //consume newline
            switch (choice){
                case 1 -> addProduct(scanner);
                case 2 -> displayProduct(scanner);
                case 3 -> addCustomer(scanner);
                case 4 -> placeAnOrder(scanner);
                case 5 -> calculateTotalPrice(scanner);
                case 6 -> displayCustomerInfo(scanner);
                case 7 -> {
                    System.out.println("Exiting the menu");
                    flag = false;
                }
                default -> System.out.println("Invalid option, Try again");
            }
        }while(flag);
    }

    public static void addProduct(Scanner scanner){
        System.out.println("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Product Price");
        double price = scanner.nextDouble();
        System.out.println("Enter Product quantity");
        int quantity = scanner.nextInt();

        inventory.add(new Product(name , price, quantity));
        System.out.println("Product added to inventory successfully");



    }
    public static void displayProduct(Scanner scanner){
        System.out.println("Available Products");
        inventory.forEach( entity -> {
            if(entity instanceof Product){
                Product product = (Product)entity;
                System.out.println(product.productName());
            }
        });

    }
    public static void addCustomer(Scanner scanner){
        System.out.println("Enter customer name");
        String name = scanner.nextLine();
        if(!validateName(name)){
            System.out.println("Invalid name. Please enter valid name");
            return;
        }
        System.out.println("Enter customer email Id");
        String email = scanner.nextLine();
        if(!validateEmail(email)){
            System.out.println("Invalid email. Please enter valid email");
            return;
        }
        Customer addCustomer = new Customer(name,email);
        customer.add(addCustomer);
//        Use while to loop again

    }

    public static boolean validateName(String name){
        return !name.isBlank() && name.matches("[A-Za-z]+");
    }

    public static boolean validateEmail(String email){
        return email.contains("@");
    }

    public static void placeAnOrder(Scanner scanner){
        System.out.println("Enter customer email");
        String email = scanner.nextLine();
        if(!validateEmail(email)){
            System.out.println("Invalid Email");
        }
        Customer orderCustomer = findCustomerByName(email);

        if(orderCustomer == null){
            System.out.println("Customer not found");
            return;
        }

        System.out.println("Available Products: ");
        displayProduct(scanner);
        System.out.println("Select the product: ");

        String productName = scanner.nextLine();
        Product selectedProduct = findProductByName(productName);

        if(selectedProduct == null){
            System.out.println("product not found");
            return;
        }
        System.out.println("Enter quantity");
        int quantity = scanner.nextInt();

        Order order = new Order(orderCustomer,selectedProduct,quantity);
        orders.add(order);
        System.out.println("Order placed successfully");


    }

    public static Product findProductByName(String name){
        return (Product) inventory.stream()
                .filter(entity -> entity instanceof Product && ((Product) entity).productName().equals(name))
                .findFirst()
                .orElseThrow();

    }
    public static Customer findCustomerByName(String email){
        return customer.stream()
                .filter(customer -> customer.customerEmail().equals(email))
                .findFirst()
                .orElseThrow();

    }

    public static void calculateTotalPrice(Scanner scanner){
        System.out.println("Enter Customer email");
        String email = scanner.nextLine();
        Customer findcustomer = findCustomerByName(email);
        System.out.println("Enter Product: ");
        String productName = scanner.nextLine();
        Product findProduct = findProductByName(productName);

//        int quantity = findorder(findcustomer,findProduct);
        int totalprice =  orders.stream()
                .filter(order -> order.customer().equals(findcustomer) && order.product().equals(findProduct))
                .mapToInt(order -> order.quantity()* (int) findProduct.price())
                .sum();

        System.out.println("The price of the order is "+ totalprice);

    }

//    public static int findorder(Customer customer,Product product){
//        return orders.stream()
//                .filter(order -> order.customer().equals(customer) && order.product().equals(product))
//                .mapToInt(Order::quantity)
//                .sum();
//    }


    public static void displayCustomerInfo(Scanner scanner){
        System.out.println("Enter customer email");
        String email = scanner.nextLine();
        List<Order> orderplaced = orders.stream()
                .filter(order -> order.customer().customerEmail().equals(email))
//                .map(Order::product)
                .collect(Collectors.toList());
        System.out.println("orders placed by the customer ");
        orderplaced.forEach( order -> System.out.println(order.product().productName()));


    }



}
