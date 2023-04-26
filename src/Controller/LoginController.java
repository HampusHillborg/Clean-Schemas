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

/**
 * This class serves as the controller for the login functionality of the application. It handles the
 *  interaction between the LoginViewerGUI view and the UserDatabase model, as well as the calculation
 *  and manipulation of user profiles and their associated macronutrient values.
 */
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

    /**
     * Returns the UserDatabase object associated with this controller.
     *
     * @return the UserDatabase object associated with this controller.
     */
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }


    /**
     * Returns the UserDatabaseOutput object associated with this controller.
     *
     * @return the UserDatabaseOutput object associated with this controller.
     */
    public UserDatabaseOutput getDatabaseOutput() {
        return databaseOutput;
    }

    /**
     * Constructs a new LoginController object with the given UserDatabase.
     *
     * @param userDatabase the UserDatabase to use for this controller.
     */
    public LoginController(UserDatabase userDatabase) {
        // Initialize database connection
        this.userDatabase = userDatabase;
        databaseOutput = new UserDatabaseOutput(userDatabase.getConnection());
        this.macroControl = new MacronutrientControl();
    }

    /**
     * Checks if the given email is registered in the user database.
     *
     * @param email the email to check for registration.
     * @return true if the email is registered, false otherwise.
     * @throws RuntimeException if a SQLException occurs.
     */
    public boolean checkIfRegistered(String email) {
        try {
            return userDatabase.isEmailRegistered(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates the user's login credentials against the user database.
     *
     * @param email the user's email address.
     * @param password the user's password.
     * @return true if the credentials are valid, false otherwise.
     */
    public boolean validateLogin(String email, String password) {
        this.username = email;
        return userDatabase.validateLogin(email, password);
    }

    /**
     * Retrieves the Profile object for the currently logged in user.
     *
     * @param username the username (email) of the logged in user.
     * @return the Profile object for the logged in user.
     */
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
        //int fat= databaseOutput.getFat(userId);//
        //int carbs= databaseOutput.getKolhydrater(userId);
       // int protein= databaseOutput.getProtein(userId);// get TDEE from database
        this.loggedInUser = new Profile(username, password);
        loggedInUser.addToProfile(height, weight, age, sex, goal, activityValue, carbAmount, mealsPerDay);
        loggedInUser.setTdee(tdee); // set TDEE in the user's profile
        return loggedInUser;
    }


    /**
     *This method starts the xmlUpdater thread
     */
    public void loadXML() {
        XmlUpdater xmlUpdater = new XmlUpdater();
        xmlUpdater.start();
    }

    /**
     * This method updates the user profile by saving the user input to the database and calculating BMR, TDEE, fat, carbohydrates, and protein
     *@param userProfile - The user profile object containing the user's input
     */
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

    /**
     * A class that runs as a thread, calling the updateXmlDocument in the NutritionAPI class
     */
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
