package src.Boundary;

import src.Controller.RegistrationController;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileFormGUI extends JFrame {

    private Profile profile;

    private JTextField heightField;
    private JTextField weightField;
    private JTextField ageField;
    private JComboBox<String> sexField;
    private JComboBox<String> goalField;
    private JComboBox<String> activityField;
    private JComboBox<String> carbField;
    private JComboBox<String> mealsField;

    public ProfileFormGUI(Profile userProfile) {
        super("User Profile Form");

    this.profile = userProfile;
        // Create components
        JLabel titleLabel = new JLabel("Enter Your Profile Information");
        JLabel heightLabel = new JLabel("Height (cm):");
        JLabel weightLabel = new JLabel("Weight (kg):");
        JLabel ageLabel = new JLabel("Age:");
        JLabel sexLabel = new JLabel("Sex:");
        JLabel goalLabel = new JLabel("Goal:");
        JLabel activityLabel = new JLabel("ActivityLevel: ");
        JLabel carbLabel = new JLabel("Carbohydrate Intake:");
        JLabel mealsLabel = new JLabel("Number of Meals:");

        heightField = new JTextField();
        weightField = new JTextField();
        ageField = new JTextField();
        sexField = new JComboBox<>(new String[]{"Male", "Female"});
        goalField = new JComboBox<>(new String[]{"Weight Loss", "Maintenance", "Weight Gain"});
        activityField = new JComboBox<>(new String[]{"Sedentary", "Light Exercise(1-2/Week)", "Moderate Exercise(3-5/Week)", "Heavy Exercise(6-7/Week)", "Athlete(2x/Day"});
        carbField = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        mealsField = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

        JButton submitButton = new JButton("Submit");

        // Create layout and add components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 5));
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(sexLabel);
        panel.add(sexField);
        panel.add(goalLabel);
        panel.add(goalField);
        panel.add(activityLabel);
        panel.add(activityField);
        panel.add(carbLabel);
        panel.add(carbField);
        panel.add(mealsLabel);
        panel.add(mealsField);
        panel.add(submitButton);

        // Set window properties
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        // Add submit button listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get user input from fields
                String activityValue = (String) activityField.getSelectedItem();
                int height = Integer.parseInt(heightField.getText());
                int weight = Integer.parseInt(weightField.getText());
                int age = Integer.parseInt(ageField.getText());
                String sex = (String) sexField.getSelectedItem();
                String goal = (String) goalField.getSelectedItem();
                String carbAmount = (String) carbField.getSelectedItem();
                int mealsPerDay = Integer.parseInt((String) mealsField.getSelectedItem());

                // Create Profile object with user input
                profile.addToProfile(height, weight, age, sex, goal, activityValue, carbAmount, mealsPerDay);
                RegistrationController controller = new RegistrationController();
                controller.submitProfile(userProfile);

                // Create and show ProfileDisplayGUI with user profile
                ProfileDisplayGUI displayGUI = new ProfileDisplayGUI(userProfile);
                dispose();
            }
        });
    }

    /*public static void main(String[] args) {
        ProfileFormGUI formGUI = new ProfileFormGUI();
    } */
}

