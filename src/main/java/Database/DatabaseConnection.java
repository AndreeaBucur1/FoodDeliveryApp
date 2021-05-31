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
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodDeliveryApp", "root", "Activitate14");
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
        ResultSet resultSet = statement.executeQuery("select * from cart where cleintId = " + id);
        if(resultSet.next()){
            Cart cart = new Cart(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDouble(3));
            return cart;
        }
        else{
            return null;
        }

    }

}
