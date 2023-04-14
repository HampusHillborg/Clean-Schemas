package src.Boundary;

import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MealsPerDayGUI extends JFrame implements ActionListener {

    private JButton generateButton, saveButton;
    private JComboBox<String> breakfastComboBox, lunchComboBox, dinnerComboBox;
    private JCheckBox breakfastCheckBox, lunchCheckBox, dinnerCheckBox;
    private JLabel caloriesLabel, proteinLabel, fatLabel, carbohydratesLabel;

    //private String[] breakfastFoods = {"Eggs", "Oatmeal", "Yogurt", "Toast", "Fruit"};
    //private String[] lunchFoods = {"Sandwich", "Salad", "Soup", "Leftovers", "Wrap"};
    //private String[] dinnerFoods = {"Chicken", "Beef", "Fish", "Vegetables", "Pasta"};
    private String[] breakfastFoods = {"Eggs (protein: 6g, fat: 5g, carbs: 0.6g, calories: 78)", "Oatmeal (protein: 6g, fat: 2g, carbs: 27g, calories: 150)", "Yogurt (protein: 12g, fat: 0.5g, carbs: 17g, calories: 110)", "Toast (protein: 3g, fat: 1g, carbs: 12g, calories: 70)", "Fruit (protein: 1g, fat: 0g, carbs: 15g, calories: 60)"};
    private String[] lunchFoods = {"Sandwich (protein: 20g, fat: 10g, carbs: 30g, calories: 350)", "Salad (protein: 10g, fat: 5g, carbs: 20g, calories: 150)", "Soup (protein: 8g, fat: 5g, carbs: 10g, calories: 120)", "Leftovers (protein: varies, fat: varies, carbs: varies, calories: varies)", "Wrap (protein: 25g, fat: 10g, carbs: 30g, calories: 400)"};
    private String[] dinnerFoods = {"Chicken (protein: 30g, fat: 10g, carbs: 0g, calories: 250)", "Beef (protein: 25g, fat: 20g, carbs: 0g, calories: 300)", "Fish (protein: 20g, fat: 10g, carbs: 0g, calories: 200)", "Vegetables (protein: 5g, fat: 0.5g, carbs: 15g, calories: 75)", "Pasta (protein: 8g, fat: 3g, carbs: 30g, calories: 200)"};

    public MealsPerDayGUI(Profile userProfile) {
        setTitle("Meals Per Day");


        JLabel titleLabel = new JLabel("Meals for the Day");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        breakfastCheckBox = new JCheckBox("Breakfast");
        breakfastCheckBox.addActionListener(this);
        mainPanel.add(breakfastCheckBox);

        breakfastComboBox = new JComboBox<>(breakfastFoods);
        mainPanel.add(breakfastComboBox);
        breakfastComboBox.setEnabled(false);



        lunchCheckBox = new JCheckBox("Lunch");
        lunchCheckBox.addActionListener(this);
        mainPanel.add(lunchCheckBox);

        lunchComboBox = new JComboBox<>(lunchFoods);
        mainPanel.add(lunchComboBox);
        lunchComboBox.setEnabled(false);


        dinnerCheckBox = new JCheckBox("Dinner");
        dinnerCheckBox.addActionListener(this);
        mainPanel.add(dinnerCheckBox);

        dinnerComboBox = new JComboBox<>(dinnerFoods);
        mainPanel.add(dinnerComboBox);
        dinnerComboBox.setEnabled(false);


        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate Meals");
        generateButton.addActionListener(this);
        buttonPanel.add(generateButton);

        saveButton = new JButton("Save Meals");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == generateButton) {
            generateMeals();
        } else if (source == saveButton) {
            saveMeals();
        } else if (source == breakfastCheckBox) {
            breakfastComboBox.setEnabled(breakfastCheckBox.isSelected());
        } else if (source == lunchCheckBox) {
            lunchComboBox.setEnabled(lunchCheckBox.isSelected());
        } else if (source == dinnerCheckBox) {
            dinnerComboBox.setEnabled(dinnerCheckBox.isSelected());
        }
    }

    private void generateMeals() {
        if (breakfastCheckBox.isSelected() && breakfastComboBox.getSelectedIndex() == -1) {
            breakfastComboBox.setSelectedIndex((int) (Math.random() * breakfastFoods.length));
        }

        if (lunchCheckBox.isSelected() && lunchComboBox.getSelectedIndex() == -1) {
            lunchComboBox.setSelectedIndex((int) (Math.random() * lunchFoods.length));
        }

        if (dinnerCheckBox.isSelected() && dinnerComboBox.getSelectedIndex() == -1) {
            dinnerComboBox.setSelectedIndex((int) (Math.random() * dinnerFoods.length));
        }
    }

    private void saveMeals() {
        StringBuilder sb = new StringBuilder();
        sb.append("Meals for the day:\n");

        if (breakfastCheckBox.isSelected()) {
            sb.append("- Breakfast: ").append(breakfastComboBox.getSelectedItem()).append("\n");
        }

        if (lunchCheckBox.isSelected()) {
            sb.append("- Lunch: ").append(lunchComboBox.getSelectedItem()).append("\n");
        }

        if (dinnerCheckBox.isSelected()) {
            sb.append("- Dinner: ").append(dinnerComboBox.getSelectedItem()).append("\n");
        }

        if (sb.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one meal.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, sb.toString(), "Selected Meals", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args){
        new MealsPerDayGUI(null);
    }
}

