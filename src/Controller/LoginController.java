package src.Controller;

import src.Boundary.LoginViewerGUI;
import src.Database.ConnectToDatabase;
import src.Database.UserDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    private ConnectToDatabase connect = new ConnectToDatabase();
    private Connection conn;
    private UserDatabase userDatabase;
    private String username;

    public LoginController() {

        // Initialize database connection
        conn = connect.getUserDatabaseConnection();
        userDatabase = new UserDatabase(conn);
    }

    public boolean checkIfRegistered(String email){
        try {
            return userDatabase.isEmailRegistered(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateLogin(String email, String password){
        this.username = email;
        return userDatabase.validateLogin(email, password);
    }
}
