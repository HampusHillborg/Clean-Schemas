package src.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CREATE TABLE users (
 * id SERIAL PRIMARY KEY,
 * email VARCHAR(255) UNIQUE NOT NULL,
 * password VARCHAR(255) NOT NULL,
 * first_name VARCHAR(50) NOT NULL,
 * last_name VARCHAR(50) NOT NULL
 * );
 * <p>
 * CREATE TABLE user_data (
 * user_id INTEGER NOT NULL REFERENCES users(id),
 * current_weight NUMERIC(5,2),
 * height NUMERIC(5,2),
 * goal_weight NUMERIC(5,2),
 * sex CHAR(1) NOT NULL,
 * age INTEGER NOT NULL,
 * meals_per_day INTEGER,
 * carbs INTEGER,
 * exercise_per_week NUMERIC(5,2),
 * PRIMARY KEY (user_id)
 * );
 * <p>
 * "SELECT * FROM user_data WHERE user_id = ?"
 */

public class UserDatabaseOutput {

    private final Connection conn;
    private ConnectToDatabase connection = new ConnectToDatabase();

    public UserDatabaseOutput(Connection conn) {
        this.conn = conn;
    }

    /**
     Retrieves a specific field value for a given user from the "user_data" table in the database.
     @param userId the unique identifier of the user whose field value is being retrieved.
     @param fieldName the name of the field being retrieved from the "user_data" table.
     @return the value of the specified field as an Object, or null if the field is not found.
     */
    public Object getField(int userId, String fieldName) {
        String sql = "SELECT " + fieldName + " FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getObject(fieldName);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the password of a given user
     *
     * @param userId
     * @return
     */
    public String getPassword(int userId) {
        PreparedStatement stmt = null;
        String password = null;

        try {
            String sql = "SELECT password FROM users WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("password");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Returns the current weight of a given user
     *
     * @param userId
     * @return
     */

    public Double getCurrentWeight(int userId) {
        Double currentWeight = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT current_weight FROM user_data WHERE user_id = ?")) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    currentWeight = rs.getDouble("current_weight");
                }
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return currentWeight;
    }

    /**
     * Returns the height of a given user
     *
     * @param userId
     * @return
     */
    public Double getHeight(int userId) {
        String sql = "SELECT height FROM user_data WHERE user_id = ?";
        Double height = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    height = rs.getDouble("height");
                }
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return height;
    }

    /**
     * Returns the goal weight of a given user
     *
     * @param userId
     * @return
     */
    public String getGoal(int userId) {
        PreparedStatement stmt = null;
        String goal = null;

        try {
            String sql = "SELECT goal FROM user_data WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                goal = rs.getString("goal");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return goal;
    }

    /**
     * Returns the sex of a given user
     *
     * @param userId
     * @return
     */
    public String getSex(int userId) {
        String sex = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT sex FROM user_data WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sex = rs.getString("sex");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sex;
    }

    /**
     * Returns the age of a given user
     *
     * @param userId
     * @return
     */
    public int getAge(int userId) {
        PreparedStatement stmt = null;
        int age = 0;

        try {
            String sql = "SELECT age FROM user_data WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                age = rs.getInt("age");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }
        return age;
    }

    /**
     * Returns the meals per day of a given user
     *
     * @param userId
     * @return
     */
    public int getMealsPerDay(int userId) {
        PreparedStatement stmt = null;
        int mealsPerDay = 0;

        try {
            String sql = "SELECT meals_per_day FROM user_data WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mealsPerDay = rs.getInt("meals_per_day");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }
        return mealsPerDay;
    }

    /**
     * Returns the carbs of a given user
     *
     * @param userId
     * @return
     */
    public String getCarbs(int userId) {
        PreparedStatement stmt = null;
        String carbs = null;

        try {
            String sql = "SELECT carbs FROM user_data WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                carbs = rs.getString("carbs");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }
        return carbs;
    }

    /**
     * Returns the exercises per week of a given user
     *
     * @param userId
     * @return
     */
    public String getActivityValue(int userId) {
        PreparedStatement stmt = null;
        String activityValue = null;

        try {
            String sql = "SELECT activityValue FROM user_data WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                activityValue = rs.getString("activityValue");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }
        return activityValue;
    }

    public int getTdee(int userId) {
        PreparedStatement stmt = null;
        int tdee = 0;

        try {
            String sql = "SELECT tdee FROM user_data WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tdee = rs.getInt("tdee");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return tdee;
    }
    /*
    public int getFat(int userId) {
        PreparedStatement stmt = null;
        int fat = 0;

        try {
            String sql = "SELECT fat FROM  foods  WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fat = rs.getInt("fat");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return fat;
    }
    public int getKolhydrater(int userId) {
        PreparedStatement stmt = null;
        int Kolhydrater = 0;

        try {
            String sql = "SELECT carbs FROM  foods  WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Kolhydrater = rs.getInt("carbs");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return Kolhydrater;
    }
    public int getProtein(int userId) {
        PreparedStatement stmt = null;
        int protein = 0;

        try {
            String sql = "SELECT protein FROM  foods  WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                protein = rs.getInt("protein");
            }

            // Clean-up environment
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return protein;
    }


     */



    public static void main(String[] args) {

    }

}
