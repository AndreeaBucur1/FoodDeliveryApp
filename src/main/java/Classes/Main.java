package Classes;

import Database.DatabaseConnection;
import ServiceClasses.CreateObjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        int option;
        boolean validOption = false;
        do {
            System.out.println("Choose an option");
            System.out.println();
            System.out.println("Option 1: Connect to your account");
            System.out.println("Option 2: Connect as manager");
            System.out.println("Option 3: Create account");
            System.out.println("Option 4: Exit");
            System.out.println();

            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();
            if (option == 1) {
                validOption = true;
            } else if (option == 2) {
                validOption = true;
            }
            else if(option == 3){
                validOption = true;
                CreateObjects createObjects = new CreateObjects();
                try {
                    createObjects.createAccount();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(option == 4){
                validOption = true;
            }

        } while (option != 4);


    }


}

