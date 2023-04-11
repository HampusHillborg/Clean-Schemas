package src.Boundary;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private LandingPage landingPage;
    private LoginViewerGUI loginViewerGUI;

    public MainFrame() {
        super("Meal Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the LoginViewerGUI and show it first
        this.loginViewerGUI = new LoginViewerGUI();
        getContentPane().add(this.loginViewerGUI, BorderLayout.CENTER);

        // Set the size of the JFrame and make it visible
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}














