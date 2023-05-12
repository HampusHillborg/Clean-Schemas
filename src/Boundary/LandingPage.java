package src.Boundary;

import src.Controller.LoginController;
import src.Database.UserDatabase;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LandingPage class represents the main menu of the application. It extends
 * JFrame and displays
 * several options for the user to choose from. It contains buttons for meals,
 * weekly plans, choosing
 * favorite/disliked foods, editing personal information, and displaying profile
 * status. It also contains
 * a logout button that allows the user to log out of the application.
 */
public class LandingPage extends JFrame {
    private ProfileFormGUI profileFormGUI;
    private UserDatabase userDatabase;
    private LoginController loginController;

    /**
     * Constructs a LandingPage object with a given Profile and UserDatabase.
     *
     * @param profile      the user's profile information
     * @param userDatabase the database containing user information
     */
    public LandingPage(Profile profile, UserDatabase userDatabase) {
        super("Main Menu");
        this.userDatabase = userDatabase;
        loginController = new LoginController(userDatabase);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 2, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(buttonsPanel, BorderLayout.CENTER);

        // Create a logout button and add it to the top right corner
        JButton logoutButton = createStyledButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40)); // Updated size
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.NORTH);

        JButton mealsButton = createStyledButton("Meals");
        JLabel mealsLabel = createStyledLabel(
                "<html><p style='width:150px;'>Heres where you see the meals for the day. You can either choose which foods you want to eat for a certain meal or you can let us generate a meal for you!</p></html>");
        mealsLabel.setVerticalAlignment(JLabel.TOP);
        mealsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonsPanel.add(mealsButton);
        buttonsPanel.add(mealsLabel);

        JButton searchForFoods = createStyledButton("Search for foods");
        JLabel weeklyPlansLabel = createStyledLabel(
                "<html><p style='width:150px;'>Here you can search for a food to see how much protein, carbs, fat and calories it contains per 100 grams</p></html>");
        weeklyPlansLabel.setVerticalAlignment(JLabel.TOP);
        weeklyPlansLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonsPanel.add(searchForFoods);
        buttonsPanel.add(weeklyPlansLabel);

        JButton chooseFoodsButton = createStyledButton("Choose Foods");
        JLabel chooseFoodsLabel = createStyledLabel(
                "<html><p style='width:150px;'>Here you will have the options to add foods to a favourite list or dislike list so that we can generate better suited meals for you.</p></html>");
        chooseFoodsLabel.setVerticalAlignment(JLabel.TOP);
        chooseFoodsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonsPanel.add(chooseFoodsButton);
        buttonsPanel.add(chooseFoodsLabel);

        JButton profileButton = createStyledButton("Edit Profile");
        JLabel profileLabel = createStyledLabel(
                "<html><p style='width:150px;'>Set up your personal info so that we can calculate your needs and make personalized mealplans.</p></html>");
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonsPanel.add(profileButton);
        buttonsPanel.add(profileLabel);

        JButton savedInfoButton = createStyledButton("Profilestatus");
        JLabel savedInfoLabel = createStyledLabel("<html><p style='width:150px;'>Show your personal info.</p></html>");
        savedInfoLabel.setVerticalAlignment(JLabel.TOP);
        savedInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        buttonsPanel.add(savedInfoButton);
        buttonsPanel.add(savedInfoLabel);

        add(buttonsPanel, BorderLayout.WEST);

        savedInfoButton.addActionListener(e -> {
            new ProfileDisplayGUI(profile);
        });

        mealsButton.addActionListener(e -> {
            // Code to execute when mealsButton is clicked
            new MealsButton(profile, userDatabase);

        });

        searchForFoods.addActionListener(e -> {
            new SearchForFoods();
        });

        chooseFoodsButton.addActionListener(e -> {
            // Code to execute when mealsButton is clicked
            new DietPreferencesView(profile, userDatabase);

        });

        profileButton.addActionListener(e -> {
            new DisplayProfile(profile, userDatabase);
            // Code to execute when mealsButton is clicked


        });

        logoutButton.addActionListener(e -> {
            new LoginViewerGUI(userDatabase);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     Creates a styled JButton with the specified text.
     @param text the text to be displayed on the button

     @return the created JButton
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(32, 98, 147));
        button.setForeground(Color.WHITE);
       // button.addActionListener(this);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // remove border when button is focused
        button.setFocusPainted(false);
        // remove border
        button.setBorderPainted(false);

        return button;
    }


    /**
     Creates a styled JLabel with the specified text.
     @param text the text to be displayed on the label

     @return the created JLabel
     */
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        return label;
    }

    public static void main(String[] args) {
        // new LandingPage();
    }
}