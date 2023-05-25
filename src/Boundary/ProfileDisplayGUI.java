package src.Boundary;

import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;

/**
 * A class that creates a graphical user interface for displaying a user's profile.
 */
public class ProfileDisplayGUI extends JFrame {
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JLabel ageLabel;
    private JLabel sexLabel;
    private JLabel goalLabel;
    private JLabel carbLabel;
    private JLabel mealsLabel;
    private JLabel tdeeLabel;

    /**
     * A class that creates a graphical user interface for displaying a user's profile.
     *
     * @param userProfile the user profile object to be displayed
     */
    public ProfileDisplayGUI(Profile userProfile) {
        super("User Profile");

        // Initialize labels with user input
        heightLabel = createLabel("Height (cm): " + userProfile.getHeight());
        weightLabel = createLabel("Weight (kg): " + userProfile.getWeight());
        ageLabel = createLabel("Age: " + userProfile.getAge());
        sexLabel = createLabel("Sex: " + userProfile.getSex());
        goalLabel = createLabel("Goal: " + userProfile.getGoal());
        carbLabel = createLabel("Carbohydrate Intake: " + userProfile.getCarbAmount());
        mealsLabel = createLabel("Number of Meals: " + userProfile.getMealsPerDay());
        tdeeLabel = createLabel("You need this amount of Kcals: " + userProfile.getTdee() + " Kcals");

        // Create panel and set layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        // Add labels to the panel with proper layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(heightLabel, gbc);
        panel.add(weightLabel, gbc);
        panel.add(ageLabel, gbc);
        panel.add(sexLabel, gbc);
        panel.add(goalLabel, gbc);
        panel.add(carbLabel, gbc);
        panel.add(mealsLabel, gbc);
        panel.add(tdeeLabel, gbc);

        // Set panel as the content pane
        setContentPane(panel);

        // Set window properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Helper method to create a formatted label.
     *
     * @param text the label text
     * @return the formatted label
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    public static void main(String[] args) {
        // Create dummy Profile object for testing
        // Profile userProfile = new Profile(170, 70, 25, "Male", "Weight Loss",
        // "Medium", 3);

        // Create ProfileFormGUI object to get user input
        // ProfileFormGUI formGUI = new ProfileFormGUI();
    }
}
