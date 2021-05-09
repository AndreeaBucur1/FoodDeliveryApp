package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
