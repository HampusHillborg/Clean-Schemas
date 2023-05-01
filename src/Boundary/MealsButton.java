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
            AddButton addButton = new AddButton(i+1);
            GenerateMealButton generateMealButton = new GenerateMealButton();
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            buttonPanel.add(addButton);
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
    

    private class AddButton extends JButton {
        private int number;
        private final Color buttonColor = new Color(59, 89, 152);
    
        public AddButton(int number) {
            this.number = number;
            setText("Add Meal " + number);
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
    
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action to be performed when "Add Meal" button is clicked
                    System.out.println("Add Meal button clicked in meal panel ");
                    String userInput = JOptionPane.showInputDialog(null, "Ange en måltid eller ingrediens som du vill ha i din måltid");
                    Food food = nutritionAPI.createFood(userInput);
                    JPanel mealPanel = mealPanels.get(number - 1);
                    JPanel foodPanel = new JPanel(new GridLayout(0, 1));
                    foodPanel.setBackground(Color.WHITE);
                    foodPanel.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createEmptyBorder(5, 5, 5, 5),
                            BorderFactory.createLineBorder(buttonColor, 2)));
                    JLabel nameLabel = new JLabel(food.getLivsmedelsNamn());
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    foodPanel.add(nameLabel);
                    foodPanel.add(new JLabel(food.getEnergiKcal() + " kcal"));
                    foodPanel.add(new JLabel("Protein: " + food.getProtein() + " g"));
                    foodPanel.add(new JLabel("Kolhydrater: " + food.getKolhydrater() + " g"));
                    foodPanel.add(new JLabel("Fett: " + food.getFett() + " g"));
                    JPanel mealFoodsPanel = null;
                    for (Component c : mealPanel.getComponents()) {
                        if (c instanceof JPanel) {
                            mealFoodsPanel = (JPanel) c;
                            break;
                        }
                    }
                    mealFoodsPanel.setLayout(new BoxLayout(mealFoodsPanel, BoxLayout.Y_AXIS));
                    mealFoodsPanel.add(foodPanel);
                    mealFoodsPanel.add(Box.createRigidArea(new Dimension(0, 5))); // add some space after the food panel
                    mealFoodsPanel.add(new JSeparator(SwingConstants.HORIZONTAL)); // add separator
                    revalidate();
                    repaint();
                    JOptionPane.showMessageDialog(null, "<html>Näringsvärde för " + food.getLivsmedelsNamn() +
                            "<br>" + food.getEnergiKcal() + " kcal<br>" +
                            "Protein: " + food.getProtein() + " g<br>" +
                            "Kolhydrater: " + food.getKolhydrater() + " g<br>" +
                            "Fett: " + food.getFett() + " g</html>");
                }
            });
        }
    }
    
    
    private class GenerateMealButton extends JButton {
        private int totalCalories = 0;
        private final JLabel totalCaloriesLabel = new JLabel();
        private final Color buttonColor = new Color(59, 89, 152);
    
        public GenerateMealButton() {
            setText("Generate Meal");
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorder(BorderFactory.createLineBorder(buttonColor, 2));
            setPreferredSize(new Dimension(150, 30));
    
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action to be performed when "Generate Meal" button is clicked
                    System.out.println("Generate Meal button clicked in meal panel ");
                    Meal meal = new Meal("Chicken and Broccoli", 360, 30, 10, 50);
                    String mealData = "<html><table cellpadding='5'>" +
                            "<tr><td><b>Meal:</b></td><td>" + meal.getName() + "</td></tr>" +
                            "<tr><td><b>Calories:</b></td><td>" + meal.getCalories() + " kcal</td></tr>" +
                            "<tr><td><b>Fat:</b></td><td>" + meal.getFat() + " g</td></tr>" +
                            "<tr><td><b>Carbs:</b></td><td>" + meal.getCarbs() + " g</td></tr>" +
                            "<tr><td><b>Protein:</b></td><td>" + meal.getProtein() + " g</td></tr>" +
                            "</table></html>";
                    JTextPane selectedMealTextPane = new JTextPane();
                    selectedMealTextPane.setContentType("text/html");
                    selectedMealTextPane.setText(mealData);
                    selectedMealTextPane.setEditable(false);
                    macrosPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add padding
                    macrosPanel.add(macrosLabel);
                    macrosPanel.add(selectedMealTextPane, 0); // add to the top
                    macrosPanel.add(new JSeparator(SwingConstants.HORIZONTAL)); // add separator
                    macrosPanel.revalidate();
                    macrosPanel.repaint();
                    totalCalories += meal.getCalories();
                    totalCaloriesLabel.setText("Total calories for the day: " + totalCalories + " kcal");
                }
            });
            macrosPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add some space before totalCaloriesLabel
            macrosPanel.add(totalCaloriesLabel);
        }
    }
    
}