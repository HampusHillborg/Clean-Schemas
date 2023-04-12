package src.Boundary;

import src.Entity.Profile;

import javax.swing.*;

public class ProfileDisplayGUI extends JFrame {
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JLabel ageLabel;
    private JLabel sexLabel;
    private JLabel goalLabel;
    private JLabel carbLabel;
    private JLabel mealsLabel;


    public ProfileDisplayGUI(Profile userProfile) {
        super("User Profile");

        // Initialize labels with user input
        heightLabel = new JLabel("Height (cm): ");
        weightLabel = new JLabel("Weight (kg): ");
        ageLabel = new JLabel("Age: ");
        sexLabel = new JLabel("Sex: ");
        goalLabel = new JLabel("Goal: ");
        carbLabel = new JLabel("Carbohydrate Intake: ");
        mealsLabel = new JLabel("Number of Meals: ");

        // Create layout and add components
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(heightLabel);
        add(weightLabel);
        add(ageLabel);
        add(sexLabel);
        add(goalLabel);
        add(carbLabel);
        add(mealsLabel);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create dummy Profile object for testing
       // Profile userProfile = new Profile(170, 70, 25, "Male", "Weight Loss", "Medium", 3);

        // Create ProfileFormGUI object to get user input
        //ProfileFormGUI formGUI = new ProfileFormGUI();


    }
}

