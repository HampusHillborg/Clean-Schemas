package src.Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginViewerGUI extends JFrame implements ActionListener {
    private HashMap<String, String> users;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ProfileFormGUI registerInput;
    private LandingPage landingPage;

    public LoginViewerGUI() {
        super("Login Viewer");

        this.users = new HashMap<>();

        JLabel usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField(20);

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

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username and password.");
            return;
        }

        if (this.users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
            return;
        }

        this.users.put(username, password);
        JOptionPane.showMessageDialog(this, "Account created successfully!");
        new ProfileFormGUI();
    }

    public void login() {
        String username = this.usernameField.getText();
        String password = new String(this.passwordField.getPassword());

        if (!this.users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username does not exist.");
            return;
        }

        if (!this.users.get(username).equals(password)) {
            JOptionPane.showMessageDialog(this, "Incorrect password.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Logged in successfully!");
        new LandingPage();

    }

    public static void main(String[] args) {
        new LoginViewerGUI();
    }
}


