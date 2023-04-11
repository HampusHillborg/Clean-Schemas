package src.Controller;


import src.Boundary.LoginViewerGUI;
import src.Boundary.ProfileFormGUI;
import src.Entity.Profile;
import src.Database.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class  RegistrationController {
    private LoginViewerGUI gui;
    private Connection conn;
    private MacronutrientControl macroControl;
    private final double LOW_CARBS = 0.2;
    private final double MEDIUM_CARBS = 0.35;
    private final double HIGH_CARBS = 0.5;
    private ConnectToDatabase connect = new ConnectToDatabase();
    private UserDatabase userDatabase;

    public RegistrationController() {
        // Initialize GUI
        gui = new LoginViewerGUI();

        // Initialize database connection
        conn = connect.getUserDatabaseConnection();
        userDatabase = new UserDatabase(conn);
    }





    public void submitProfile(Profile userProfile) {
        // Save user input to database

        try {
            userDatabase.createUser(userProfile.getEmail(), userProfile.getAge(), userProfile.getSex(), userProfile.getPassword());
            int userId = userDatabase.getUserId(userProfile.getEmail());
            userDatabase.addWeight(userId, userProfile.getWeight());
            userDatabase.addHeight(userId, userProfile.getHeight());
            userDatabase.addActivityValue(userId, userProfile.getActivityValue());
            userDatabase.addAmountOfCarbs(userId, userProfile.getCarbAmount());
            userDatabase.addGoal(userId, userProfile.getGoal());
            userDatabase.addMealsPerDay(userId, userProfile.getMealsPerDay());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}