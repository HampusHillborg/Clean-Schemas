package src;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {
    public LandingPage() {
        super("Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 0, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton mealsButton = new JButton("Meals");
        JLabel mealsLabel = new JLabel("<html><p style='width:150px;'>Heres where you see the meals for the day. You can either choose which foods you want to eat for a certain meal or you can let us generate a meal for you!</p></html>");
        mealsLabel.setVerticalAlignment(JLabel.TOP);
        buttonsPanel.add(mealsButton);
        buttonsPanel.add(mealsLabel);

        JButton weeklyPlansButton = new JButton("Weekly Plans");
        JLabel weeklyPlansLabel = new JLabel("<html><p style='width:150px;'>When you have made daily meals you can add them here in weekly plans to make your own weekly plans for the future. Otherwise we can make you a weekly plan for a small cost.</p></html>");
        weeklyPlansLabel.setVerticalAlignment(JLabel.TOP);
        buttonsPanel.add(weeklyPlansButton);
        buttonsPanel.add(weeklyPlansLabel);

        JButton chooseFoodsButton = new JButton("Choose Foods");
        JLabel chooseFoodsLabel = new JLabel("<html><p style='width:150px;'>Here you will have the options to add foods to a favourite list or dislike list so that we can generate better suited meals for you.</p></html>");
        chooseFoodsLabel.setVerticalAlignment(JLabel.TOP);
        buttonsPanel.add(chooseFoodsButton);
        buttonsPanel.add(chooseFoodsLabel);

        JButton profileButton = new JButton("Profile");
        JLabel profileLabel = new JLabel("<html><p style='width:150px;'>Set up your personal info so that we can calculate your needs and make personalized mealplans.</p></html>");
        profileLabel.setVerticalAlignment(JLabel.TOP);
        buttonsPanel.add(profileButton);
        buttonsPanel.add(profileLabel);

        add(buttonsPanel, BorderLayout.WEST);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LandingPage();
    }
}