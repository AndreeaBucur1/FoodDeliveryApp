package ServiceClasses;

import Classes.Cart;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteObjects {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();

    public void deleteAccount(int accountId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from account where accountId = " + accountId);
        preparedStatement.execute();

    }
    public void deleteCart(Cart cart) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cart WHERE cartId = ?;");
        preparedStatement.setInt(1, cart.getCartId());
        preparedStatement.execute();
    }
}
