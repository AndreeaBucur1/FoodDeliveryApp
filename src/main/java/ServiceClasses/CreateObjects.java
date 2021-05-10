package ServiceClasses;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateObjects {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    Scanner scanner = new Scanner(System.in);
    public void createAccount() throws SQLException {
        System.out.print("Enter your email adress: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        System.out.println();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into account (email,password) values (?,?)");
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        preparedStatement.execute();


    }
}
