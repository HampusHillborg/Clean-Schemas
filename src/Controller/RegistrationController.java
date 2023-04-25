package src.Controller;

import src.Boundary.LoginViewerGUI;
import src.Boundary.ProfileFormGUI;
import src.Entity.Profile;
import src.Database.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationController {
    private LoginViewerGUI gui;
    private Connection conn;
    private MacronutrientControl macroControl;
    private final double LOW_CARBS = 0.2;
    private final double MEDIUM_CARBS = 0.35;
    private final double HIGH_CARBS = 0.5;
    private ConnectToDatabase connect = new ConnectToDatabase();
    private UserDatabase userDatabase;
    private int tdee;
    private int bmr;
    private int protein;
    private int carbs;
    private int fat;

    public RegistrationController(UserDatabase userDatabase) {
        // Initialize GUI
        this.macroControl = new MacronutrientControl();
        // Initialize database connection
        this.userDatabase = userDatabase;
        gui = new LoginViewerGUI(userDatabase);
    }

    public void submitProfile(Profile userProfile) {
        // Save user input to database

        try {
            userDatabase.createUser(userProfile.getEmail(), userProfile.getAge(), userProfile.getSex(),
                    userProfile.getPassword());
            int userId = userDatabase.getUserId(userProfile.getEmail());
            userDatabase.addWeight(userId, userProfile.getWeight());
            userDatabase.addHeight(userId, userProfile.getHeight());
            userDatabase.addActivityValue(userId, userProfile.getActivityValue());
            userDatabase.addAmountOfCarbs(userId, userProfile.getCarbAmount());
            userDatabase.addGoal(userId, userProfile.getGoal());
            userDatabase.addMealsPerDay(userId, userProfile.getMealsPerDay());
            // Calculate BMR and TDEE
            bmr = macroControl.calculateBmr(userProfile.getWeight(), userProfile.getHeight(), userProfile.getAge(),
                    userProfile.getSex());
            userProfile.setBmr(bmr);
            macroControl.setActivityLevel(userProfile.getActivityValue()); // set activityLevel
            tdee = macroControl.calculateTdee(bmr, userProfile.getActivityValue());
            tdee = macroControl.adjustTdeeForGoal(tdee, userProfile.getGoal());
            userProfile.setTdee(tdee);

            // Update userProfile object with the new BMR and TDEE values
            userProfile.setBmr(bmr);
            userProfile.setTdee(tdee);
            macroControl.calculateMacronutrients(tdee, userProfile.getCarbAmount());

            // Save BMR and TDEE to database
            userId = userDatabase.getUserId(userProfile.getEmail());
            userDatabase.addBmr(userId, bmr);
            userDatabase.addTdee(userId, tdee);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateProfile(Profile userProfile) {
        // Save user input to database

        try {
            int userId = userDatabase.getUserId(userProfile.getEmail());
            userDatabase.addSex(userId, userProfile.getSex());
            userDatabase.addWeight(userId, userProfile.getWeight());
            userDatabase.addHeight(userId, userProfile.getHeight());
            userDatabase.addActivityValue(userId, userProfile.getActivityValue());
            userDatabase.addAmountOfCarbs(userId, userProfile.getCarbAmount());
            userDatabase.addGoal(userId, userProfile.getGoal());
            userDatabase.addMealsPerDay(userId, userProfile.getMealsPerDay());
            userDatabase.addAge(userId, userProfile.getAge());
            // Calculate BMR and TDEE
            bmr = macroControl.calculateBmr(userProfile.getWeight(), userProfile.getHeight(), userProfile.getAge(),
                    userProfile.getSex());
            userProfile.setBmr(bmr);
            macroControl.setActivityLevel(userProfile.getActivityValue()); // set activityLevel
            tdee = macroControl.calculateTdee(bmr, userProfile.getActivityValue());
            tdee = macroControl.adjustTdeeForGoal(tdee, userProfile.getGoal());
            userProfile.setTdee(tdee);

            // Update userProfile object with the new BMR and TDEE values
            userProfile.setBmr(bmr);
            userProfile.setTdee(tdee);

            // Save BMR and TDEE to database
            userId = userDatabase.getUserId(userProfile.getEmail());
            userDatabase.addBmr(userId, bmr);
            userDatabase.addTdee(userId, tdee);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}