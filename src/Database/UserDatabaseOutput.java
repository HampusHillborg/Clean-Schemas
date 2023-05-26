package src.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that handles the database outputs
 * that relates to the user database
 */
public class UserDatabaseOutput {

    private final Connection conn;

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
        String password = null;
        String sql = "SELECT password FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    password = rs.getString("password");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        String sql = "SELECT current_weight FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    currentWeight = rs.getDouble("current_weight");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        Double height = null;
        String sql = "SELECT height FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    height = rs.getDouble("height");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        String goal = null;
        String sql = "SELECT goal FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    goal = rs.getString("goal");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return goal;
    }

    /**
     * returns the users diet category
     * @param userId
     * @return
     */
    public String getCategory(int userId) {
        String category = null;
        String sql = "SELECT category FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    category = rs.getString("category");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return category;
    }

    /**
     * Returns the sex of a given user
     *
     * @param userId
     * @return
     */
    public String getSex(int userId) {
        String sex = null;
        String sql = "SELECT sex FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sex = rs.getString("sex");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        int age = 0;
        String sql = "SELECT age FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    age = rs.getInt("age");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        int mealsPerDay = 0;
        String sql = "SELECT meals_per_day FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mealsPerDay = rs.getInt("meals_per_day");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        String carbs = null;
        String sql = "SELECT carbs FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    carbs = rs.getString("carbs");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        String activityValue = null;
        String sql = "SELECT activityValue FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    activityValue = rs.getString("activityValue");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return activityValue;
    }

    /**
     * Returns the Tdee of a given user
     *
     * @param userId
     * @return
     */
    public int getTdee(int userId) {
        int tdee = 0;
        String sql = "SELECT tdee FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tdee = rs.getInt("tdee");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return tdee;
    }

    /**
     * Returns the fat of a given user
     *
     * @param userId
     * @return
     */
    public int getFett(int userId) {
        int fett = 0;
        String sql = "SELECT fett FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fett = rs.getInt("fett");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return fett;
    }

    /**
     * Returns the carbs of a given user
     *
     * @param userId
     * @return
     */
    public int getKolhydrater(int userId) {
        int Kolhydrater = 0;
        String sql = "SELECT kolhydrater FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Kolhydrater = rs.getInt("kolhydrater");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return Kolhydrater;
    }

    /**
     * Returns the protein of a given user
     *
     * @param userId
     * @return
     */
    public int getProtein(int userId) {
        int protein = 0;
        String sql = "SELECT protein FROM user_data WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    protein = rs.getInt("protein");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return protein;
    }

    public static void main(String[] args) {

    }
}
