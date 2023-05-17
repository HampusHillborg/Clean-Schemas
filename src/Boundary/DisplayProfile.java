package src.Boundary;

import src.Controller.LoginController;
import src.Controller.RegistrationController;
import src.Database.UserDatabase;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is responsible for displaying a form for a user to enter their profile information.
 * It takes a Profile object and a UserDatabase object as parameters to initialize the form.
 * It sets up the GUI components, layout, and submit button listener for the form.
 * When the submit button is pressed, it updates the user's profile information in the database
 * and displays the updated profile.
 */

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

    /**
     * Constructor for the DisplayProfile class.
     *
     * @param profile      the user's profile information
     * @param userDatabase the user database object
     */
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

    /**
     * Initializes and sets the values for the components in the Profile Information window.
     * Components include JLabels for Height, Weight, Age, Sex, Goal, Activity Level, Carbohydrate
     * Intake, and Number of Meals, as well as corresponding JTextFields and JComboBoxes.
     * The default values for the JTextFields and JComboBoxes are set based on the values stored
     * in the profile object.
     */
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
        sexField = new JComboBox<>(new String[]{"Male", "Female"});
        goalField = new JComboBox<>(new String[]{"Weight Loss", "Maintenance", "Weight Gain"});
        activityField = new JComboBox<>(new String[]{"Sedentary", "Light Exercise(1-2/Week)",
                "Moderate Exercise(3-5/Week)", "Heavy Exercise(6-7/Week)", "Athlete(2x/Day)"});
        carbField = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        mealsField = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

        heightField.setText(Double.toString(profile.getHeight()));
        weightField.setText(Double.toString(profile.getWeight()));
        ageField.setText(Integer.toString(profile.getAge()));
        sexField.setSelectedItem(profile.getSex());
        goalField.setSelectedItem(profile.getGoal());
        activityField.setSelectedItem(profile.getActivityValue());
        carbField.setSelectedItem(profile.getCarbAmount());
        mealsField.setSelectedItem(Integer.toString(profile.getMealsPerDay()));
    }

    /**
     * Creates the layout for the profile information input panel.
     * Adds components to a JPanel with a 9x2 GridLayout and sets their labels and fields.
     * Sets the layout of the main panel to a BorderLayout and adds the GridLayout panel to the center.
     * Adds a title label to the top of the main panel.
     */
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

    /**
     * Sets up the listener for the submit button in the graphical user interface.
     * Configures the appearance and behavior of the submit button and adds it to the GUI.
     * Also adds a KeyListener to each relevant text field to enable submission by pressing the Enter key.
     */
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
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(e -> handleSubmission());

        // Add a KeyListener to each text field
        heightField.addKeyListener(createKeyListener(submitButton));
        weightField.addKeyListener(createKeyListener(submitButton));
        ageField.addKeyListener(createKeyListener(submitButton));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles the submission of user input values.
     * Parses the input fields, updates the profile with the values,
     * and performs necessary operations to update the profile in the system.
     * If any errors occur during parsing or updating the profile, an appropriate error message is displayed.
     */
    private void handleSubmission() {
        try {
            double height = parseDoubleField(heightField);
            double weight = parseDoubleField(weightField);
            int age = parseIntegerField(ageField);
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
            showErrorDialog("Invalid input! Please enter valid numerical values.");
        } catch (Exception ex) {
            showErrorDialog("An error occurred while updating the profile.");
        }
    }

    /**
     * Parses the text from the given text field into a double value.
     * Throws a NumberFormatException if the field is empty or contains non-numeric characters.
     *
     * @param field the JTextField to parse the value from
     * @return the parsed double value from the field
     * @throws NumberFormatException if the field is empty or contains non-numeric characters
     */
    private double parseDoubleField(JTextField field) throws NumberFormatException {
        String text = field.getText();
        if (text.isEmpty()) {
            throw new NumberFormatException("Input field is empty.");
        }
        return Double.parseDouble(text);
    }

    /**
     * Parses the text from the given text field into an integer value.
     * Throws a NumberFormatException if the field is empty or contains non-numeric characters.
     *
     * @param field the JTextField to parse the value from
     * @return the parsed integer value from the field
     * @throws NumberFormatException if the field is empty or contains non-numeric characters
     */
    private int parseIntegerField(JTextField field) throws NumberFormatException {
        String text = field.getText();
        if (text.isEmpty()) {
            throw new NumberFormatException("Input field is empty.");
        }
        return Integer.parseInt(text);
    }

    /**
     * Creates a KeyListener that triggers the submit button click when the Enter key is pressed.
     *
     * @param submitButton the JButton to be triggered when the Enter key is pressed
     * @return a KeyListener that listens for the Enter key and triggers the submit button click
     */
    private KeyListener createKeyListener(JButton submitButton) {
        return new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submitButton.doClick(); // trigger button press
                }
            }
        };
    }

    /**
     * Displays an error dialog with the given message.
     *
     * @param message the error message to be shown in the dialog
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(DisplayProfile.this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }




    public static void main(String[] args) {
    }
}
