package src.Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.Database.UserDatabase;
import src.Entity.Profile;

public class DietPreferencesView {
    private JFrame frame;
    private ArrayList<JCheckBox> dietCheckboxes;
    private JPanel panel;

    private Profile profile;
    private UserDatabase userDatabase;

    public DietPreferencesView(Profile profile, UserDatabase userDatabase) {
        this.profile = profile;
        this.userDatabase = userDatabase;

        frame = new JFrame("Diet Preferences");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel dietPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        dietPanel.setBorder(BorderFactory.createTitledBorder("Select your diet preferences"));

        dietCheckboxes = new ArrayList<>();
        JCheckBox vegetarianCheckbox = new JCheckBox("Vegetarian");
        dietCheckboxes.add(vegetarianCheckbox);
        dietPanel.add(vegetarianCheckbox);

        JCheckBox veganCheckbox = new JCheckBox("Vegan");
        dietCheckboxes.add(veganCheckbox);
        dietPanel.add(veganCheckbox);

        JCheckBox glutenFreeCheckbox = new JCheckBox("Gluten-Free");
        dietCheckboxes.add(glutenFreeCheckbox);
        dietPanel.add(glutenFreeCheckbox);

        JCheckBox dairyFreeCheckbox = new JCheckBox("Dairy-Free");
        dietCheckboxes.add(dairyFreeCheckbox);
        dietPanel.add(dairyFreeCheckbox);

        JCheckBox carinvorCheckbox = new JCheckBox("Carnivore");
        dietCheckboxes.add(carinvorCheckbox);
        dietPanel.add(carinvorCheckbox);

        JCheckBox ketoCheckbox = new JCheckBox("Keto");
        dietCheckboxes.add(ketoCheckbox);
        dietPanel.add(ketoCheckbox);

        JCheckBox halalCheckbox = new JCheckBox("Halal");
        dietCheckboxes.add(halalCheckbox);
        dietPanel.add(halalCheckbox);

        JCheckBox pescatarianCheckbox = new JCheckBox("Pescatarian");
        dietCheckboxes.add(pescatarianCheckbox);
        dietPanel.add(pescatarianCheckbox);

        JCheckBox paleoCheckbox = new JCheckBox("Paleo");
        dietCheckboxes.add(paleoCheckbox);
        dietPanel.add(paleoCheckbox);

        // Add a listener to each checkbox to uncheck the others when it is selected
        for (JCheckBox checkbox : dietCheckboxes) {
            checkbox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JCheckBox otherCheckbox : dietCheckboxes) {
                        if (otherCheckbox != checkbox) {
                            otherCheckbox.setSelected(false);
                        }
                    }
                }
            });
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean done = false;
                try {
                    for (JCheckBox checkbox : dietCheckboxes) {

                        if (checkbox.isSelected()) {
                            userDatabase.addCategory(userDatabase.getUserId(profile.getEmail()), checkbox.getText());
                            done = true;
                        }

                    }
                    if (!done) {
                        userDatabase.addCategory(userDatabase.getUserId(profile.getEmail()), null);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(dietPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}





