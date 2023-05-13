package src.Boundary;

import src.Controller.LoginController;
import src.Controller.RegistrationController;
import src.Database.UserDatabase;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayProfile extends JFrame {

    private Profile profile;

    private JTextField heightField;
    private JTextField weightField;
    private JTextField ageField;
    private JComboBox<String> sexField;
    private JComboBox<String> goalField;
    private JComboBox<String> activityField;
    private JComboBox<String> carbField;
    private JComboBox<String> mealsField;
    private UserDatabase userDatabase;

    public DisplayProfile(Profile profile, UserDatabase userDatabase) {
        super("User Profile Form");
        this.userDatabase = userDatabase;
        this.profile = profile;

        initializeComponents();
        createLayout();
        setupSubmitButtonListener();

        // Set window properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        JLabel titleLabel = new JLabel("Enter Your Profile Information");
        JLabel heightLabel = new JLabel("Height (cm):");
        JLabel weightLabel = new JLabel("Weight (kg):");
        JLabel ageLabel = new JLabel("Age:");
        JLabel sexLabel = new JLabel("Sex:");
        JLabel goalLabel = new JLabel("Goal:");
        JLabel activityLabel = new JLabel("Activity Level: ");
        JLabel carbLabel = new JLabel("Carbohydrate Intake:");
        JLabel mealsLabel = new JLabel("Number of Meals:");

        heightField = new JTextField();
        weightField = new JTextField();
        ageField = new JTextField();
        sexField = new JComboBox<>(new String[] { "Male", "Female" });
        goalField = new JComboBox<>(new String[] { "Weight Loss", "Maintenance", "Weight Gain" });
        activityField = new JComboBox<>(new String[] { "Sedentary", "Light Exercise(1-2/Week)",
                "Moderate Exercise(3-5/Week)", "Heavy Exercise(6-7/Week)", "Athlete(2x/Day)" });
        carbField = new JComboBox<>(new String[] { "Low", "Medium", "High" });
        mealsField = new JComboBox<>(new String[] { "1", "2", "3", "4", "5" });

        heightField.setText(Double.toString(profile.getHeight()));
        weightField.setText(Double.toString(profile.getWeight()));
        ageField.setText(Integer.toString(profile.getAge()));
        sexField.setSelectedItem(profile.getSex());
        goalField.setSelectedItem(profile.getGoal());
        activityField.setSelectedItem(profile.getActivityValue());
        carbField.setSelectedItem(profile.getCarbAmount());
        mealsField.setSelectedItem(Integer.toString(profile.getMealsPerDay()));
    }

    private void createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));
        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Sex:"));
        panel.add(sexField);
        panel.add(new JLabel("Goal:"));
        panel.add(goalField);
        panel.add(new JLabel("Activity Level:"));
        panel.add(activityField);
        panel.add(new JLabel("Carbohydrate Intake:"));
        panel.add(carbField);
        panel.add(new JLabel("Number of Meals:"));
        panel.add(mealsField);

        setLayout(new BorderLayout());
        add(new JLabel("Enter Your Profile Information"), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void setupSubmitButtonListener() {
        JButton submitButton = new JButton("Submit");

        // Use a shade of blue that works well on Mac
        Color macBlue = new Color(54, 144, 246);

        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(macBlue);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.setOpaque(true);

        // added to remove border
        submitButton.setBorderPainted(false); // added to remove border
        submitButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                int age = Integer.parseInt(ageField.getText());
                String sex = (String) sexField.getSelectedItem();
                String goal = (String) goalField.getSelectedItem();
                String activityValue = (String) activityField.getSelectedItem();
                String carbAmount = (String) carbField.getSelectedItem();
                int mealsPerDay = Integer.parseInt((String) mealsField.getSelectedItem());

                profile.addToProfile(height, weight, age, sex, goal, activityValue, carbAmount, mealsPerDay);

                LoginController controller = new LoginController(userDatabase);
                controller.updateProfile(profile);

                ProfileDisplayGUI displayGUI = new ProfileDisplayGUI(profile);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(DisplayProfile.this,
                        "Invalid input! Please enter valid numerical values.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DisplayProfile.this, "An error occurred while updating the profile.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
    }
}
