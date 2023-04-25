package src.Controller;

import org.xml.sax.SAXException;
import src.API.NutritionAPI;
import src.Boundary.LoginViewerGUI;
import src.Database.ConnectToDatabase;
import src.Database.UserDatabase;
import src.Database.UserDatabaseOutput;
import src.Entity.Profile;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    private ConnectToDatabase connect = new ConnectToDatabase();
    private Connection conn;
    private UserDatabase userDatabase;
    private String username;
    private Profile loggedInUser;
    private UserDatabaseOutput databaseOutput;
    private MacronutrientControl macroControl;
    private final double LOW_CARBS = 0.2;
    private final double MEDIUM_CARBS = 0.35;
    private final double HIGH_CARBS = 0.5;
    private int tdee;
    private int bmr;

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

    public UserDatabaseOutput getDatabaseOutput() {
        return databaseOutput;
    }

    public LoginController(UserDatabase userDatabase) {
        // Initialize database connection
        this.userDatabase = userDatabase;
        databaseOutput = new UserDatabaseOutput(userDatabase.getConnection());
        this.macroControl = new MacronutrientControl();
    }

    public boolean checkIfRegistered(String email) {
        try {
            return userDatabase.isEmailRegistered(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateLogin(String email, String password) {
        this.username = email;
        return userDatabase.validateLogin(email, password);
    }

    public Profile getLoggedInUser(String username) {
        int userId = userDatabase.getUserId(username);
        String password = databaseOutput.getPassword(userId);
        Double height = databaseOutput.getHeight(userId);
        Double weight = databaseOutput.getCurrentWeight(userId);
        int age = databaseOutput.getAge(userId);
        String sex = databaseOutput.getSex(userId);
        String goal = databaseOutput.getGoal(userId);
        String activityValue = databaseOutput.getActivityValue(userId);
        String carbAmount = databaseOutput.getCarbs(userId);
        int mealsPerDay = databaseOutput.getMealsPerDay(userId);
        int tdee = databaseOutput.getTdee(userId);
        int fat= databaseOutput.getFat(userId);//
        int carbs= databaseOutput.getKolhydrater(userId);
        int protein= databaseOutput.getProtein(userId);// get TDEE from database
        this.loggedInUser = new Profile(username, password);
        loggedInUser.addToProfile(height, weight, age, sex, goal, activityValue, carbAmount, mealsPerDay);
        loggedInUser.setTdee(tdee); // set TDEE in the user's profile
        return loggedInUser;
    }

    public void loadXML() {
        XmlUpdater xmlUpdater = new XmlUpdater();
        xmlUpdater.start();
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
            // Calculate fat, carbohydrates, and protein



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

    private class XmlUpdater extends Thread {
        private NutritionAPI api = new NutritionAPI();

        @Override
        public void run() {
            try {
                api.updateXmlDocument();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
