package questions;

import java.sql.*;
import java.util.List;

public class DBHandler {
    //  Utilizing the Transformer, insert into Database
        /*
        Database Schema:
        CREATE TABLE batch
        (
            batchId       INT IDENTITY,
            accountNumber VARCHAR(30),
            invoiceNumber VARCHAR(30),
            firstName     VARCHAR(30),
            lastName      VARCHAR(30),
            email         VARCHAR(75),
            homePhone     VARCHAR(10),
            address1      VARCHAR(250),
            address2      VARCHAR(250),
            city          VARCHAR(50),
            state         CHAR(2),
            postalCode    VARCHAR(5)
        );

         */
    public void insertBatchRecordIntoDB(List<BatchRecord> batchRecordList) {
        String insertSql = "INSERT INTO batch (accountNumber, invoiceNumber, firstName, lastName, email, homePhone, address1, address2, city, state, postalCode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        /*
        Try with resource used to ensure connection and preparedstatement are automatically closed once no longer in use.
        Alternatively, a finally block could be used to check if the resources are still open then close them.
        Try with resource always a better option.
         */
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSql)) {

            for (BatchRecord batchRecord : batchRecordList) {
                statement.setString(1, batchRecord.getAccountNumber());
                statement.setString(2, batchRecord.getInvoiceNumber());
                statement.setString(3, batchRecord.getFirstName());
                statement.setString(4, batchRecord.getLastName());
                statement.setString(5, batchRecord.getEmail());
                statement.setString(6, batchRecord.getHomePhone());
                statement.setString(7, batchRecord.getAddress1());
                statement.setString(8, batchRecord.getAddress2());
                statement.setString(9, batchRecord.getCity());
                statement.setString(10, batchRecord.getState());
                statement.setString(11, batchRecord.getPostalCode());
                statement.addBatch();
            }
            /*
            executed a batch insert to prevent database round trip.
            Batch inserts are proven to better performing operation than atomic inserts.
             */
            statement.executeBatch();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }


    /*
    Not required to reformat this, it is not designed to work just to not throw errors.
     */
    private static Connection getConnection() throws SQLException {
        return new ConnectionBuilder() {
            /*
            Returning the instance of this class to prevent null pointer exception
             */
            @Override
            public ConnectionBuilder user(String username) {
                return this;
            }

            @Override
            public ConnectionBuilder password(String password) {
                return this;
            }

            @Override
            public ConnectionBuilder shardingKey(ShardingKey shardingKey) {
                return this;
            }

            @Override
            public ConnectionBuilder superShardingKey(ShardingKey superShardingKey) {
                return this;
            }

            @Override
            public Connection build() throws SQLException {
                /*
                Provided dummy connection configuration to prevent a null pointer exception.
                Real db configuration should be provided for configuration code.
                 */
                return DriverManager.getConnection("jdbc:yourdatabaseurl", "test", "password");

            }
        }.user("test").password("password").build();
    }


}
