package Database;

import Classes.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static Connection connection = null;

    public Connection Connection() {
        if (connection != null) {
            return connection;
        } else {
            try {
<<<<<<< Updated upstream
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodDeliveryApp", "root", "**");
=======
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp", "root", "root");
>>>>>>> Stashed changes
                return connection;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            return null;
        }
    }

    public ArrayList<Account> getAllAccounts() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from account");
        ArrayList<Account> accounts = new ArrayList<>();
        while (resultSet.next()){
            Account account = new Account(resultSet.getInt("accountId"),resultSet.getString("email"),resultSet.getString("password"));
            accounts.add(account);
        }
        return accounts;
    }

    public ArrayList<Category> getAllCategories() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category");
        ArrayList<Category> categories = new ArrayList<>();
        while (resultSet.next()){
            Category category = new Category(resultSet.getInt("categoryId"),resultSet.getString("categoryName"));
            categories.add(category);
        }
    return categories;
    }

    public ArrayList<Product> getAllProducts() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        ArrayList<Product> products = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getString(5));
            products.add(product);
        }
        return products;
    }

    public ArrayList<Product> getAllProductsByCategoryId(int categoryId) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product where categoryId = " + categoryId);
        ArrayList<Product> products = new ArrayList<>();
        while (resultSet.next()){
            Product product = new Product(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getString(5));
            products.add(product);
        }
        return products;
    }

    public Category getCategoryById(int categoryId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category where categoryId = " + categoryId);
        if(resultSet.next()){
            Category category = new Category(resultSet.getInt(1),resultSet.getString(2));
            return category;
        }
        else{
            return null;
        }
    }

    public Product getProductById(int productId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product where productId = " + productId);
        if(resultSet.next()){
            Product product = new Product(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getString(5));
            return product;
        }
        else{
            return null;
        }
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders");
        ArrayList<Order> orders = new ArrayList<>();
        while(resultSet.next()){
            Order order = new Order(resultSet.getInt(1),resultSet.getInt(2),resultSet.getFloat(3),resultSet.getString(4),resultSet.getDate(5).toLocalDate());
            orders.add(order);
        }
        return orders;
    }

    public ArrayList<Order> getOrdersByClientId (int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from orders where clientId = " + id);
        ArrayList<Order> orders = new ArrayList<Order>();
        while (resultSet.next()) {
            Order order = new Order(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5).toLocalDate());
            orders.add(order);
        }
        for (Order o : orders) {
            resultSet = statement.executeQuery("select productId, quantity from orderhasproducts where orderId = " + o.getOrderId());
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                Product product = getProductById(resultSet.getInt(1));
                int quantity = resultSet.getInt(2);
                while (quantity > 0) {
                    products.add(product);
                    quantity--;
                }
            }
            o.setProducts(products);

        }
        return orders;

    }

    public Integer getNumberOfOrders(int clientId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(orderId) from orders where clientId = " + clientId);
        Integer numberOfOrders = resultSet.getInt(1);
        return numberOfOrders;
    }

    public ArrayList<Cart> getAllCarts() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from cart");
        ArrayList<Cart> carts = new ArrayList<>();
        while(resultSet.next()){
            Cart cart = new Cart(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDouble(3));
            carts.add(cart);

        }
        return carts;
    }

    public Cart getCartByClientId (int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from cart where clientId = " + id);
        double totalPrice = 0;
        if(resultSet.next()){
            Cart cart = new Cart(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDouble(3));
            resultSet = statement.executeQuery("select productId, quantity from carthasproducts where cartId = " + cart.getCartId());
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next())
            {
                Product product = getProductById(resultSet.getInt(1));
                totalPrice += product.getPrice();
                int quantity = resultSet.getInt(2);
                while(quantity>0) {
                    products.add(product);
                    quantity--;
                }
            }
            cart.setProducts(products);
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        else{
            return null;
        }

    }
    public Cart getCartById (int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from cart where cartId = " + id);
        if(resultSet.next()){
            Cart cart = new Cart(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDouble(3));
            return cart;
        }
        else{
            return null;
        }

    }

}
