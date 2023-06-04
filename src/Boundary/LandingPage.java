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
     * Constructs a LandingPage object with the specified profile and user database.
     *
     * @param profile The user profile.
     * @param userDatabase The user database.
     */
    public LandingPage(Profile profile, UserDatabase userDatabase) {
        super("Main Menu");
        this.userDatabase = userDatabase;
        loginController = new LoginController(userDatabase);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE.brighter());
        setupUI();
        setupEventHandlers(profile);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets up the user interface by calling the necessary setup methods.
     * Configures the buttons panel and the logout button.
     */
    private void setupUI() {
        setupButtonsPanel();
        setupLogoutButton();
    }

    /**
     * Sets up the buttons panel in the user interface.
     * Creates a panel with a grid layout and adds buttons with corresponding labels to it.
     * The buttons represent different functionalities in the application.
     */
    private void setupButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 2, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(buttonsPanel, BorderLayout.CENTER);

        addButtonWithLabel(buttonsPanel, "Meals",
                "<html><p style='width:150px;'>Heres where you can generate meals based on your daily needs!</p></html>");
        addButtonWithLabel(buttonsPanel, "Search for foods",
                "<html><p style='width:150px;'>Here you can search for a food to see how much protein, carbs, fat and calories it contains per 100 grams</p></html>");
        addButtonWithLabel(buttonsPanel, "Choose Diet",
                "<html><p style='width:150px;'>Here you will have the option to add a diet preference so that we can generate better suited meals for you.</p></html>");
        addButtonWithLabel(buttonsPanel, "Edit Profile",
                "<html><p style='width:150px;'>Set up your personal info so that we can calculate your needs and make personalized mealplans.</p></html>");
        addButtonWithLabel(buttonsPanel, "Profilestatus",
                "<html><p style='width:150px;'>Show your personal info.</p></html>");

        buttonsPanel.setBackground(Color.WHITE.brighter());
    }

    /**
     * Adds a button with a corresponding label to the specified panel.
     *
     * @param panel The panel to which the button and label are added.
     * @param buttonText The text displayed on the button.
     * @param labelText  The text displayed on the label.
     */
    private void addButtonWithLabel(JPanel panel, String buttonText, String labelText) {
        JButton button = createStyledButton(buttonText);
        JLabel label = createStyledLabel(labelText);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panel.add(button);
        panel.add(label);
    }

    /**
     * Sets up the logout button in the user interface.
     * The button is added to the top right corner of the layout.
     * When clicked, it triggers the logout functionality.
     */
    private void setupLogoutButton() {
        JButton logoutButton = createStyledButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setBackground(Color.WHITE.brighter());
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.NORTH);
    }

    /**
     * Sets up the event handlers for the buttons in the user interface.
     *
     * @param profile The user profile used for handling the events.
     */
    private void setupEventHandlers(Profile profile) {
        Component[] components = getContentPane().getComponents();
        if (components.length >= 1 && components[0] instanceof JPanel) {
            JPanel buttonsPanel = (JPanel) components[0];
            for (Component component : buttonsPanel.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (button.getText().equals("Profilestatus")) {
                        button.addActionListener(e -> new ProfileDisplayGUI(profile));
                    } else if (button.getText().equals("Meals")) {
                        button.addActionListener(e -> new MealsButton(profile, userDatabase));
                    } else if (button.getText().equals("Search for foods")) {
                        button.addActionListener(e -> new SearchForFoods());
                    } else if (button.getText().equals("Choose Foods")) {
                        button.addActionListener(e -> new DietPreferencesView(profile, userDatabase));
                    } else if (button.getText().equals("Edit Profile")) {
                        button.addActionListener(e -> new DisplayProfile(profile, userDatabase));
                    }
                }
            }
        }

        Component[] logoutComponents = getContentPane().getComponents();
        if (logoutComponents.length >= 2 && logoutComponents[1] instanceof JPanel) {
            JPanel logoutPanel = (JPanel) logoutComponents[1];
            for (Component component : logoutPanel.getComponents()) {
                if (component instanceof JButton && ((JButton) component).getText().equals("Logout")) {
                    JButton logoutButton = (JButton) component;
                    logoutButton.addActionListener(e -> {
                        new LoginViewerGUI(userDatabase);
                        dispose();
                    });
                }
            }
        }
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