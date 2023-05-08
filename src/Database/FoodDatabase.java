package src.Database;

import java.sql.*;

public class FoodDatabase {

    Connection conn;

    public FoodDatabase(UserDatabase userDatabase) {
        conn = userDatabase.getConnection();
    }

    /**
     * Method to save food in the foods table
     *
     * @param name
     * @param carbs
     * @param protein
     * @param fat
     * @param kcal
     * @return
     */
    public boolean saveFood(String name, String carbs, String protein, String fat, String kcal) {
        try {
            String sql = "INSERT INTO foods (name, carbs, protein, fat, kcal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, Double.parseDouble(carbs.replace(",", ".")));
            statement.setDouble(3, Double.parseDouble(protein.replace(",", ".")));
            statement.setDouble(4, Double.parseDouble(fat.replace(",", ".")));
            statement.setDouble(5, Double.parseDouble(kcal.replace(",", ".")));
            statement.executeUpdate();
            System.out.println("Food saved successfully");
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving food: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to update the user_foods table with meal 1
     *
     * @param userId
     * @param foodName
     */
    public void updateMeal1(int userId, String foodName) {
        try {
            String sql = "UPDATE user_foods SET meal1 = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, foodName);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Meal 1 updated successfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating meal 1: " + e.getMessage());
        }
    }

    /**
     * Method to update the user_foods table with meal 2
     *
     * @param userId
     * @param foodName
     */
    public void updateMeal2(int userId, String foodName) {
        try {
            String sql = "UPDATE user_foods SET meal2 = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, foodName);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Meal 2 updated successfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating meal 2: " + e.getMessage());
        }
    }

    /**
     * Method to update the user_foods table with meal 3
     *
     * @param userId
     * @param foodName
     */
    public void updateMeal3(int userId, String foodName) {
        try {
            String sql = "UPDATE user_foods SET meal3 = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, foodName);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Meal 3 updated successfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating meal 3: " + e.getMessage());
        }
    }

    /**
     * Method to update the user_foods table with meal 4
     *
     * @param userId
     * @param foodName
     */
    public void updateMeal4(int userId, String foodName) {
        try {
            String sql = "UPDATE user_foods SET meal4 = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, foodName);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Meal 4 updated successfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating meal 4: " + e.getMessage());
        }
    }

    /**
     * Method to update the user_foods table with meal 5
     *
     * @param userId
     * @param foodName
     */
    public void updateMeal5(int userId, String foodName) {
        try {
            String sql = "UPDATE user_foods SET meal5 = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, foodName);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Meal 5 updated successfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating meal 5: " + e.getMessage());
        }
    }

    public void updateMeals(int userId, String foodName, int mealNumber) {
        switch (mealNumber) {
            case 1:
                updateMeal1(userId, foodName);
                break;
            case 2:
                updateMeal2(userId, foodName);
                break;
            case 3:
                updateMeal3(userId, foodName);
                break;
            case 4:
                updateMeal4(userId, foodName);
            case 5:
                updateMeal5(userId, foodName);
            default:
                System.out.println("Fel");
                break;
        }
    }


    public void findFood(double proteins, double carbs, double kcals, double fat, String category) {
        double proteinRatio = proteins / kcals;
        double carbRatio = carbs / kcals;
        double fatRatio = fat / kcals;
        double proteinLowerBound = proteinRatio - 0.03;
        double proteinUpperBound = proteinRatio + 0.03;
        double carbLowerBound = carbRatio - 0.03;
        double carbUpperBound = carbRatio + 0.03;
        double fatLowerBound = fatRatio - 0.03;
        double fatUpperBound = fatRatio + 0.03;
        try {
            // Create a prepared statement with placeholders for protein, carbs, fat, and category
            String sql = "SELECT * FROM food WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ? AND category = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
    
            // Set the seven placeholders to the desired values
            stmt.setDouble(1, proteinLowerBound);
            stmt.setDouble(2, proteinUpperBound);
            stmt.setDouble(3, carbLowerBound);
            stmt.setDouble(4, carbUpperBound);
            stmt.setDouble(5, fatLowerBound);
            stmt.setDouble(6, fatUpperBound);
            stmt.setString(7, category);
    
            // Execute the query and retrieve the result
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double carb = rs.getDouble("carbs");
                double protein = rs.getDouble("protein");
                double fats = rs.getDouble("fat");
                double kcal = rs.getDouble("kcal");
                String foodCategory = rs.getString("category");
                System.out.printf("%d\t%s\t%.2f\t%.2f\t%.2f\t%.2f\t%s\n",
                        id, name, carb, protein, fats, kcal, foodCategory);
                System.out.println("You need to eat this many grams: " + (int) (kcals / kcal * 100) + "g");
                
            }
    
            // Close the resources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}



