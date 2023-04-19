package src.Boundary;

import src.Controller.LoginController;
import src.Database.UserDatabase;
import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {
    private ProfileFormGUI profileFormGUI;
    private UserDatabase userDatabase;
    private LoginController loginController;
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
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        logoutButton.setPreferredSize(new Dimension(70, 30));
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.NORTH);

        JButton mealsButton = new JButton("Meals");
        JLabel mealsLabel = new JLabel("<html><p style='width:150px;'>Heres where you see the meals for the day. You can either choose which foods you want to eat for a certain meal or you can let us generate a meal for you!</p></html>");
        mealsLabel.setVerticalAlignment(JLabel.TOP);
        mealsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        mealsButton.setBackground(Color.WHITE);
        mealsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mealsButton.setPreferredSize(new Dimension(150, 50));
        buttonsPanel.add(mealsButton);
        buttonsPanel.add(mealsLabel);

        JButton weeklyPlansButton = new JButton("Weekly Plans");
        JLabel weeklyPlansLabel = new JLabel("<html><p style='width:150px;'>When you have made daily meals you can add them here in weekly plans to make your own weekly plans for the future. Otherwise we can make you a weekly plan for a small cost.</p></html>");
        weeklyPlansLabel.setVerticalAlignment(JLabel.TOP);
        weeklyPlansLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        weeklyPlansButton.setBackground(Color.WHITE);
        weeklyPlansButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        weeklyPlansButton.setPreferredSize(new Dimension(150, 50));
        buttonsPanel.add(weeklyPlansButton);
        buttonsPanel.add(weeklyPlansLabel);

        JButton chooseFoodsButton = new JButton("Choose Foods");
        JLabel chooseFoodsLabel = new JLabel("<html><p style='width:150px;'>Here you will have the options to add foods to a favourite list or dislike list so that we can generate better suited meals for you.</p></html>");
        chooseFoodsLabel.setVerticalAlignment(JLabel.TOP);
        chooseFoodsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        chooseFoodsButton.setBackground(Color.WHITE);
        chooseFoodsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        chooseFoodsButton.setPreferredSize(new Dimension(150, 50));
        buttonsPanel.add(chooseFoodsButton);
        buttonsPanel.add(chooseFoodsLabel);

        JButton profileButton = new JButton("Edit Profile");
        JLabel profileLabel = new JLabel("<html><p style='width:150px;'>Set up your personal info so that we can calculate your needs and make personalized mealplans.</p></html>");
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        profileButton.setBackground(Color.WHITE);
        profileButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        profileButton.setPreferredSize(new Dimension(150, 50));
        buttonsPanel.add(profileButton);
        buttonsPanel.add(profileLabel);

        JButton savedInfoButton = new JButton("Profilestatus");
        JLabel savedInfoLabel = new JLabel("<html><p style='width:150px;'>Show your personal info.</p></html>");
        savedInfoLabel.setVerticalAlignment(JLabel.TOP);
        savedInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        savedInfoButton.setBackground(Color.WHITE);
        savedInfoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        savedInfoButton.setPreferredSize(new Dimension(150, 50));
        buttonsPanel.add(savedInfoButton);
        buttonsPanel.add(savedInfoLabel);





        add(buttonsPanel, BorderLayout.WEST);

        savedInfoButton.addActionListener(e -> {
            new ProfileDisplayGUI(profile);
        });

        mealsButton.addActionListener(e -> {
            // Code to execute when mealsButton is clicked
            new MealsPerDayGUI(profile);
            System.out.println("Meals button clicked!");
        });

        weeklyPlansButton.addActionListener(e -> {
            // Code to execute when mealsButton is clicked
            System.out.println("Weekly button clicked!");
        });

        chooseFoodsButton.addActionListener(e -> {
            // Code to execute when mealsButton is clicked
            new FavouriteFoodsPanel(profile);
            System.out.println("Foods button clicked!");
        });

        profileButton.addActionListener(e -> {
            new DisplayProfile(profile, userDatabase);
            // Code to execute when mealsButton is clicked
            System.out.println("Profile button clicked!");

        });

        logoutButton.addActionListener(e -> {
            new LoginViewerGUI(userDatabase);
            dispose();
        });




        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void main(String[] args) {
        //new LandingPage();
    }
}