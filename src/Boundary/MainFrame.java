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


    public void showLandingPage() {
        // Create the LandingPage and remove the LoginViewerGUI
        this.landingPage = new LandingPage();
        getContentPane().remove(this.loginViewerGUI);

        // Add the LandingPage to the content pane of the JFrame
        getContentPane().add(this.landingPage, BorderLayout.CENTER);

        // Refresh the JFrame
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Create a new MainFrame object
        MainFrame mainFrame = new MainFrame();

        // Set the LoginViewerGUI's listener to the MainFrame's showLandingPage method
        /*mainFrame.loginViewerGUI.setLoginListener(mainFrame::showLandingPage);

         */
    }
}















