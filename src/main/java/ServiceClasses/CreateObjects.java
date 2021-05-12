package ServiceClasses;
import Database.DatabaseConnection;
import Classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateObjects {
    DisplayObjects displayObjects = new DisplayObjects();
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
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.execute();


    }

    public void addCategory() throws SQLException {
        System.out.print("Enter the name of the category you want to add: ");
        String categoryName = scanner.next();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into category (categoryname) values (?)");
        preparedStatement.setString(1, categoryName);
        preparedStatement.execute();

    }

    public void addProduct() throws SQLException {
        System.out.print("Enter the name of the product you want to add: ");
        String productName = scanner.nextLine().toString();
        System.out.println("This are all the categories:");
        displayObjects.displayCategories();
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        boolean validCategory = false;
        int categoryId;
        do {
            System.out.println("Enter -1 to abort");
            System.out.print("Enter the number of the category: ");
            categoryId = scanner.nextInt();
            System.out.println(categoryId);
            if(categoryId == -1){
                break;
            }
            resultSet = statement.executeQuery("select * from category where categoryId = " + categoryId);
            if (resultSet.next()) {
                validCategory = true;
            } else {
                System.out.println("Category does not exist");
            }
        } while (!validCategory);

        if(categoryId == -1){
            System.out.println("Canceled");
        }
        else{
            System.out.print("Enter the price: ");
            float price = scanner.nextFloat();
            System.out.print("Enter product description: ");
            scanner.nextLine();
            String description = scanner.nextLine().toString();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("insert into product (categoryId,productName,price,description) values (?,?,?,?)");
            preparedStatement.setInt(1,categoryId);
            preparedStatement.setString(2,productName);
            preparedStatement.setFloat(3,price);
            preparedStatement.setString(4,description);
            preparedStatement.execute();
        }
    }
}