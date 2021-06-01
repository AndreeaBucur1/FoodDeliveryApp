package ServiceClasses;

import Classes.*;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ClientServices {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    Scanner scanner = new Scanner(System.in);
    DisplayObjects displayObjects = new DisplayObjects();
    CreateObjects createObjects = new CreateObjects();
    UpdateObjects updateObjects = new UpdateObjects();
    DeleteObjects deleteObjects = new DeleteObjects();

    public Account connectToAccount() throws SQLException {
        System.out.print("Enter email: ");
        String email = scanner.next();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from account where email = '" + email + "'");
        if(resultSet.next() == false){
            System.out.println("Wrong email");
            return null;
        }
        else{
            Account account = new Account(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            System.out.print("Enter your password: ");
            String password = scanner.next();
            if(password.compareTo(account.getPassword()) != 0){
                System.out.println("Failed attempt to connect to the account");
                return null;
            }
            else {
                return account;
            }
        }

    }

    public void clientServices(Account account) throws SQLException {

        int option;
        do{
            System.out.println("Choose an option: ");
            System.out.println();
            System.out.println("Option 1: View products");
            System.out.println("Option 2: Place an order");
            System.out.println("Option 3: View all your orders");
            System.out.println("Option 4: Delete your account");
            System.out.println("Option 5: Change password");
            System.out.println("Option 6: Exit");
            option = scanner.nextInt();
            if(option == 1)
            {
                ArrayList<Product> products = databaseConnection.getAllProducts();
                for(Product p : products)
                    System.out.println(p);
            }
            if(option == 3) {
                ArrayList<Order> orders = databaseConnection.getOrdersByClientId(account.getAccountId());
                for (Order o : orders)
                    System.out.println(o);
            }
                if(option == 4){
                DeleteObjects deleteObjects = new DeleteObjects();
                try {
                    deleteObjects.deleteAccount(account.getAccountId());
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(option == 2){
                placeOrder(account.getAccountId());
            }
            else if (option == 5)
                {
                    System.out.println("Enter your new password");
                    String newPassword = scanner.next();
                    updateObjects.updatePassword(account.getAccountId(), newPassword);
                }
        }while (option!=6);
    }

    public void placeOrder(int clientId) throws SQLException {
        System.out.println("Choose a category: ");
        displayObjects.displayCategories();
        ArrayList<Product> products = new ArrayList<>();
        int categoryId;
        do {
            System.out.print("Enter the id of the category or -1 to exit: ");
            categoryId = scanner.nextInt();
            if(categoryId == -1){
                System.out.println("Done");
            }
            else{

                try {
                    if(databaseConnection.getCategoryById(categoryId) != null){
                        ArrayList<Product> newProducts = new ArrayList<>();
                        newProducts = chooseProducts(categoryId);
                        if(newProducts.size() > 0){
                            products.addAll(newProducts);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }while(categoryId != -1);

        if(products.size() == 0){
            System.out.println("You did not order any product");
        }
        else {
            Cart cart = databaseConnection.getCartByClientId(clientId);
            boolean isNew = false;
            if(cart == null) {
                cart = new Cart(clientId, 0);
                isNew = true;
            }
            cart.addProducts(products);
            double totalPrice = 0;
            for(Product product : products){
                totalPrice += product.getPrice();
            }
            int numberOfOrders = databaseConnection.getNumberOfOrders(clientId);
            if(numberOfOrders > 3)
                totalPrice = totalPrice - numberOfOrders / 100 * totalPrice;

            cart.setTotalPrice(cart.getTotalPrice() + totalPrice);
            if(isNew)
                createObjects.addCart(cart);
            else
                updateObjects.updateCart(cart);

            System.out.println("Place the order. Continue? (1.Yes 2.No)");
            int option = scanner.nextInt();
            if(option == 1)
                placeOrder(cart);
        }

    }

    public ArrayList<Product> chooseProducts(int categoryId) throws SQLException {
        ArrayList<Product> productsFromThisCategory = new ArrayList<>();
        productsFromThisCategory = databaseConnection.getAllProductsByCategoryId(categoryId);
        ArrayList<Product> orderedProducts = new ArrayList<>();
        displayObjects.displayProducts(productsFromThisCategory);
        int productId;
        do{
            System.out.print("Enter the id of the product or -1 to exit ");
            productId = scanner.nextInt();
            if(productId == -1){
                System.out.println("Done");
            }
            else {
                Product newProduct = databaseConnection.getProductById(productId);
                orderedProducts.add(newProduct);
            }

        }while(productId != -1);

        return orderedProducts;
    }
    public void placeOrder ( Cart cart) throws SQLException {
        System.out.println("Enter your adress: ");
        scanner.nextLine();
        String address = scanner.nextLine().toString();

        Order order = new Order(cart.getClientId(), cart.getProducts(), cart.getTotalPrice(),address, LocalDate.now());
        try {
            createObjects.addOrder(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        deleteObjects.deleteCart(cart);
    }

}
