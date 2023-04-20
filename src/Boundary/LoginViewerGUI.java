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

public class LoginViewerGUI extends JFrame implements ActionListener, KeyListener {
    private HashMap<String, String> users;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ProfileFormGUI registerInput;
    private LandingPage landingPage;
    private Profile userProfile;
    private UserDatabase userDatabase;
    private LoginController loginController;

    public LoginViewerGUI(UserDatabase userDatabase) {
        super("Login Viewer");
        this.userDatabase = userDatabase;
        loginController = new LoginController(userDatabase);

        this.users = new HashMap<>();

        JLabel usernameLabel = new JLabel("Email:");
        this.usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField(20);
        this.passwordField.addKeyListener(this);

        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(this);

        this.loginButton = new JButton("Log In");
        this.loginButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(this.usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(this.passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(createButton, gbc);
        gbc.gridy = 3;
        panel.add(this.loginButton, gbc);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create Account")) {
            createAccount();
        } else if (e.getActionCommand().equals("Log In")) {
            login();
        }
    }

    public void createAccount() {
        String username = this.usernameField.getText();
        String password = new String(this.passwordField.getPassword());

        if (loginController.checkIfRegistered(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
            return;
        }

        this.users.put(username, password);
        userProfile = new Profile(username, password);
        JOptionPane.showMessageDialog(this, "Account created successfully!");
        dispose();
        new ProfileFormGUI(userProfile, userDatabase);
    }

    public void login() {
        String username = this.usernameField.getText();
        String password = new String(this.passwordField.getPassword());

        if (!loginController.validateLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Login failed, try again!");
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
        // new LoginViewerGUI();
    }
}
