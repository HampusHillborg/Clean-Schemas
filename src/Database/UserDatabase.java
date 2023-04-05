package src.Database;
import java.sql.*;


public class UserDatabase {

    Connection conn;

    public UserDatabase(){
        conn = getDatabaseConnection();
    }
    

    /*
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

    /*
    * Creates a new user with the given email, first name, last name, age, sex, and password
    * in the database. Also creates a corresponding row in the user_data table.
    * Returns true if the operation was successful, false otherwise.
    */
    public boolean createUser(String email, String firstName, String lastName, int age, char sex, String password) throws SQLException {
        // Insert new user into users table
        String usersSql = "INSERT INTO users (email, first_name, last_name, password) VALUES (?, ?, ?, ?)";
        PreparedStatement usersStmt = conn.prepareStatement(usersSql);
        usersStmt.setString(1, email);
        usersStmt.setString(2, firstName);
        usersStmt.setString(3, lastName);
        usersStmt.setString(4, password);
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



    /*
     * Adds current weight to a user_id in the database.
     * If successful, returns true.
     */
    public boolean addWeight(int userId, double weight) throws SQLException {
        String sql = "UPDATE user_data SET current_weight = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, weight);
        stmt.setInt(2, userId);
        int rowsInserted = stmt.executeUpdate();
        stmt.close();
        return rowsInserted == 1;
    }


    /*
     * Adds the goal weight to a given user in the database.
     * If successful, returns true.
     */
    public boolean addGoalWeight(int userId, double goalWeight) throws SQLException {
        String sql = "UPDATE user_data SET goal_weight = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, goalWeight);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    /*
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

    /*
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

    /*
     * Adds how many meals a user eats per day to a given user in the database.
     * If successful, returns true. exercise
     */
    public boolean addExercisePerWeek(int userId, int exercise) throws SQLException {
        String sql = "UPDATE user_data SET exercise_per_week = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, exercise);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }

    /*
     * Adds how many carbs a user eats per day to a given user in the database.
     * If successful, returns true.
     */
    public boolean addAmountOfCarbs(int userId, int cals) throws SQLException {
        String sql = "UPDATE user_data SET carbs = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, cals);
        stmt.setInt(2, userId);
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return rowsUpdated == 1;
    }


    /*
     * Connects to the database and
     * prints connected if successful.
     * Returns a connection that can be used to send statements to the database.
     */
    public Connection getDatabaseConnection() {

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

    public static void main(String[] args) {
        UserDatabase ud = new UserDatabase();
        
    }
}
