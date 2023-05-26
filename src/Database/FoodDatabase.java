package src.Database;

import src.Entity.Food;
import src.Entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that handles requests sent to
 * the database related to food
 */
public class FoodDatabase {

    private Connection conn;

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
            statement.setDouble(2, parseDoubleValue(carbs));
            statement.setDouble(3, parseDoubleValue(protein));
            statement.setDouble(4, parseDoubleValue(fat));
            statement.setDouble(5, parseDoubleValue(kcal));
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving food: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to update the user_foods table with meal a meal
     *
     * @param userId
     * @param foodName
     * @param mealNumber
     */
    public void updateMeal(int userId, String foodName, int mealNumber) {
        try {
            String sql = "UPDATE user_foods SET meal" + mealNumber + " = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, foodName);
            statement.setInt(2, userId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating meal " + mealNumber + ": " + e.getMessage());
        }
    }


    public void updateMeals(int userId, String foodName, int mealNumber) {
        if (mealNumber >= 1 && mealNumber <= 5) {
            updateMeal(userId, foodName, mealNumber);
        } else {
            System.out.println("Invalid meal number");
        }
    }

    public String getSavedMeal(int userId, int mealNumber) {
        String mealColumnName = "meal" + mealNumber;
        PreparedStatement stmt = null;
        String foodName = null;

        try {
            String sql = "SELECT " + mealColumnName + " FROM user_foods WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                foodName = rs.getString(mealColumnName);
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodName;
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
        double tolerance = 0.03;

        double proteinLowerBound = proteinRatio - tolerance;
        double proteinUpperBound = proteinRatio + tolerance;
        double carbLowerBound = carbRatio - tolerance;
        double carbUpperBound = carbRatio + tolerance;
        double fatLowerBound = fatRatio - tolerance;
        double fatUpperBound = fatRatio + tolerance;

        String sql;
        PreparedStatement stmt;
        ArrayList<Meal> matchingMeals = new ArrayList<>();

        try {
            if (category.equals("normal")) {
                sql = "SELECT * FROM food WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ?";
                stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);
            } else {
                sql = "SELECT * FROM food WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ? AND category = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);
                stmt.setString(7, category);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double carb = rs.getDouble("carbs");
                double protein = rs.getDouble("protein");
                double fats = rs.getDouble("fat");
                double kcal = rs.getDouble("kcal");
                String foodCategory = rs.getString("category");
                Meal matchingMeal = new Meal(id, name, carb, protein, fats, kcal, foodCategory);
                matchingMeals.add(matchingMeal);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingMeals;
    }

    /**
     * returns a meal that fits the users meal plan with a 3% accuracy
     * @param proteins
     * @param carbs
     * @param kcals
     * @param fat
     * @param category
     * @return
     */
    public ArrayList<Meal> findBreakfast(double proteins, double carbs, double kcals, double fat, String category) {
        double proteinRatio = proteins / kcals;
        double carbRatio = carbs / kcals;
        double fatRatio = fat / kcals;
        double tolerance = 0.03;

        double proteinLowerBound = proteinRatio - tolerance;
        double proteinUpperBound = proteinRatio + tolerance;
        double carbLowerBound = carbRatio - tolerance;
        double carbUpperBound = carbRatio + tolerance;
        double fatLowerBound = fatRatio - tolerance;
        double fatUpperBound = fatRatio + tolerance;

        String sql;
        PreparedStatement stmt;
        ArrayList<Meal> matchingMeals = new ArrayList<>();

        try {
            if (category.equals("normal")) {
                sql = "SELECT * FROM breakfast WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ?";
                stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);
            } else {
                sql = "SELECT * FROM breakfast WHERE protein / kcal BETWEEN ? AND ? AND carbs / kcal BETWEEN ? AND ? AND fat / kcal BETWEEN ? AND ? AND category = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setDouble(1, proteinLowerBound);
                stmt.setDouble(2, proteinUpperBound);
                stmt.setDouble(3, carbLowerBound);
                stmt.setDouble(4, carbUpperBound);
                stmt.setDouble(5, fatLowerBound);
                stmt.setDouble(6, fatUpperBound);
                stmt.setString(7, category);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double carb = rs.getDouble("carbs");
                double protein = rs.getDouble("protein");
                double fats = rs.getDouble("fat");
                double kcal = rs.getDouble("kcal");
                String foodCategory = rs.getString("category");
                Meal matchingMeal = new Meal(id, name, carb, protein, fats, kcal, foodCategory);
                matchingMeals.add(matchingMeal);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingMeals;
    }

    /**
     * Creates a Meal from a food name
     * @param foodName
     * @return
     */
    public Meal getFoodFromName(String foodName) {
        Meal meal = null;
        String sql = "SELECT * FROM food WHERE name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    meal = createMealFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meal;
    }

    /**
     * Creates a Meal from a food name
     * @param foodName
     * @return
     */
    public Meal getBreakfastFromName(String foodName) {
        Meal meal = null;
        String sql = "SELECT * FROM breakfast WHERE name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    meal = createMealFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meal;
    }

    /**
     * Returns an array of ingredients
     * @param name
     * @return
     */
    public ArrayList<String[]> getRecipe(String name) {
        int id = getFoodId(name);
        ArrayList<String[]> recipe = new ArrayList<>();

        String sql = "SELECT ingredient, amount_grams, amount_ml, amount_tbsp, amount_st FROM recipes WHERE food_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String ingredient = rs.getString("ingredient");
                    String amount = null;
                    String label = null;

                    if (rs.getBigDecimal("amount_grams") != null) {
                        amount = rs.getBigDecimal("amount_grams").toString();
                        label = " grams";
                    } else if (rs.getBigDecimal("amount_ml") != null) {
                        amount = rs.getBigDecimal("amount_ml").toString();
                        label = " ml";
                    } else if (rs.getBigDecimal("amount_tbsp") != null) {
                        amount = rs.getBigDecimal("amount_tbsp").toString();
                        label = " tbsp";
                    } else if (rs.getBigDecimal("amount_st") != null) {
                        amount = rs.getBigDecimal("amount_st").toString();
                        label = " st";
                    }

                    if (ingredient != null && amount != null) {
                        String[] ingredientEntry = { ingredient, amount, label };
                        recipe.add(ingredientEntry);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipe;
    }

    /**
     * Returns the food id from a food name
     * @param name
     * @return
     */
    public int getFoodId(String name) {
        String sql = "SELECT * FROM food WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private double parseDoubleValue(String value) {
        return Double.parseDouble(value.replace(",", "."));
    }

    /**
     * Creates a Meal object from the data in the ResultSet.
     *
     * @param rs The ResultSet containing the meal data.
     * @return A Meal object created from the ResultSet data.
     * @throws SQLException If an error occurs while retrieving the data from the
     *                      ResultSet.
     */
    private Meal createMealFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double carb = rs.getDouble("carbs");
        double protein = rs.getDouble("protein");
        double fats = rs.getDouble("fat");
        double kcal = rs.getDouble("kcal");
        String foodCategory = rs.getString("category");
        return new Meal(id, name, carb, protein, fats, kcal, foodCategory);
    }

    public static void main(String[] args) {
        FoodDatabase foodDatabase = new FoodDatabase(new UserDatabase());
        ArrayList<String[]> recipe = foodDatabase.getRecipe("Vegetarian Stir Fry");

        System.out.println("Recipe for Vegetarian Stir Fry:");
        for (String[] ingredientEntry : recipe) {
            String ingredient = ingredientEntry[0];
            String amount = ingredientEntry[1];
            System.out.println(ingredient + ": " + amount);
        }
    }

}
