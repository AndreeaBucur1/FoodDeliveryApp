package Database;

import Classes.Account;
import Classes.Category;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static Connection connection = null;

    public Connection Connection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodDeliveryApp", "root", "root");
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
}
