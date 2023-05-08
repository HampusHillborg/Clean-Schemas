package src.Boundary;

import java.awt.BorderLayout;
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
    private Profile profile;
    private UserDatabase userDatabase;

    public DietPreferencesView(Profile profile, UserDatabase userDatabase) {
        this.profile = profile;
        this.userDatabase = userDatabase;

        createFrame();
        createMainPanel();
        createDietPanel();
        createSubmitButton();
        addComponentsToFrame();
    }

    private void createFrame() {
        frame = new JFrame("Diet Preferences");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
    }

    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(mainPanel);
    }

    private void createDietPanel() {
        JPanel dietPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        dietPanel.setBorder(BorderFactory.createTitledBorder("Select your diet preferences"));
        dietCheckboxes = new ArrayList<>();

        String[] dietOptions = { "Vegetarian", "Vegan", "Gluten-Free", "Dairy-Free", "Carnivore", "Keto", "Halal", "Pescatarian", "Paleo" };

        for (String dietOption : dietOptions) {
            JCheckBox dietCheckbox = new JCheckBox(dietOption);
            dietCheckboxes.add(dietCheckbox);
            dietPanel.add(dietCheckbox);
        }

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

        frame.getContentPane().add(dietPanel, BorderLayout.CENTER);
    }

    private void createSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean done = false;

                    for (JCheckBox checkbox : dietCheckboxes) {
                        if (checkbox.isSelected()) {
                            String dietCategory = checkbox.getText().toLowerCase();
                            userDatabase.addCategory(userDatabase.getUserId(profile.getEmail()), dietCategory);
                            profile.setDietCategory(dietCategory);
                            done = true;
                            break; // Exit loop after the first selected checkbox is found
                        }
                    }

                    if (!done) {
                        userDatabase.addCategory(userDatabase.getUserId(profile.getEmail()), "normal");
                        profile.setDietCategory("normal");
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
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addComponentsToFrame() {
        frame.pack();
        frame.setVisible(true);
    }
}
