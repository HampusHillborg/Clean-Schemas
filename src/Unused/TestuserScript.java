package src.Unused;

import src.Controller.RegistrationController;
import src.Database.ConnectToDatabase;
import src.Database.UserDatabase;
import src.Entity.Profile;

import java.sql.*;

public class TestuserScript {

    ConnectToDatabase connect = new ConnectToDatabase();
    UserDatabase ub = new UserDatabase();
    RegistrationController registrationController;

    public TestuserScript() {
         registrationController = new RegistrationController(ub);
        removeTestUser(ub.getUserId("test1@gmail.com"));
        addTestUser();
    }

    public void removeTestUser(int userId) {
        Connection conn = null;
        try {
            conn = connect.getUserDatabaseConnection();
            conn.setAutoCommit(false);

            // Delete user data from user_data table
            String sqlDeleteUserData = "DELETE FROM user_data WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteUserData)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            // Delete user from user_foods table
            String sqlDeleteUserFood = "DELETE FROM user_foods WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteUserFood)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            // Delete user from users table
            String sqlDeleteUser = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteUser)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addTestUser() {
        Connection conn = null;
        try {
            conn = connect.getUserDatabaseConnection();
            conn.setAutoCommit(false);

            int userId = ub.getUserId("test2@gmail.com");

            // Delete user from user_foods table
            String sqlDeleteUserFood = "DELETE FROM user_foods WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteUserFood)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            // Delete user data from user_data table
            String sqlDeleteUserData = "DELETE FROM user_data WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteUserData)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }


            // Delete user from users table
            String sqlDeleteUser = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDeleteUser)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }


            conn.commit();

            Profile profile = new Profile("test2@gmail.com", "123");
            profile.addToProfile(170, 70, 18, "Male","Maintenance", "Sedentary", "Medium", 3);

            registrationController.submitProfile(profile);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        TestuserScript testuserScript = new TestuserScript();
    }
}
