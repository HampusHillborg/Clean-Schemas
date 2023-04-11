package src.Database;
import src.Controller.MacronutrientControl;

import java.sql.*;


public class UserDatabase {

    Connection conn;
    MacronutrientControl macronutrientControl;

    public UserDatabase(Connection conn){
        this.conn = conn;
        this.macronutrientControl = new MacronutrientControl();
    }
    

    /**
     * Checks if the user is already registered in the database.
     * Returns true if user is already registered.
     */
    public boolean isEmailRegistered(String email) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean emailRegistered = rs.next();
        rs.close();
        stmt.close();
        return emailRegistered;
    }

    public boolean validateLogin(String email, String password) {
        String sql = "SELECT COUNT(*) AS count FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count == 1;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }


    /**
    * Creates a new user with the given email, first name, last name, age, sex, and password
    * in the database. Also creates a corresponding row in the user_data table.
    * Returns true if the operation was successful, false otherwise.
    */
    public boolean createUser(String email, int age, String sex, String password) throws SQLException {
        // Insert new user into users table
        String usersSql = "INSERT INTO users (email, password) VALUES (?, ?)";
        PreparedStatement usersStmt = conn.prepareStatement(usersSql);
        usersStmt.setString(1, email);
        usersStmt.setString(2, password);
        int rowsInserted = usersStmt.executeUpdate();
        usersStmt.close();
        if (rowsInserted != 1) {
            return false;
        }

    // Get the user ID of the newly created user
    String userIdSql = "SELECT id FROM users WHERE email = ?";
    PreparedStatement userIdStmt = conn.prepareStatement(userIdSql);
    userIdStmt.setString(1, email);
    ResultSet userIdResult = userIdStmt.executeQuery();
    userIdResult.next();
    int userId = userIdResult.getInt("id");
    userIdStmt.close();

    // Insert corresponding row into user_data table
    String userDataSql = "INSERT INTO user_data (user_id, age, sex) VALUES (?, ?, ?)";
    PreparedStatement userDataStmt = conn.prepareStatement(userDataSql);
    userDataStmt.setInt(1, userId);
    userDataStmt.setInt(2, age);
    userDataStmt.setString(3, String.valueOf(sex));
    rowsInserted = userDataStmt.executeUpdate();
    userDataStmt.close();

    return rowsInserted == 1;
}

    public int getUserId(String email) {
        try {
            String sql = "SELECT id from users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            stmt.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Adds current weight to a user_id in the database.
     * If successful, returns true.
     */
    public boolean addWeight(int userId, double weight) throws SQLException {
        String sql = "UPDATE user_data SET current_weight = ? WHERE user_id = ?";
        System.out.println(userId + "  " + weight);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, weight);
        stmt.setInt(2, userId);
        int rowsInserted = stmt.executeUpdate();
        stmt.close();
        return rowsInserted == 1;
    }


    /**
     * Adds the goal weight to a given user in the database.
     * If successful, returns true.
     */
    public boolean addGoal(int userId, String goal) throws SQLException {
        String sql = "UPDATE user_data SET goal = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, goal);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    /**
     * Adds the height to a given user in the database.
     * If successful, returns true.
     */
    public boolean addHeight(int userId, double height) throws SQLException {
        String sql = "UPDATE user_data SET height = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, height);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    /**
     * Adds how many meals a user eats per day to a given user in the database.
     * If successful, returns true.
     */
    public boolean addMealsPerDay(int userId, int meals) throws SQLException {
        String sql = "UPDATE user_data SET meals_per_day = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, meals);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    /**
     * Adds activityValue to a given user in the database.
     * If successful, returns true. exercise
     */
    public boolean addActivityValue(int userId, String activityValue) throws SQLException {
        String sql = "UPDATE user_data SET activityValue = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, activityValue);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    /**
     * Adds how many carbs a user eats per day to a given user in the database.
     * If successful, returns true.
     */
    public boolean addAmountOfCarbs(int userId, String carbs) throws SQLException {
        String sql = "UPDATE user_data SET carbs = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, carbs);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    public boolean addBmr(int userId, int bmr) throws SQLException{
        String sql = "UPDATE user_data SET bmr = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bmr);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    public boolean addTdee(int userId, int tdee) throws SQLException{
        String sql = "UPDATE user_data SET tdee = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, tdee);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }


    public static void main(String[] args) {
        ConnectToDatabase connect = new ConnectToDatabase();
        UserDatabase ub = new UserDatabase(connect.getUserDatabaseConnection());
        //System.out.println(ub.getUserId("hampushillborg@gmail.com"));
            System.out.println(ub.getUserId("andreas"));
    }
}
