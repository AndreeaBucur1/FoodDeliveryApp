package ServiceClasses;

import Classes.Cart;
import Classes.Product;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateObjects {

    DisplayObjects displayObjects = new DisplayObjects();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    Scanner scanner = new Scanner(System.in);
    CreateObjects createObjects = new CreateObjects();

    public void updateCart(Cart cart) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("update cart set totalPrice =" + cart.getTotalPrice() + "where cartId = " + cart.getCartId());
        preparedStatement.execute();
        createObjects.addProductsToCart(cart);
    }
}
