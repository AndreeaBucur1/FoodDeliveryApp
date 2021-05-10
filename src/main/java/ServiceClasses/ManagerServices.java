package ServiceClasses;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagerServices {
    public void managerServices(){
        CreateObjects createObjects = new CreateObjects();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        System.out.println("Enter the access token:");
        Scanner scanner = new Scanner(System.in);
        int accessToken = scanner.nextInt();
        System.out.println("Enter your password:");
        String password = scanner.next();
        if(!(accessToken == 100 && password.compareTo("manager") == 0)){
            System.out.println("Failed attempt to connect as manager");
        }
        else {
            System.out.println("Choose an option:");
            System.out.println();
            System.out.println("Option 1: Add new product");
            System.out.println("Option 2: Add new category");
            System.out.println("Option 3: Exit");
            int option;
            do {
                System.out.print("Enter the number of the option: ");
                option = scanner.nextInt();
                if(option == 2){
                    try {
                        createObjects.addCategory();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

            }while(option!=3);



        }

    }
}
