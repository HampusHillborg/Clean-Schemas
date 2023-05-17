package src.Boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import src.Database.UserDatabase;
import src.Entity.Profile;

/**
 * The DietPreferencesView class represents the graphical user interface for the Diet Preferences window.
 * It allows the user to select their diet preferences and save them to a user's profile.
 */
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

    /**
     * Creates and configures the main frame for the Diet Preferences window.
     */
    private void createFrame() {
        frame = new JFrame("Diet Preferences");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // Disable frame resizing

        // Set a custom look and feel
        /*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

         */

        // Use a custom font for the frame
        Font customFont = new Font("Arial", Font.PLAIN, 12);
        UIManager.put("Label.font", customFont);
        UIManager.put("Button.font", customFont);
        UIManager.put("CheckBox.font", customFont);

        // Adjust the color scheme
        Color panelBackgroundColor = new Color(240, 240, 240);
        Color titleBorderColor = new Color(170, 170, 170);
        Color buttonColor = new Color(32, 98, 147);

        // Set the panel background color
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(panelBackgroundColor);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(contentPane);

        // Set the title border
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(titleBorderColor, 2), "Select your diet preferences");
        titledBorder.setTitleFont(customFont);
        ((JComponent) frame.getContentPane()).setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), titledBorder));
    }

    /**
     * Creates the main panel and sets it as the content pane of the main frame.
     */
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(mainPanel);
    }

    /**
     * Creates the diet panel with checkboxes for selecting diet preferences and adds it to the main frame.
     */
    private void createDietPanel() {
        JPanel dietPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        dietPanel.setBorder(BorderFactory.createTitledBorder("Select your diet preferences"));
        dietCheckboxes = new ArrayList<>();

        String[] dietOptions = { "Vegetarian", "Vegan", "Gluten-Free", "Dairy-Free", "Carnivore", "Keto", "Halal",
                "Pescatarian", "Paleo" };

        for (String dietOption : dietOptions) {
            JCheckBox dietCheckbox = new JCheckBox(dietOption);
            dietCheckbox.setFocusPainted(false);
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

    /**
     * Create a submit button with an actionlistener that
     * saves the diet category to a user
     */
    private void createSubmitButton() {
        Color buttonColor = new Color(32, 98, 147); // Custom button color
        Color panelBackgroundColor = Color.WHITE; // Custom panel background color

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        submitButton.setPreferredSize(new Dimension(150, 30));
        submitButton.setOpaque(true); // Set button's opaque property to truehamham

        // Customize the button font and size
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        submitButton.setFont(buttonFont);

        // Set custom ButtonUI for consistent background color
        submitButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(c.getBackground());
                g2.fillRect(0, 0, c.getWidth(), c.getHeight());
                super.paint(g2, c);
                g2.dispose();
            }
        });

        // Add hover effect (optional)
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(buttonColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(buttonColor);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the submit button is clicked

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

                    // Show the prompt dialog
                    int choice = JOptionPane.showConfirmDialog(frame,
                            "Is " + profile.getDietCategory() + " the right choice?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        // User clicked "Yes"
                        frame.dispose();
                    } else {
                        // User clicked "No"
                        // Go back to the choice pane
                        frame.getContentPane().removeAll();
                        createDietPanel();
                        createSubmitButton();
                        frame.revalidate();
                        frame.repaint();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.setBackground(panelBackgroundColor); // Set the background color of the button panel to white
        buttonPanel.setOpaque(true); // Set button panel's opaque property to true
        buttonPanel.add(submitButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }


    /**
     * Method that adds the components to the frame
     */
    private void addComponentsToFrame() {
        frame.pack();
        frame.setVisible(true);
    }
}
