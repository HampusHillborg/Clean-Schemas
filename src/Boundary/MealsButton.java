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
 * The MealsButton class represents a GUI for displaying the user's meals and
 * macros.
 * It extends JFrame and implements ActionListener.
 * The GUI displays a panel for each meal and a panel for the user's selected
 * meal macros.
 * Each meal panel contains two buttons: "Add Meal" and "Generate Meal", which
 * can be used to add or generate a new meal.
 * The selected meal macros are displayed in the macros panel.
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

    private final List<JPanel> mealPanels = new ArrayList<>();
    private final JPanel mealsPanel = new JPanel(new GridLayout(0, 1));
    private final JPanel macrosPanel = new JPanel(new GridLayout(0, 1));
    private final JScrollPane mealsScrollPane = new JScrollPane(mealsPanel);

    private final JLabel macrosLabel = new JLabel("Total Macros for the day:");

    private UserDatabaseOutput userDatabaseOutput;

    private final JLabel totalCaloriesLabel = new JLabel();
    private final JLabel totalProteinLabel = new JLabel();
    private final JLabel totalCarbsLabel = new JLabel();
    private final JLabel totalFatLabel = new JLabel();

    /**
     * Constructs a new MealsButton object with a given user profile.
     * This method creates a GUI for meals and macros, where the meals are displayed
     * in a scrollable pane
     * and the macros are displayed in a fixed-size panel.
     * The number of meals displayed is determined by the userProfile parameter.
     * The method adds two buttons to each meal panel: an "Add Meal" button and a
     * "Generate Meal" button.
     *
     * @param userProfile  the user profile containing the number of meals per day
     *                     to be displayed
     * @param userDatabase the user database object
     */
    public MealsButton(Profile userProfile, UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        foodDatabase = new FoodDatabase(userDatabase);
        userId = userDatabase.getUserId(userProfile.getEmail());
        setTitle("Meals GUI");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupMealsPanel(userProfile);
        setupMacrosPanel(userProfile);
        createSplitPane();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupMealsPanel(Profile userProfile) {
        mealsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mealsPanel.setBackground(Color.WHITE);
        mealsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mealsScrollPane.setPreferredSize(new Dimension(400, 500));

        for (int i = 0; i < userProfile.getMealsPerDay(); i++) {
            JPanel mealPanel = createMealPanel(i, userProfile);
            mealPanels.add(mealPanel);
            mealsPanel.add(mealPanel);
        }
    }

    private JPanel createMealPanel(int index, Profile userProfile) {
        JPanel mealPanel = new JPanel(new BorderLayout());
        TitledBorder titledBorder;
        if (index == 0) {
            titledBorder = BorderFactory.createTitledBorder("Breakfast");
        } else {
            titledBorder = BorderFactory.createTitledBorder("Meal " + (index + 1));
        }
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        mealPanel.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10), titledBorder));

        GenerateMealButton generateMealButton = new GenerateMealButton(userProfile, foodDatabase, index + 1);
        GenerateRecipeButton generateRecipeButton = new GenerateRecipeButton(userProfile, foodDatabase, index + 1);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(generateMealButton);
        buttonPanel.add(generateRecipeButton);

        JPanel mealFoodsPanel = new JPanel();
        mealPanel.add(buttonPanel, BorderLayout.NORTH);
        mealPanel.add(mealFoodsPanel, BorderLayout.CENTER);

        if (foodDatabase.getSavedMeal(userId, index + 1) != null) {
            JLabel selectedMealLabel = new JLabel("Last saved meal: " + foodDatabase.getSavedMeal(userId, index + 1));
            mealFoodsPanel.add(selectedMealLabel);
        }

        return mealPanel;
    }

    private void setupMacrosPanel(Profile userProfile) {
        macrosPanel.setBorder(BorderFactory.createTitledBorder("Selected Meal Macros"));
        macrosPanel.setBackground(Color.LIGHT_GRAY);
        macrosPanel.setPreferredSize(new Dimension(300, 500));

        macrosPanel.setBackground(Color.LIGHT_GRAY);
        macrosPanel.setPreferredSize(new Dimension(300, 500));
        macrosPanel.add(macrosLabel);
        macrosPanel.add(totalProteinLabel);
        macrosPanel.add(totalCarbsLabel);
        macrosPanel.add(totalFatLabel);

        JPanel totalCaloriesPanel = new JPanel();
        totalCaloriesPanel.setBackground(Color.LIGHT_GRAY);
        totalCaloriesPanel.setPreferredSize(new Dimension(300, 50));
        totalCaloriesPanel.add(totalCaloriesLabel);

        BoxLayout macrosBoxLayout = new BoxLayout(macrosPanel, BoxLayout.Y_AXIS);
        macrosPanel.setLayout(macrosBoxLayout);

        macrosPanel.add(Box.createVerticalGlue());
        macrosPanel.add(totalCaloriesPanel);
    }

    private void createSplitPane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mealsScrollPane, macrosPanel);
        splitPane.setDividerLocation(400);

        getContentPane().add(splitPane);
        getContentPane().add(splitPane);

        Border mealsPanelBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mealsPanel.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), mealsPanelBorder));

        Border macrosPanelBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        macrosPanel.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), macrosPanelBorder));
    }

    private class GenerateRecipeButton extends JButton {

        private final Color buttonColor = new Color(59, 89, 152);

        private Profile userProfile;
        private FoodDatabase foodDatabase;
        private int mealIndex;
        private JFrame recipeFrame;
        private JLabel recipeLabel;
        private JLabel recipeName;
        private double macrosModifier;

        public GenerateRecipeButton(Profile userProfile, FoodDatabase foodDatabase, int mealIndex) {
            this.mealIndex = mealIndex;
            Meal meal = null;

            setText("Generate Recipe");
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
            this.userProfile = userProfile;
            this.foodDatabase = foodDatabase;
            if (foodDatabase.getSavedMeal(userId, mealIndex) != null) {
                if (mealIndex == 1) {
                    meal = foodDatabase.getBreakfastFromName(foodDatabase.getSavedMeal(userId, mealIndex));
                } else {
                    meal = foodDatabase.getFoodFromName(foodDatabase.getSavedMeal(userId, mealIndex));
                }
                double portionGrams = ((userProfile.getTdee() / userProfile.getMealsPerDay()) / meal.getKcal() * 100);
                macrosModifier = (portionGrams / 100);
            }

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String[]> recipe = foodDatabase.getRecipe(foodDatabase.getSavedMeal(userId, mealIndex));

                    StringBuilder recipeText = new StringBuilder();
                    for (String[] ingredientEntry : recipe) {
                        String ingredient = ingredientEntry[0];
                        double amount = Double.valueOf(ingredientEntry[1]) * macrosModifier;
                        String label = ingredientEntry[2];
                        recipeText.append(ingredient).append(": ").append(String.format("%.1f", amount)).append(label)
                                .append("<br>");
                    }

                    if (recipeText.length() > 0) {
                        recipeFrame = new JFrame("Recipe");
                        recipeName = new JLabel("Recipe for " + foodDatabase.getSavedMeal(userId, mealIndex));
                        recipeLabel = new JLabel("<html>" + recipeText.toString() + "</html>");

                        recipeFrame.getContentPane().setLayout(new FlowLayout());
                        recipeFrame.getContentPane().add(recipeName);
                        recipeFrame.getContentPane().add(recipeLabel);

                        recipeFrame.setSize(400, 200);
                        recipeFrame.setLocationRelativeTo(null);
                        recipeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        recipeFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No recipe found for this meal.", "Recipe Not Found",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
        }
    }

    private class GenerateMealButton extends JButton {

        private final Color buttonColor = new Color(59, 89, 152);

        private Profile userProfile;
        private FoodDatabase foodDatabase;
        private int mealIndex;

        private int totalCalories = 0;
        private int totalProtein = 0;
        private int totalCarbs = 0;
        private int totalFat = 0;

        private JLabel proteinLabel = new JLabel("Protein:");
        private JLabel carbsLabel = new JLabel("Carbs:");
        private JLabel fatLabel = new JLabel("Fat:");
        private JLabel caloriesLabel = new JLabel("Calories:");
        private JLabel gramsToEatLabel = new JLabel("Portion size");
        private JLabel mealLabel = new JLabel();

        public GenerateMealButton(Profile userProfile, FoodDatabase foodDatabase, int mealIndex) {
            this.mealIndex = mealIndex;
            Meal meal = null;

            setText("Generate Meal");
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
            this.userProfile = userProfile;
            this.foodDatabase = foodDatabase;
            if (foodDatabase.getSavedMeal(userId, mealIndex) != null) {
                if (mealIndex == 1) {
                    meal = foodDatabase.getBreakfastFromName(foodDatabase.getSavedMeal(userId, mealIndex));
                } else {
                    meal = foodDatabase.getFoodFromName(foodDatabase.getSavedMeal(userId, mealIndex));
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

        private void updateLabels(Meal meal, Profile userProfile) {
            double portionGrams = ((userProfile.getTdee() / userProfile.getMealsPerDay()) / meal.getKcal() * 100);
            double macrosModifier = (portionGrams / 100);
            proteinLabel.setText("Protein: " + String.format("%.2f", meal.getProtein() * macrosModifier) + "g");
            carbsLabel.setText("Carbs: " + String.format("%.2f", meal.getCarbs() * macrosModifier) + "g");
            fatLabel.setText("Fat: " + String.format("%.2f", meal.getFat() * macrosModifier) + "g");
            caloriesLabel.setText("Calories: " + String.format("%.2f", meal.getKcal() * macrosModifier) + " kcal");
            gramsToEatLabel.setText("Portion size: " + String.format("%.2f", portionGrams) + "g");
        }

        private void generateMealAction() {
            int tdee = userProfile.getTdee() / userProfile.getMealsPerDay();
            int protein = userProfile.getProtein() / userProfile.getMealsPerDay();
            int carbs = userProfile.getCarbs() / userProfile.getMealsPerDay();
            int fat = userProfile.getFat() / userProfile.getMealsPerDay();
            ArrayList<Meal> matchingMeals;

            if (mealIndex == 1) {
                matchingMeals = foodDatabase.findBreakfast(protein, carbs, tdee, fat, userProfile.getDietCategory());
            } else {
                matchingMeals = foodDatabase.findFood(protein, carbs, tdee, fat, userProfile.getDietCategory());
            }
            Meal randomMeal = chooseRandomMeal(matchingMeals, tdee);

            if (randomMeal != null) {
                double portionGrams = ((userProfile.getTdee() / userProfile.getMealsPerDay()) / randomMeal.getKcal()
                        * 100);
                double macrosModifier = (portionGrams / 100);
                foodDatabase.updateMeals(userId, randomMeal.getName(), mealIndex);

                mealLabel.setText("Selected meal: " + randomMeal.getName());
                proteinLabel
                        .setText("Protein: " + String.format("%.2f", randomMeal.getProtein() * macrosModifier) + "g");
                carbsLabel.setText("Carbs: " + String.format("%.2f", randomMeal.getCarbs() * macrosModifier) + "g");
                fatLabel.setText("Fat: " + String.format("%.2f", randomMeal.getFat() * macrosModifier) + "g");
                caloriesLabel
                        .setText("Calories: " + String.format("%.2f", randomMeal.getKcal() * macrosModifier) + " kcal");
                gramsToEatLabel.setText("Portion size: " + String.format("%.2f", portionGrams) + "g");

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
