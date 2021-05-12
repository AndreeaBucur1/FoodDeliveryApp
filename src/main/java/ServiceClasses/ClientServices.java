package ServiceClasses;

import Classes.Account;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Stack;

public class ClientServices {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    Scanner scanner = new Scanner(System.in);
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

    public void clientServices(Account account){


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
            if(option == 4){
                DeleteObjects deleteObjects = new DeleteObjects();
                try {
                    deleteObjects.deleteAccount(account.getAccountId());
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            

        }while (option!=5);
    }
}
