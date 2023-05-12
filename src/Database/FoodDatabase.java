package src.Database;

import src.Entity.Food;
import src.Entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
                break;
            case 5:
                updateMeal5(userId, foodName);
                break;
            default:
                System.out.println("Fel att spara");
                break;
        }
    }

    public String getSavedMeal1(int userId) {
        PreparedStatement stmt = null;
        String foodName = null;

        try {
            String sql = "SELECT meal1 FROM user_foods WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                foodName = rs.getString("meal1");
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

        return foodName;
    }

    public String getSavedMeal2(int userId) {
        PreparedStatement stmt = null;
        String foodName = null;

        try {
            String sql = "SELECT meal2 FROM user_foods WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                foodName = rs.getString("meal2");
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

        return foodName;
    }

    public String getSavedMeal3(int userId) {
        PreparedStatement stmt = null;
        String foodName = null;

        try {
            String sql = "SELECT meal3 FROM user_foods WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                foodName = rs.getString("meal3");
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

        return foodName;
    }


    public String getSavedMeal4(int userId) {
        PreparedStatement stmt = null;
        String foodName = null;

        try {
            String sql = "SELECT meal4 FROM user_foods WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                foodName = rs.getString("meal4");
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

        return foodName;
    }

    public String getSavedMeal5(int userId) {
        PreparedStatement stmt = null;
        String foodName = null;

        try {
            String sql = "SELECT meal5 FROM user_foods WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                foodName = rs.getString("meal5");
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

        return foodName;
    }

    public String getSavedMeal(int userId, int mealNumber) {
        switch (mealNumber) {
            case 1:
                return getSavedMeal1(userId);
            case 2:
                return getSavedMeal2(userId);
            case 3:
                return getSavedMeal3(userId);
            case 4:
                return getSavedMeal4(userId);
            case 5:
                return getSavedMeal5(userId);
            default:
                System.out.println("Fel");
        }
        return null;
    }

    /**
     * Returns a list of meals that match the given nutritional requirements and food category.
     *
     * @param proteins the amount of proteins in grams desired for each meal
     * @param carbs the amount of carbohydrates in grams desired for each meal
     * @param kcals the number of calories desired for each meal
     * @param fat the amount of fat in grams desired for each meal
     * @param category the food category desired, or "normal" for any category
     *
     * @return a list of Meal objects that match the nutritional requirements and food category
     */

    public ArrayList<Meal> findFood(double proteins, double carbs, double kcals, double fat, String category) {
        double proteinRatio = proteins / kcals;
        double carbRatio = carbs / kcals;
        double fatRatio = fat / kcals;
        double proteinLowerBound = proteinRatio - 0.03;
        double proteinUpperBound = proteinRatio + 0.03;
        double carbLowerBound = carbRatio - 0.03;
        double carbUpperBound = carbRatio + 0.03;
        double fatLowerBound = fatRatio - 0.03;
        double fatUpperBound = fatRatio + 0.03;
        String sql;
        PreparedStatement stmt;
        ArrayList<Meal> matchingMeal = new ArrayList<>();
        try {

            if(category.equals("normal")){
                sql = "SELECT * FROM food WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ?";
                stmt = conn.prepareStatement(sql);

                // Set the seven placeholders to the desired values
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);

            }else {
                // Create a prepared statement with placeholders for protein, carbs, fat, and category
                sql = "SELECT * FROM food WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ? AND category = ?";
                stmt = conn.prepareStatement(sql);

                // Set the seven placeholders to the desired values
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);
                stmt.setString(7, category);

            }

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
                Meal matchingMeals = new Meal(id, name, carb, protein, fats, kcal, foodCategory);
                matchingMeal.add(matchingMeals);

                //System.out.println("You need to eat this many grams: " + (int) (kcals / kcal * 100) + "g");

            }

            // Close the resources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingMeal;
    }

    public ArrayList<Meal> findBreakfast(double proteins, double carbs, double kcals, double fat, String category) {
        double proteinRatio = proteins / kcals;
        double carbRatio = carbs / kcals;
        double fatRatio = fat / kcals;
        double proteinLowerBound = proteinRatio - 0.03;
        double proteinUpperBound = proteinRatio + 0.03;
        double carbLowerBound = carbRatio - 0.03;
        double carbUpperBound = carbRatio + 0.03;
        double fatLowerBound = fatRatio - 0.03;
        double fatUpperBound = fatRatio + 0.03;
        String sql;
        PreparedStatement stmt;
        ArrayList<Meal> matchingMeal = new ArrayList<>();
        try {

            if(category.equals("normal")){
                sql = "SELECT * FROM breakfast WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ?";
                stmt = conn.prepareStatement(sql);

                // Set the seven placeholders to the desired values
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);

            }else {
                // Create a prepared statement with placeholders for protein, carbs, fat, and category
                sql = "SELECT * FROM breakfast WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ? AND category = ?";
                stmt = conn.prepareStatement(sql);

                // Set the seven placeholders to the desired values
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);
                stmt.setString(7, category);

            }

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
                Meal matchingMeals = new Meal(id, name, carb, protein, fats, kcal, foodCategory);
                matchingMeal.add(matchingMeals);

                //System.out.println("You need to eat this many grams: " + (int) (kcals / kcal * 100) + "g");

            }

            // Close the resources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingMeal;
    }

    public Meal getFoodFromName(String foodName) {
        Meal meal = null;
        String sql = "SELECT * FROM food WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double carb = rs.getDouble("carbs");
                    double protein = rs.getDouble("protein");
                    double fats = rs.getDouble("fat");
                    double kcal = rs.getDouble("kcal");
                    String foodCategory = rs.getString("category");
                    meal = new Meal(id, name, carb, protein, fats, kcal, foodCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    public Meal getBreakfastFromName(String foodName) {
        Meal meal = null;
        String sql = "SELECT * FROM breakfast WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double carb = rs.getDouble("carbs");
                    double protein = rs.getDouble("protein");
                    double fats = rs.getDouble("fat");
                    double kcal = rs.getDouble("kcal");
                    String foodCategory = rs.getString("category");
                    meal = new Meal(id, name, carb, protein, fats, kcal, foodCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }




}



