package src.Database;

import java.sql.*;

public class TestuserScript {

    ConnectToDatabase connect = new ConnectToDatabase();
    UserDatabase ub = new UserDatabase();

    public TestuserScript() {
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

            ub.createUser("test2@gmail.com", 18, "Male", "123");
            userId = ub.getUserId("test2@gmail.com");
            ub.addWeight(userId, 70);
            ub.addHeight(userId, 170);
            ub.addActivityValue(userId, "Sedentary");
            ub.addAmountOfCarbs(userId, "Medium");
            ub.addGoal(userId, "Maintenance");
            ub.addMealsPerDay(userId, 3);

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
