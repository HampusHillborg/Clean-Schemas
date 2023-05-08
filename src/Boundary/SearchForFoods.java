package src.Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.API.NutritionAPI;
import src.Entity.Food;

public class SearchForFoods extends JFrame implements ActionListener {

    private JTextField foodNameField;
    private JButton searchButton;

    public SearchForFoods() {
        super("Search for Foods");

        // Create UI components
        JLabel foodNameLabel = new JLabel("Food Name:");
        foodNameField = new JTextField(20);
        searchButton = new JButton("Search");

        // Set button properties
        searchButton.setBackground(new Color(32, 98, 147));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(new Dimension(100, 30));

        // Create panel and set layout
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        // Add components to panel
        panel.add(foodNameLabel);
        panel.add(foodNameField);
        panel.add(searchButton);

        // Set panel as the content pane
        setContentPane(panel);

        // Set ActionListener for searchButton
        searchButton.addActionListener(this);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            // Get food name from text field
            String foodName = foodNameField.getText();

            // Call searchForFood method in NutritionAPI class
            NutritionAPI nutritionAPI = new NutritionAPI();
            Food food = nutritionAPI.createFood(foodName);

            // Display search result in JOptionPane
            JOptionPane.showMessageDialog(this, food.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SearchForFoods();
            }
        });
    }
}
