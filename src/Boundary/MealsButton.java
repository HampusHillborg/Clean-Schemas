package src.Boundary;

import src.API.NutritionAPI;
import src.Database.FoodDatabase;
import src.Database.UserDatabase;
import src.Database.UserDatabaseOutput;
import src.Entity.Food;
import src.Entity.Meal;
import src.Entity.Profile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private final JLabel macrosLabel = new JLabel("Selected Meal Macros:");
    private UserDatabaseOutput userDatabaseOutput;

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
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Meal " + (i + 1));
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
            mealPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), titledBorder));
            GenerateMealButton generateMealButton = new GenerateMealButton(userProfile, foodDatabase);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            buttonPanel.add(generateMealButton);
            mealPanel.add(buttonPanel, BorderLayout.NORTH);
            JPanel mealFoodsPanel = new JPanel();
            mealPanel.add(mealFoodsPanel, BorderLayout.CENTER);
            mealPanels.add(mealPanel);
            mealsPanel.add(mealPanel);
        }
        
        // Create the macros panel
        macrosPanel.setBorder(BorderFactory.createTitledBorder("Selected Meal Macros"));
        macrosPanel.setBackground(Color.LIGHT_GRAY);
        macrosPanel.setPreferredSize(new Dimension(300, 500));
        macrosPanel.add(macrosLabel);
    
        // Create a split pane with the meals panel on the left and the macros panel on
        // the right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mealsScrollPane, macrosPanel);
        splitPane.setDividerLocation(400);
    
        // Add the split pane to the frame
        getContentPane().add(splitPane);
    
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private class GenerateMealButton extends JButton {
        private int totalCalories = 0;
        private final JLabel totalCaloriesLabel = new JLabel();
        private final Color buttonColor = new Color(59, 89, 152);

        private Profile userProfile;
        private FoodDatabase foodDatabase;

        public GenerateMealButton(Profile userProfile, FoodDatabase foodDatabase) {
            setText("Generate Meal");
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
            this.userProfile = userProfile;
            this.foodDatabase = foodDatabase;

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action to be performed when "Generate Meal" button is clicked
                    System.out.println("Generate Meal button clicked in meal panel ");
                    int tdee = userProfile.getTdee()/userProfile.getMealsPerDay();
                    int protein = userProfile.getProtein()/userProfile.getMealsPerDay();
                    int carbs = userProfile.getCarbs()/userProfile.getMealsPerDay();
                    int fat = userProfile.getFat()/userProfile.getMealsPerDay();
                    //foodDatabase.findFittingMeal(tdee, protein, carbs, fat);
                    foodDatabase.getMeal("normal",tdee);

                    /*
                    // Adding more meals to generate meals button
                    Meal meal1 = new Meal("Chicken and Broccoli", 360, 30, 10, 50);
                    Meal meal2 = new Meal("Spinach and Mushroom Omelette with Whole Wheat Toast",360,17,26,25);
                    Meal meal3 = new Meal("Grilled Salmon with Sweet Potato and Grilled Asparagus", 500,24,30,40);
                    Meal meal4 = new Meal("Tuna Salad with Mixed Greens and Whole Wheat Crackers", 320,12,22,30);
                    Meal meal5 = new Meal("Beef Stir-Fry with Brown Rice and Mixed Vegetables", 550,20,50,40);


                    String mealData1 = "<html><table cellpadding='5'>" +
                            "<tr><td><b>Meal:</b></td><td>" + meal1.getName() + "</td></tr>" +
                            "<tr><td><b>Calories:</b></td><td>" + meal1.getCalories() + " kcal</td></tr>" +
                            "<tr><td><b>Fat:</b></td><td>" + meal1.getFat() + " g</td></tr>" +
                            "<tr><td><b>Carbs:</b></td><td>" + meal1.getCarbs() + " g</td></tr>" +
                            "<tr><td><b>Protein:</b></td><td>" + meal1.getProtein() + " g</td></tr>" +
                            "</table></html>";

                    String mealData2 = "<html><table cellpadding='5'>" +
                            "<tr><td><b>Meal:</b></td><td>" + meal2.getName() + "</td></tr>" +
                            "<tr><td><b>Calories:</b></td><td>" + meal2.getCalories() + " kcal</td></tr>" +
                            "<tr><td><b>Fat:</b></td><td>" + meal2.getFat() + " g</td></tr>" +
                            "<tr><td><b>Carbs:</b></td><td>" + meal2.getCarbs() + " g</td></tr>" +
                            "<tr><td><b>Protein:</b></td><td>" + meal2.getProtein() + " g</td></tr>" +
                            "</table></html>";

                    String mealData3 = "<html><table cellpadding='5'>" +
                            "<tr><td><b>Meal:</b></td><td>" + meal3.getName() + "</td></tr>" +
                            "<tr><td><b>Calories:</b></td><td>" + meal3.getCalories() + " kcal</td></tr>" +
                            "<tr><td><b>Fat:</b></td><td>" + meal3.getFat() + " g</td></tr>" +
                            "<tr><td><b>Carbs:</b></td><td>" + meal3.getCarbs() + " g</td></tr>" +
                            "<tr><td><b>Protein:</b></td><td>" + meal3.getProtein() + " g</td></tr>" +
                            "</table></html>";

                    String mealData4 = "<html><table cellpadding='5'>" +
                            "<tr><td><b>Meal:</b></td><td>" + meal4.getName() + "</td></tr>" +
                            "<tr><td><b>Calories:</b></td><td>" + meal4.getCalories() + " kcal</td></tr>" +
                            "<tr><td><b>Fat:</b></td><td>" + meal4.getFat() + " g</td></tr>" +
                            "<tr><td><b>Carbs:</b></td><td>" + meal4.getCarbs() + " g</td></tr>" +
                            "<tr><td><b>Protein:</b></td><td>" + meal4.getProtein() + " g</td></tr>" +
                            "</table></html>";

                    String mealData5 = "<html><table cellpadding='5'>" +
                            "<tr><td><b>Meal:</b></td><td>" + meal5.getName() + "</td></tr>" +
                            "<tr><td><b>Calories:</b></td><td>" + meal5.getCalories() + " kcal</td></tr>" +
                            "<tr><td><b>Fat:</b></td><td>" + meal5.getFat() + " g</td></tr>" +
                            "<tr><td><b>Carbs:</b></td><td>" + meal5.getCarbs() + " g</td></tr>" +
                            "<tr><td><b>Protein:</b></td><td>" + meal5.getProtein() + " g</td></tr>" +
                            "</table></html>";


                    JTextPane selectedMealTextPane1 = new JTextPane();
                    selectedMealTextPane1.setContentType("text/html");
                    selectedMealTextPane1.setText(mealData1);
                    selectedMealTextPane1.setEditable(false);

                    JTextPane selectedMealTextPane2 = new JTextPane();
                    selectedMealTextPane2.setContentType("text/html");
                    selectedMealTextPane2.setText(mealData2);
                    selectedMealTextPane2.setEditable(false);

                    JTextPane selectedMealTextPane3 = new JTextPane();
                    selectedMealTextPane3.setContentType("text/html");
                    selectedMealTextPane3.setText(mealData2);
                    selectedMealTextPane3.setEditable(false);

                    JTextPane selectedMealTextPane4 = new JTextPane();
                    selectedMealTextPane4.setContentType("text/html");
                    selectedMealTextPane4.setText(mealData2);
                    selectedMealTextPane4.setEditable(false);

                    JTextPane selectedMealTextPane5 = new JTextPane();
                    selectedMealTextPane5.setContentType("text/html");
                    selectedMealTextPane5.setText(mealData2);
                    selectedMealTextPane5.setEditable(false);


                    macrosPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add padding
                    macrosPanel.add(macrosLabel);
                    macrosPanel.add(selectedMealTextPane1, 0); // add to the top
                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL)); // add separator

                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
                    macrosPanel.add(selectedMealTextPane2,0);

                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
                    macrosPanel.add(selectedMealTextPane3,0);

                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
                    macrosPanel.add(selectedMealTextPane4,0);

                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
                    macrosPanel.add(selectedMealTextPane5,0);
                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL));



                    // Revalidate and repaint the macrosPanel
                    macrosPanel.revalidate();
                    macrosPanel.repaint();


                    totalCalories += meal1.getCalories();
                    totalCaloriesLabel.setText("Total calories for the day: " + totalCalories + " kcal");

                     */
                }
            });
            macrosPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add some space before totalCaloriesLabel
            macrosPanel.add(totalCaloriesLabel);
        }
    }

    }

