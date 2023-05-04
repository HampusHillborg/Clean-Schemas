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

    public String getMeal(String category, int calorieLimit) {
        int lowerCalorieLimit = (int) ((calorieLimit-200)/1.5);
        int upperCalorieLimit = (int) ((calorieLimit+50)/1.5);
        String sql = "SELECT name, kcal FROM food WHERE kcal BETWEEN ? AND ? AND category = ? ORDER BY random() LIMIT 1";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lowerCalorieLimit);
            stmt.setDouble(2, upperCalorieLimit);
            stmt.setString(3, category);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String mealName = rs.getString("name");
                String kcal = rs.getString("kcal");
                System.out.println(mealName + "(" + kcal + "kcal)");
                return mealName;
            } else {
                return "No meals found.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findFittingMeal(int tdee, int protein, int carb, int fat) {



        int lowerCalorieLimit = (int) ((tdee-200)/1.5);
        int upperCalorieLimit = (int) ((tdee+50)/1.5);




        System.out.println(lowerCalorieLimit + " " + upperCalorieLimit);
        System.out.println(protein/1.5 + " " + fat/1.5 + " " + carb/1.5);


        String sql = "SELECT name, protein, fat, carbs, kcal\n" +
                "FROM food\n" +
                "WHERE kcal BETWEEN ? AND ? AND protein >= ? AND fat >= ? AND carbs >= ?\n" +
                "ORDER BY (ABS(protein - ?) + ABS(fat - ?) + ABS(carbs - ?)) ASC, random()\n" +
                "LIMIT 1\n";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lowerCalorieLimit);
            stmt.setDouble(2, upperCalorieLimit);
            stmt.setDouble(3, protein/1.5);
            stmt.setDouble(4, fat/1.5);
            stmt.setDouble(5, carb/1.5);
            stmt.setDouble(6, protein/1.5);
            stmt.setDouble(7, fat/1.5);
            stmt.setDouble(8, carb/1.5);


            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                    String mealName = rs.getString("name");
                    double mealCals = rs.getDouble("kcal");
                    System.out.println("Recommended meal: " + mealName + " (" + mealCals + " kcal)");
                    System.out.println("You need to eat this many grams: " + (tdee / (int) mealCals * 100));
                    return mealName;

                } else {
                    System.out.println("No meals found.");
                }
            }catch (Exception e){
                System.out.println(e);
            }
            return null;
        }

}



