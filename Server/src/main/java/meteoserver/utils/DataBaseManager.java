package meteoserver.utils;
import java.sql.*;

/**
 * Created by Zetro on 11.05.2015.
 */
public class DataBaseManager {
    private Connection connection;
    public DataBaseManager(String url, String userName, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        Connection connection = DriverManager.getConnection("jdbc:postgresql://" + url, userName, password);
        this.connection = connection;
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
    public Connection getConnection() {
        return connection;
    }
    public void setAutocommit(boolean autocommit) throws SQLException {
        connection.setAutoCommit(autocommit);
    }
    public void commit() throws SQLException {
        connection.commit();
    }
}