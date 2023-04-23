
package src.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDatabase {

    private LogData logData = new LogData();

    /**
     * Connects to the database and
     * prints connected if successful.
     * Returns a connection that can be used to send statements to the database.
     */
    public Connection getUserDatabaseConnection() {

        String url = logData.getUrl();
        String user = logData.getUserName();
        String password = logData.getPassword();
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

}