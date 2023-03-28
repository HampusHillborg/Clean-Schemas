package src;

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
        panel.setLayout(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(this.usernameField);
        panel.add(passwordLabel);
        panel.add(this.passwordField);
        panel.add(createButton);
        panel.add(this.loginButton);

        this.add(panel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
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
        new ProfileFormGUI();
    }

    public static void main(String[] args) {
        new LoginViewerGUI();
    }
}

