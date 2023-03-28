package src;
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
     * Creates an User with email, firstname and lastname in the database.
     * If successful, returns true.
     */
    public boolean createUser(String email, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO users (email, first_name, last_name) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, firstName);
        stmt.setString(3, lastName);
        int rowsInserted = stmt.executeUpdate();
        stmt.close();
        return rowsInserted == 1;
    }


    /*
     * Adds current weight to a user_id in the database.
     * If successful, returns true.
     */
    public boolean addWeight(int userId, double weight) throws SQLException {
        String sql = "INSERT INTO user_data (user_id, current_weight) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setDouble(2, weight);
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
        
        try {
            ud.addGoalWeight(1, 70);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
