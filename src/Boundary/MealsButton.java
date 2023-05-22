package src.Boundary;

import src.API.NutritionAPI;
import src.Database.FoodDatabase;
import src.Database.UserDatabase;
import src.Database.UserDatabaseOutput;
import src.Entity.Food;
import src.Entity.Meal;
import src.Entity.Profile;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * The MealsButton class represents a GUI for displaying the user's meals and
 * macros. It extends JFrame and implements ActionListener.
 * The GUI displays a panel for each meal and a panel for the user's selected
 * meal macros. Each meal panel contains two buttons: "Add Meal" and "Generate
 * Meal", which can be used to add or generate a new meal. The selected meal
 * macros are displayed in the macros panel.
 * The class has a constructor that takes a userProfile as a parameter and sets
 * up the GUI accordingly.
 * The class also contains a list of meal panels and a userDatabaseOutput object
 * that is used to access the user's meals and macros.
 * The class is located in the src.Boundary package.
 */
public class MealsButton extends JFrame {

    private NutritionAPI nutritionAPI = new NutritionAPI();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private UserDatabase userDatabase;
    private FoodDatabase foodDatabase;
    private int userId;

    private int buttonNumber = 0;

    private final List<JPanel> mealPanels = new ArrayList<>();
    private final JPanel mealsPanel = new JPanel(new GridLayout(0, 1));
    private final JPanel macrosPanel = new JPanel(new GridLayout(0, 1));
    private final JScrollPane mealsScrollPane = new JScrollPane(mealsPanel);

    // private final JButton addMealButton = new JButton("Add Meal");
    // private final JButton generateMealButton = new JButton("Generate Meal");
    private final JLabel macrosLabel = new JLabel("Total Macros for the day:");

    private UserDatabaseOutput userDatabaseOutput;

    private int totalCalories = 0;
    private int totalProtein = 0;
    private int totalCarbs = 0;
    private int totalFat = 0;
    private final JLabel totalCaloriesLabel = new JLabel();
    private final JLabel totalProteinLabel = new JLabel();
    private final JLabel totalCarbsLabel = new JLabel();
    private final JLabel totalFatLabel = new JLabel();

    /**
     * Constructs a new MealsButton object with a given user profile.
     * This method creates a GUI for meals and macros, where the meals
     * are displayed in a scrollable pane and the macros are displayed
     * in a fixed-size panel. The number of meals displayed is determined
     * by the userProfile parameter. The method adds two buttons to each
     * meal panel: an "Add Meal" button and a "Generate Meal" button.
     *
     * @param userProfile the user profile containing the number of meals per day to
     *                    be displayed
     */
    public MealsButton(Profile userProfile, UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        foodDatabase = new FoodDatabase(userDatabase);
        userId = userDatabase.getUserId(userProfile.getEmail());
        setTitle("Meals GUI");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the meals panel with scrollable layout
        mealsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mealsPanel.setBackground(Color.WHITE);
        mealsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mealsScrollPane.setPreferredSize(new Dimension(400, 500));

        // Add the meal panels to the meals panel
        for (int i = 0; i < userProfile.getMealsPerDay(); i++) {
            JPanel mealPanel = new JPanel(new BorderLayout());
            TitledBorder titledBorder;
            if (i == 0) {
                titledBorder = BorderFactory.createTitledBorder("Breakfast");
            } else {
                titledBorder = BorderFactory.createTitledBorder("Meal " + (i + 1));
            }
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
            mealPanel.setBorder(
                    BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10), titledBorder));
            GenerateMealButton generateMealButton = new GenerateMealButton(userProfile, foodDatabase, i + 1);
            GenerateRecipe generateRecipe = new GenerateRecipe(userProfile, foodDatabase, i + 1);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            buttonPanel.add(generateMealButton);
            buttonPanel.add(generateRecipe);
            mealPanel.add(buttonPanel, BorderLayout.NORTH);
            JPanel mealFoodsPanel = new JPanel();
            mealPanel.add(mealFoodsPanel, BorderLayout.CENTER);
            if (!(foodDatabase.getSavedMeal(userId, i + 1) == null)) {
                JLabel selectedMealLabel = new JLabel("Last saved meal: " + foodDatabase.getSavedMeal(userId, i + 1));
                mealFoodsPanel.add((selectedMealLabel));
            }
            mealPanels.add(mealPanel);
            mealsPanel.add(mealPanel);
        }

        // Create the macros panel
        macrosPanel.setBorder(BorderFactory.createTitledBorder("Selected Meal Macros"));
        macrosPanel.setBackground(Color.LIGHT_GRAY);
        macrosPanel.setPreferredSize(new Dimension(300, 500));

        macrosPanel.setBackground(Color.LIGHT_GRAY);
        macrosPanel.setPreferredSize(new Dimension(300, 500));
        macrosPanel.add(macrosLabel);
        macrosPanel.add(totalProteinLabel);
        macrosPanel.add(totalCarbsLabel);
        macrosPanel.add(totalFatLabel);

        // Create a JPanel to hold the total calories label
        JPanel totalCaloriesPanel = new JPanel();
        totalCaloriesPanel.setBackground(Color.LIGHT_GRAY);
        totalCaloriesPanel.setPreferredSize(new Dimension(300, 50));
        totalCaloriesPanel.add(totalCaloriesLabel);

        // Create a vertical BoxLayout for the macros panel
        BoxLayout macrosBoxLayout = new BoxLayout(macrosPanel, BoxLayout.Y_AXIS);
        macrosPanel.setLayout(macrosBoxLayout);

        // Add the total calories panel to the macros panel
        macrosPanel.add(Box.createVerticalGlue());
        macrosPanel.add(totalCaloriesPanel);

        // Create a split pane with the meals panel on the left and the macros panel on
        // the right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mealsScrollPane, macrosPanel);
        splitPane.setDividerLocation(400);

        // Add the split pane to the frame
        getContentPane().add(splitPane);

        // Create an empty border for the meals panel
        Border mealsPanelBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mealsPanel.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), mealsPanelBorder));

        // Create an empty border for the macros panel
        Border macrosPanelBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        macrosPanel.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), macrosPanelBorder));

        // Add the split pane to the frame
        getContentPane().add(splitPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This class represents a custom JButton component used to generate a random
     * meal
     * based on the user's profile and dietary requirements.
     * It extends the JButton class and overrides its default behavior.
     * It contains a Profile object and a FoodDatabase object used to generate the
     * meal.
     */

    private class GenerateRecipe extends JButton {

        private final Color buttonColor = new Color(59, 89, 152);

        private Profile userProfile;
        private FoodDatabase foodDatabase;
        private int i;
        private JFrame recipeFrame;
        private JLabel recipeLabel;
        private JLabel recipeName;
        double macrosModifier;


        public GenerateRecipe(Profile userProfile, FoodDatabase foodDatabase, int i) {
            this.i = i;
            Meal meal = null;

            setText("Generate Recipe");
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
            this.userProfile = userProfile;
            this.foodDatabase = foodDatabase;
            if (!(foodDatabase.getSavedMeal(userId, i) == null)) {
                if (i == 1) {
                    meal = foodDatabase.getBreakfastFromName(foodDatabase.getSavedMeal(userId, i));
                } else {
                    meal = foodDatabase.getFoodFromName(foodDatabase.getSavedMeal(userId, i));
                }
                double portionGrams = ((userProfile.getTdee() / userProfile.getMealsPerDay()) / meal.getKcal() * 100);
                macrosModifier = (portionGrams / 100);
            }

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String[]> recipe = foodDatabase.getRecipe(foodDatabase.getSavedMeal(userId, i));

                    StringBuilder recipeText = new StringBuilder();
                    for (String[] ingredientEntry : recipe) {
                        String ingredient = ingredientEntry[0];
                        double amount = Double.valueOf(ingredientEntry[1]) * macrosModifier;
                        String label = ingredientEntry[2];
                        recipeText.append(ingredient).append(": ").append(String.format("%.1f",amount)).append(label).append("<br>");
                    }

                    if (recipeText.length() > 0) {
                        recipeFrame = new JFrame("Recipe");
                        recipeName = new JLabel("Recipe for " + foodDatabase.getSavedMeal(userId, i));
                        recipeLabel = new JLabel("<html>" + recipeText.toString() + "</html>");

                        recipeFrame.getContentPane().setLayout(new FlowLayout());
                        recipeFrame.getContentPane().add(recipeName);
                        recipeFrame.getContentPane().add(recipeLabel);

                        recipeFrame.setSize(400, 200);
                        recipeFrame.setLocationRelativeTo(null);
                        recipeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        recipeFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No recipe found for this meal.", "Recipe Not Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });


        }
    }

    private class GenerateMealButton extends JButton {

        private int totalCalories = 0;
        private final JLabel totalCaloriesLabel = new JLabel();
        private final Color buttonColor = new Color(59, 89, 152);

        private Profile userProfile;
        private FoodDatabase foodDatabase;
        private int i;

        JLabel proteinLabel = new JLabel("Protein:");
        JLabel carbsLabel = new JLabel("Carbs:");
        JLabel fatLabel = new JLabel("Fat:");
        JLabel caloriesLabel = new JLabel("Calories:");
        JLabel gramsToEatLabel = new JLabel("Portion size");
        JLabel mealLabel = new JLabel();

        /**
         * Constructs a GenerateMealButton object.
         * 
         * @param userProfile  a Profile object containing the user's information
         * @param foodDatabase a FoodDatabase object containing the database of
         * available meals
         */
        public GenerateMealButton(Profile userProfile, FoodDatabase foodDatabase, int i) {
            this.i = i;
            Meal meal = null;

            setText("Generate Meal");
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
            this.userProfile = userProfile;
            this.foodDatabase = foodDatabase;
            if (!(foodDatabase.getSavedMeal(userId, i) == null)) {
                if (i == 1) {
                    meal = foodDatabase.getBreakfastFromName(foodDatabase.getSavedMeal(userId, i));
                } else {
                    meal = foodDatabase.getFoodFromName(foodDatabase.getSavedMeal(userId, i));
                }
                updateLabels(meal, userProfile);
            }

            macrosPanel.add(proteinLabel);
            macrosPanel.add(carbsLabel);
            macrosPanel.add(fatLabel);
            macrosPanel.add(caloriesLabel);
            macrosPanel.add(gramsToEatLabel);

            macrosPanel.add(totalProteinLabel);
            macrosPanel.add(totalCarbsLabel);
            macrosPanel.add(totalFatLabel);

            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    generateMealAction();
                }
            });

            macrosPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add some space before totalCaloriesLabel
        }

        /**
         * Updates the labels with the nutrition information based on the provided meal and user profile.
         *
         * @param meal The meal object containing nutritional information.
         * @param userProfile The user profile object containing user-specific information.
         */
        private void updateLabels(Meal meal, Profile userProfile) {
            double portionGrams = ((userProfile.getTdee() / userProfile.getMealsPerDay()) / meal.getKcal() * 100);
            double macrosModifier = (portionGrams / 100);
            proteinLabel.setText("Protein: " + String.format("%.2f", meal.getProtein() * macrosModifier) + "g");
            carbsLabel.setText("Carbs: " + String.format("%.2f", meal.getCarbs() * macrosModifier) + "g");
            fatLabel.setText("Fat: " + String.format("%.2f", meal.getFat() * macrosModifier) + "g");
            caloriesLabel.setText("Calories: " + String.format("%.2f", meal.getKcal() * macrosModifier) + " kcal");
            gramsToEatLabel.setText("Portion size: " + String.format("%.2f", portionGrams) + "g");
        }

        /**
         * Performs the action when the "Generate Meal" button is clicked. It generates a random meal based on the user's profile,
         * updates the labels with the nutrition information of the selected meal, calculates the total macros, and updates the meal panel.
         * If no matching meals are found, it prints a message indicating the absence of matching meals.
         */
        private void generateMealAction() {
            int tdee = userProfile.getTdee() / userProfile.getMealsPerDay();
            int protein = userProfile.getProtein() / userProfile.getMealsPerDay();
            int carbs = userProfile.getCarbs() / userProfile.getMealsPerDay();
            int fat = userProfile.getFat() / userProfile.getMealsPerDay();
            ArrayList<Meal> matchingMeals;

            totalCalories = 0;
            totalProtein = 0;
            totalCarbs = 0;
            totalFat = 0;

            if (i == 1) {
                matchingMeals = foodDatabase.findBreakfast(protein, carbs, tdee, fat, userProfile.getDietCategory());
            } else {
                matchingMeals = foodDatabase.findFood(protein, carbs, tdee, fat, userProfile.getDietCategory());
            }
            Meal randomMeal = chooseRandomMeal(matchingMeals, tdee);

            if (randomMeal != null) {
                double portionGrams = ((userProfile.getTdee() / userProfile.getMealsPerDay()) / randomMeal.getKcal() * 100);
                double macrosModifier = (portionGrams / 100);
                foodDatabase.updateMeals(userId, randomMeal.getName(), i);

                mealLabel.setText("Selected meal: " + randomMeal.getName());
                proteinLabel.setText("Protein: " + String.format("%.2f", randomMeal.getProtein() * macrosModifier) + "g");
                carbsLabel.setText("Carbs: " + String.format("%.2f", randomMeal.getCarbs() * macrosModifier) + "g");
                fatLabel.setText("Fat: " + String.format("%.2f", randomMeal.getFat() * macrosModifier) + "g");
                caloriesLabel.setText("Calories: " + String.format("%.2f", randomMeal.getKcal() * macrosModifier) + " kcal");
                gramsToEatLabel.setText("Portion size: " + String.format("%.2f", portionGrams) + "g");

                totalCalories += (int) (randomMeal.getKcal() * macrosModifier);
                totalProtein += (int) (randomMeal.getProtein() * macrosModifier);
                totalCarbs += (int) (randomMeal.getCarbs() * macrosModifier);
                totalFat += (int) (randomMeal.getFat() * macrosModifier);

                totalCaloriesLabel.setText("Total Calories: " + totalCalories);
                totalProteinLabel.setText("Total Protein: " + totalProtein + "g");
                totalCarbsLabel.setText("Total Carbs: " + totalCarbs + "g");
                totalFatLabel.setText("Total Fat: " + totalFat + "g");

                JPanel mealPanel = (JPanel) getParent().getParent();
                JPanel mealFoodsPanel = (JPanel) mealPanel.getComponent(1);
                mealFoodsPanel.removeAll();
                mealFoodsPanel.add(mealLabel);
                mealFoodsPanel.revalidate();
                mealFoodsPanel.repaint();
            } else {
                System.out.println("No matching meals found.");
            }
        }

    }

    /**
     * Chooses a random meal from the given list of meals that matches the provided
     * calorie count.
     * Calculates the recommended grams of the meal based on the calorie count and
     * sets it on the chosen meal.
     * Returns the chosen meal or null if the list is empty.
     *
     * @param matchingMeals the list of meals to choose from
     * @param kcals         the calorie count to match
     *
     * @return the chosen meal or null if the list is empty
     */
    public Meal chooseRandomMeal(ArrayList<Meal> matchingMeals, double kcals) {
        if (matchingMeals.isEmpty()) {
            return null;

        } else {
            Random rand = new Random();
            Meal randomMeal = matchingMeals.get(rand.nextInt(matchingMeals.size()));
            double grams = kcals / randomMeal.getKcal() * 100;
            randomMeal.setRecommendedGrams(String.valueOf(grams));
            return randomMeal;
        }
    }
}
