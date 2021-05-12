package ServiceClasses;

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
}
