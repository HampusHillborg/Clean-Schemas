package src.Boundary;

import src.Controller.LoginController;
import src.Database.UserDatabase;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


/**
 * The LoginViewerGUI class represents the graphical user interface for the
 * login screen of the application.
 * It allows users to log in with an existing account or create a new account.
 * This class extends the JFrame class and implements the ActionListener and
 * KeyListener interfaces.
 */
public class LoginViewerGUI extends JFrame implements ActionListener, KeyListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createButton;
    private UserDatabase userDatabase;
    private LoginController loginController;

    /**
     * Constructs a LoginViewerGUI object with the given UserDatabase.
     *
     * @param userDatabase the UserDatabase object to use for managing user data.
     */
    public LoginViewerGUI(UserDatabase userDatabase) {
        super("Login Viewer");
        this.userDatabase = userDatabase;
        loginController = new LoginController(userDatabase);

        createUI();
    }

    /**
     * Creates the user interface for the LoginViewerGUI and sets its layout, components, and settings.
     * The user interface includes a form panel containing email and password fields, and a panel
     * containing the create and login buttons.
     */
    private void createUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Email:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.addKeyListener(this);

        createButton = createButton("Create Account");
        loginButton = createButton("Log In");
        createButton.setOpaque(true);
        loginButton.setOpaque(true);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(createButtonPanel());

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates a JButton with the specified text and sets its alignment, background color,
     * foreground color, action listener, and opacity.
     *
     * @param text the text to display on the button
     * @return a JButton with the specified text and settings
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(32, 98, 147));
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        button.setOpaque(true);
        return button;
    }

    private JPanel createFormField(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textField, gbc);

        return panel;
    }

    /**
     * Creates a JPanel containing the create and login buttons.
     *
     * @return a JPanel containing the create and login buttons
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createButton);
        panel.add(loginButton);

        return panel;
    }

    /**
     * Called when an action is performed on the create or login button.
     * If the source of the action event is the create button, the createAccount() method is called.
     * If the source of the action event is the login button, the login() method is called.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            createAccount();
        } else if (e.getSource() == loginButton) {
            login();
        }
    }

    /**
     * This method creates a new account for a user.
     * It retrieves the username and password entered by the user and checks if the
     * username already exists in the database.
     * If the username already exists, a message dialog is displayed asking the user
     * to choose another username.
     * Otherwise, the new user is added to the database, and a message dialog is
     * displayed indicating that the account was created successfully.
     * Then, the current frame is disposed, and a new instance of the ProfileFormGUI
     * class is created for the new user.
     */
    public void createAccount() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.trim().isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email and password.");
            return;
        }

        if (!username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return;
        }

        if (loginController.checkIfRegistered(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
            return;
        }

        Profile userProfile = new Profile(username, password);
        JOptionPane.showMessageDialog(this, "Account created successfully!");

        dispose();
        new ProfileFormGUI(userProfile, userDatabase);
    }

    /**
     * Validates the login credentials entered by the user and logs in the user if
     * the credentials are correct.
     * Displays an error message if the credentials are incorrect or if there is a
     * problem loading the user's data from the database.
     * Once logged in successfully, the LandingPage is opened for the logged-in
     * user.
     */
    public void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.trim().isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email and password.");
            return;
        }

        if (!loginController.validateLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Login failed, please try again!");
            return;
        }

        Profile loggedInUser = loginController.getLoggedInUser(username);
        JOptionPane.showMessageDialog(this, "Logged in successfully!");

        loginController.loadXML();

        dispose();
        new LandingPage(loggedInUser, userDatabase);
    }

    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /**
     * Adds a key event on enter so that when enter is pressed
     * it will use the login method
     * 
     * @param e the event to be processed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            login();
        }
    }

    /**
     * Invoked when a key has been released.
     *
     * @param e the event to be processed
     */
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    /**
     * The main method for the LoginViewerGUI class.
     * This method creates a UserDatabase object and a new instance of the LoginViewerGUI class,
     * which is executed on the event dispatching thread using SwingUtilities.invokeLater().
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDatabase userDatabase = new UserDatabase();
            new LoginViewerGUI(userDatabase);
        });
    }
}
