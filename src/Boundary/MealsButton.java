package src.Boundary;

import src.API.NutritionAPI;
import src.Database.UserDatabaseOutput;
import src.Entity.Food;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**

 The MealsButton class represents a GUI for displaying the user's meals and
 macros. It extends JFrame and implements ActionListener.
 The GUI displays a panel for each meal and a panel for the user's selected
 meal macros. Each meal panel contains two buttons: "Add Meal" and "Generate
 Meal", which can be used to add or generate a new meal. The selected meal
 macros are displayed in the macros panel.
 The class has a constructor that takes a userProfile as a parameter and sets
 up the GUI accordingly.
 The class also contains a list of meal panels and a userDatabaseOutput object
 that is used to access the user's meals and macros.
 The class is located in the src.Boundary package.
 */
public class MealsButton extends JFrame {
    private NutritionAPI nutritionAPI = new NutritionAPI();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final List<JPanel> mealPanels = new ArrayList<>();
    private final JPanel mealsPanel = new JPanel(new GridLayout(0, 1));
    private final JPanel macrosPanel = new JPanel(new GridLayout(0, 1));
    private final JScrollPane mealsScrollPane = new JScrollPane(mealsPanel);
    // private final JButton addMealButton = new JButton("Add Meal");
    // private final JButton generateMealButton = new JButton("Generate Meal");
    private final JLabel macrosLabel = new JLabel("Selected Meal Macros:");
    private UserDatabaseOutput userDatabaseOutput;

    /**
     Constructs a new MealsButton object with a given user profile.
     This method creates a GUI for meals and macros, where the meals
     are displayed in a scrollable pane and the macros are displayed
     in a fixed-size panel. The number of meals displayed is determined
     by the userProfile parameter. The method adds two buttons to each
     meal panel: an "Add Meal" button and a "Generate Meal" button.
     @param userProfile the user profile containing the number of meals per day to be displayed
     */
    public MealsButton(Profile userProfile) {
        setTitle("Meals GUI");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the meals panel with scrollable layout
        mealsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mealsScrollPane.setPreferredSize(new Dimension(400, 500));

        // Add the meal panels to the meals panel
        for (int i = 0; i < userProfile.getMealsPerDay(); i++) { // Replace 10 with the number of meals per day from the
                                                                 // database
            JPanel mealPanel = new JPanel();
            mealPanel.setBorder(BorderFactory.createTitledBorder("Meal " + (i + 1)));
            mealPanel.setLayout(new BoxLayout(mealPanel, BoxLayout.Y_AXIS));
            mealsPanel.add(mealPanel);
            mealPanels.add(mealPanel);
            JButton addMealButton = new JButton("Add Meal");
            addMealButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action to be performed when "Add Meal" button is clicked
                    System.out.println("Add Meal button clicked in meal panel ");
                    String userInput = JOptionPane.showInputDialog(null, "Ange en måltid eller ingrediens som du vill ha i din måltid");
                    Food food = nutritionAPI.createFood(userInput);
                    JOptionPane.showMessageDialog(null, "Näringsvärde för " + food.getLivsmedelsNamn()+ "\n" + food.getEnergiKcal() + " kcal\n" +
                    food.getFett() +" gram fett\n" + food.getKolhydrater() + " gram kolhydrater\n" + food.getProtein() + " gram protein");
                }
            });
            JButton generateMealButton = new JButton("Generate Meal");
            generateMealButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action to be performed when "Generate Meal" button is clicked
                    System.out.println("Generate Meal button clicked in meal panel ");

                }
            });

            // Add the Add and Generate buttons to the meal panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.add(addMealButton);
            buttonPanel.add(generateMealButton);
            mealPanel.add(buttonPanel);

            // Add a separator to the meal panel
            mealPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        // Create the macros panel
        macrosPanel.setBorder(BorderFactory.createTitledBorder("Macros"));
        macrosPanel.setPreferredSize(new Dimension(300, 500));

        // Add the macros label to the macros panel
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

    /*
     * public static void main(String[] args) {
     * SwingUtilities.invokeLater(() -> {
     * Profile userProfile = new Profile("Andreas", "123");
     * new MealsButton(userProfile);
     * });
     * }
     * 
     */
}