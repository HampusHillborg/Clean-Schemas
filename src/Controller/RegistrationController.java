package src.Controller;


import src.Boundary.ProfileFormGUI;
import src.Entity.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationController {
    private ProfileFormGUI gui;
    private Connection conn;
    private MacronutrientControl macroControl;
    private final double LOW_CARBS = 0.2;
    private final double MEDIUM_CARBS = 0.35;
    private final double HIGH_CARBS = 0.5;

    public RegistrationController() {
        // Initialize GUI
        gui = new ProfileFormGUI();

        // Initialize database connection
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://pgserver.mau.se:5432/cleanschemas", "an4737", "gzomhy1h");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void submitProfile(Profile userProfile) {
        // Save user input to database
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO userdata (height, weight, age, sex, goal, activity, carbAmount, mealsPerDay) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setDouble(1, userProfile.getHeight());
            stmt.setDouble(2, userProfile.getWeight());
            stmt.setInt(3, userProfile.getAge());
            stmt.setString(4, userProfile.getSex());
            stmt.setString(5, userProfile.getGoal());
            stmt.setString(6, userProfile.getActivityValue());
            stmt.setString(7, userProfile.getCarbAmount());
            stmt.setInt(8, userProfile.getMealsPerDay());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}