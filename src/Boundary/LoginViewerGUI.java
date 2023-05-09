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
    

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(32, 98, 147));
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
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
    

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createButton);
        panel.add(loginButton);
        return panel;
    }

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

    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDatabase userDatabase = new UserDatabase();
            new LoginViewerGUI(userDatabase);
        });
    }
}
