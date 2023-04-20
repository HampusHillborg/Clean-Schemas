package src.Boundary;

import src.Database.UserDatabaseOutput;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MealsButton extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final List<JPanel> mealPanels = new ArrayList<>();
    private final JPanel mealsPanel = new JPanel(new GridLayout(0, 1));
    private final JPanel macrosPanel = new JPanel(new GridLayout(0, 1));
    private final JScrollPane mealsScrollPane = new JScrollPane(mealsPanel);
    private final JButton addMealButton = new JButton("Add Meal");
    private final JButton generateMealButton = new JButton("Generate Meal");
    private final JLabel macrosLabel = new JLabel("Selected Meal Macros:");
    private UserDatabaseOutput userDatabaseOutput;

    public MealsButton(Profile userProfile) {
        setTitle("Meals GUI");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the meals panel with scrollable layout
        mealsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mealsScrollPane.setPreferredSize(new Dimension(400, 500));


        // Add the meal panels to the meals panel
        for (int i = 0; i < userProfile.getMealsPerDay(); i++) { // Replace 10 with the number of meals per day from the database
            JPanel mealPanel = new JPanel();
            mealPanel.setBorder(BorderFactory.createTitledBorder("Meal " + (i + 1)));
            mealPanel.setLayout(new BoxLayout(mealPanel, BoxLayout.Y_AXIS));
            mealsPanel.add(mealPanel);
            mealPanels.add(mealPanel);

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

        // Create a split pane with the meals panel on the left and the macros panel on the right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mealsScrollPane, macrosPanel);
        splitPane.setDividerLocation(400);

        // Add the split pane to the frame
        getContentPane().add(splitPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setVisible(true);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Profile userProfile = new Profile("Andreas", "123");
            new MealsButton(userProfile);
        });
    }

     */
}