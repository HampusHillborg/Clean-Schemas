package src.Controller;

import src.Boundary.LoginViewerGUI;
import src.Database.ConnectToDatabase;
import src.Database.UserDatabase;
import src.Database.UserDatabaseOutput;
import src.Entity.Profile;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    private ConnectToDatabase connect = new ConnectToDatabase();
    private Connection conn;
    private UserDatabase userDatabase;
    private String username;
    private Profile loggedInUser;
    private UserDatabaseOutput databaseOutput;

    public LoginController() {
        // Initialize database connection
        userDatabase = new UserDatabase();
        databaseOutput = new UserDatabaseOutput();

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

    public Profile getLoggedInUser(String username){
        int userId = userDatabase.getUserId(username);
        String password = databaseOutput.getPassword(userId);
        Double height = databaseOutput.getHeight(userId);
        Double weight= databaseOutput.getCurrentWeight(userId);
        int age = databaseOutput.getAge(userId);
        String sex = databaseOutput.getSex(userId);
        String goal = databaseOutput.getGoal(userId);
        String activityValue = databaseOutput.getActivityValue(userId);
        String carbAmount = databaseOutput.getCarbs(userId);
        int mealsPerDay = databaseOutput.getMealsPerDay(userId);
        this.loggedInUser = new Profile(username, password);
        loggedInUser.addToProfile(height, weight, age, sex, goal, activityValue, carbAmount,mealsPerDay);
        return loggedInUser;
    }


}
