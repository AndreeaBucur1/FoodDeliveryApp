package ServiceClasses;

import Database.DatabaseConnection;
import Classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerServices {
    public void managerServices() throws SQLException {
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
            System.out.println("Option 3: Review employments forms");
            System.out.println("Option 4: Exit");
            int option;
            do {
                System.out.print("Enter the number of the option: ");
                option = scanner.nextInt();
                if(option == 1){
                    try {
                        createObjects.addProduct();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(option == 2){
                    try {
                        createObjects.addCategory();
                    } catch (SQLException throwables) {
                        System.out.println("This category already exists");
                    }
                }
                if ( option == 3)
                {
                    reviewForms();
                }

            }while(option!=4);

        }

    }

    public void reviewForms() throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Scanner scanner1 = new Scanner(System.in);
        ArrayList<Form> forms = databaseConnection.getAllForms();
        for ( Form form : forms)
        {
            System.out.println(form.toString());
        }
        System.out.println("Enter the id of the form you want to accept/reject :");
        int idForm = scanner1.nextInt();
        String answer = " ";
        while ( !answer.equals("Accept")  && !answer.equals("Reject") ) {
            System.out.println(" Accept or Reject ? (Insert a valid answer)");
            answer = scanner1.next();
        }
        if ( answer.equals("Accept") )
        {
            Form form = databaseConnection.getFormyById(idForm);
            String sql = "INSERT INTO employee (employeeId, lastName, firstName, age, email) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, form.getClientId());
            statement.setString(2, form.getNume());
            statement.setString(3, form.getPrenume());
            statement.setInt(4, form.getVarsta());
            statement.setString(5, form.getEmail());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" The employee has been accepted! ");
            }

            sql = "DELETE FROM form WHERE idForm =" + idForm;

            statement = connection.prepareStatement(sql);
            statement.executeUpdate();

        }
        else if ( answer.equals("Reject") )
        {
            String sql = "DELETE FROM form WHERE idForm =" + idForm;

            PreparedStatement statement = connection.prepareStatement(sql);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The form has been rejected!");
            }
        }

    }
}
