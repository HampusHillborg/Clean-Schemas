package src.Boundary;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI components
        JLabel foodNameLabel = new JLabel("Food Name:");
        foodNameField = new JTextField(20);
        searchButton = new JButton("Search");

        // Add components to frame
        add(foodNameLabel);
        add(foodNameField);
        add(searchButton);

        // Set layout and size
        setLayout(new FlowLayout());
        setSize(400, 100);

        // Set ActionListener for searchButton
        searchButton.addActionListener(this);
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

}