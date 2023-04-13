package src.Boundary;

import src.Entity.Profile;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class FavouriteFoodsPanel extends JFrame {
    private JCheckBox riceCheckBox;
    private JCheckBox pastaCheckBox;
    private JCheckBox chickenCheckBox;
    private JCheckBox beefCheckBox;
    private JCheckBox fishCheckBox;

    public FavouriteFoodsPanel(Profile userProfile) {
        // Set the layout manager for the panel
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets.bottom = 10;

        // Add a title label to the panel
        JLabel titleLabel = new JLabel("Choose your preferred carbohydrates and proteins:");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, constraints);

        // Add a rice checkbox to the panel
        riceCheckBox = new JCheckBox("Rice");
        constraints.gridy = 1;
        add(riceCheckBox, constraints);

        // Add a pasta checkbox to the panel
        pastaCheckBox = new JCheckBox("Pasta");
        constraints.gridy = 2;
        add(pastaCheckBox, constraints);

        // Add a chicken checkbox to the panel
        chickenCheckBox = new JCheckBox("Chicken");
        constraints.gridy = 3;
        add(chickenCheckBox, constraints);

        // Add a beef checkbox to the panel
        beefCheckBox = new JCheckBox("Beef");
        constraints.gridy = 4;
        add(beefCheckBox, constraints);

        // Add a fish checkbox to the panel
        fishCheckBox = new JCheckBox("Fish");
        constraints.gridy = 5;
        add(fishCheckBox, constraints);

        // Set size and make visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Getters for the checkboxes
    public boolean isRiceSelected() {
        return riceCheckBox.isSelected();
    }

    public boolean isPastaSelected() {
        return pastaCheckBox.isSelected();
    }

    public boolean isChickenSelected() {
        return chickenCheckBox.isSelected();
    }

    public boolean isBeefSelected() {
        return beefCheckBox.isSelected();
    }

    public boolean isFishSelected() {
        return fishCheckBox.isSelected();
    }

    public static void main(String[] args) {
        new FavouriteFoodsPanel(null);
    }

}
