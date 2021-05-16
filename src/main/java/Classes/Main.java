package Classes;

import Database.DatabaseConnection;
import ServiceClasses.ClientServices;
import ServiceClasses.CreateObjects;
import ServiceClasses.ManagerServices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
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

                ClientServices clientServices = new ClientServices();
                Account account = clientServices.connectToAccount();
                if(account == null){
                    System.out.println("Failed attempt to connect to your account");
                }
                else {
                    clientServices.clientServices(account);
                }

            } else if (option == 2) {
                ManagerServices managerServices = new ManagerServices();
                managerServices.managerServices();
            }
            else if(option == 3){

                CreateObjects createObjectss = new CreateObjects();
                try {
                    createObjectss.createAccount();
                } catch (SQLException throwables) {
                    System.out.println("This account already exists");
                }
            }
            else if(option == 4){
                break;
            }

        } while (option != 4);


    }


}

