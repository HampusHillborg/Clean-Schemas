package src;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class ProfileFormGUI extends JFrame {
        // Text fields for user input
        private JTextField heightField;
        private JTextField weightField;
        private JTextField ageField;
        private JComboBox<String> sexBox;
        private JComboBox<String> goalBox;
        private JComboBox<String> carbBox;
        private JTextField mealsField;

        // Labels for input fields
        private JLabel heightLabel;
        private JLabel weightLabel;
        private JLabel ageLabel;
        private JLabel sexLabel;
        private JLabel goalLabel;
        private JLabel carbLabel;
        private JLabel mealsLabel;

        // Button for submitting input
        private JButton submitButton;

        public ProfileFormGUI() {
            super("User Profile Form");

            // Initialize text fields
            heightField = new JTextField(10);
            weightField = new JTextField(10);
            ageField = new JTextField(10);
            sexBox = new JComboBox<>(new String[]{"Male", "Female"});
            goalBox = new JComboBox<>(new String[]{"Weight Loss", "Weight Gain"});
            carbBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
            mealsField = new JTextField(10);

            // Initialize labels
            heightLabel = new JLabel("Height (cm): ");
            weightLabel = new JLabel("Weight (kg): ");
            ageLabel = new JLabel("Age: ");
            sexLabel = new JLabel("Sex: ");
            goalLabel = new JLabel("Goal: ");
            carbLabel = new JLabel("Carbohydrate Intake: ");
            mealsLabel = new JLabel("Number of Meals: ");

            // Initialize submit button
            submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get user input from text fields and combo boxes
                    int height = Integer.parseInt(heightField.getText());
                    int weight = Integer.parseInt(weightField.getText());
                    int age = Integer.parseInt(ageField.getText());
                    String sex = (String) sexBox.getSelectedItem();
                    String goal = (String) goalBox.getSelectedItem();
                    String carbAmount = (String) carbBox.getSelectedItem();
                    int mealsPerDay = Integer.parseInt(mealsField.getText());



                    // Create new Profile object and set user input
                    Profile userProfile = new Profile(height, weight, age, sex, goal, carbAmount, mealsPerDay /*favoriteFoods, dislikedFoods*/);
                    userProfile.setHeight(height);
                    userProfile.setWeight(weight);
                    userProfile.setAge(age);
                    userProfile.setSex(sex);
                    userProfile.setGoal(goal);
                    userProfile.setCarbAmount(carbAmount);
                    userProfile.setMealsPerDay(mealsPerDay);

                    // Save user input to database or perform other actions as needed

                    // Show message to confirm submission
                    JOptionPane.showMessageDialog(null, "Profile submitted!");
                }
            });

            // Create layout and add components
            setLayout(new GridLayout(8, 2, 10, 10));
            add(heightLabel);
            add(heightField);
            add(weightLabel);
            add(weightField);
            add(ageLabel);
            add(ageField);
            add(sexLabel);
            add(sexBox);
            add(goalLabel);
            add(goalBox);
            add(carbLabel);
            add(carbBox);
            add(mealsLabel);
            add(mealsField);
            add(new JLabel(""));
            add(submitButton);

            // Set window properties
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public static void main(String[] args) {
            ProfileFormGUI gui = new ProfileFormGUI();
        }
    }
