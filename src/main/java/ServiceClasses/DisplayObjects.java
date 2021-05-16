package ServiceClasses;

import Database.DatabaseConnection;
import Classes.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayObjects {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    public void displayCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        try {
            categories = databaseConnection.getAllCategories();
            for(Category category : categories){
                System.out.println(category.getCategoryId() + ": " + category.getCategoryName());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void displayProducts(ArrayList<Product> products){
        for(Product product : products){
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice() + " " + product.getDescription());
        }
    }

}
