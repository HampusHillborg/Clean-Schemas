package src.Boundary;

import src.Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MealsPerDayGUI extends JFrame implements ActionListener {

    private JButton generateButton, saveButton;
    private JComboBox<String> breakfastComboBox, lunchComboBox, dinnerComboBox;
    private JCheckBox breakfastCheckBox, lunchCheckBox, dinnerCheckBox;

    private String[] breakfastFoods = {"Eggs", "Oatmeal", "Yogurt", "Toast", "Fruit"};
    private String[] lunchFoods = {"Sandwich", "Salad", "Soup", "Leftovers", "Wrap"};
    private String[] dinnerFoods = {"Chicken", "Beef", "Fish", "Vegetables", "Pasta"};

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

