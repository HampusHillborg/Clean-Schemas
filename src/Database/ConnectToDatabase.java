package src.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDatabase {


    /**
     * Connects to the database and
     * prints connected if successful.
     * Returns a connection that can be used to send statements to the database.
     */
    public Connection getUserDatabaseConnection() {

        String url = "jdbc:postgresql://pgserver.mau.se:5432/cleanschemas";
        String user = "an6020";
        String password = "te36iale";
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
