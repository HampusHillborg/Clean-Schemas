package src.Boundary;

import org.xml.sax.SAXException;
import src.API.NutritionAPI;
import src.Entity.Profile;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DietPreferences {
    private JFrame frame;
    private JPanel mainPanel;
    private JCheckBox[] dietCheckboxes;
    private JTextField searchField;
    private JButton likeButton;
    private JButton dislikeButton;
    private JList<String> likeList;
    private JList<String> dislikeList;
    private JButton submitButton;
    private DefaultListModel<String> likeListModel;
    private DefaultListModel<String> dislikeListModel;



    public DietPreferences(Profile userprofile) {

        frame = new JFrame("Diet Preferences");
        frame.setBounds(100, 100, 580, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        String[] diets = { "Vegetarian", "Gluten-free", "Vegan", "Dairy-free", "Carnivore", "Keto", "Halal",
                "Pescatarian", "Keto", "Paleo" };
        dietCheckboxes = new JCheckBox[diets.length];
        for (int i = 0; i < diets.length; i++) {
            dietCheckboxes[i] = new JCheckBox(diets[i]);
            checkboxPanel.add(dietCheckboxes[i]);
        }
        mainPanel.add(checkboxPanel);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        searchField = new JTextField(10);
        searchField.setMaximumSize(new Dimension(320, 20)); // set the preferred size
        listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add some padding above the search field
        listPanel.add(searchField);

        likeButton = new JButton("Like");
        dislikeButton = new JButton("Dislike");

        likeListModel = new DefaultListModel<>();
        likeList = new JList<>(likeListModel);
        JScrollPane likeListScrollPane = new JScrollPane(likeList);
        likeListScrollPane.setMaximumSize(new Dimension(150, 200)); // set the preferred size

        dislikeListModel = new DefaultListModel<>();
        dislikeList = new JList<>(dislikeListModel);
        JScrollPane dislikeListScrollPane = new JScrollPane(dislikeList);
        dislikeListScrollPane.setMaximumSize(new Dimension(150, 200)); // set the preferred size

        JPanel likedDislikePanel = new JPanel();
        likedDislikePanel.setLayout(new BoxLayout(likedDislikePanel, BoxLayout.X_AXIS));
        likedDislikePanel.add(likeListScrollPane);
        likedDislikePanel.add(Box.createRigidArea(new Dimension(20, 0))); // add a rigid area
        likedDislikePanel.add(dislikeListScrollPane);

        listPanel.add(Box.createRigidArea(new Dimension(0, 35))); // add some padding below the search field
        listPanel.add(likedDislikePanel);
        listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // add some padding below the likedDislikePanel

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(likeButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // add a rigid area
        buttonPanel.add(dislikeButton);

        listPanel.add(buttonPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(10, 0))); // add some padding between the checkbox panel and the
                                                                  // list panel
        mainPanel.add(listPanel);

        submitButton = new JButton("Submit");
        mainPanel.add(Box.createRigidArea(new Dimension(20, 0))); // add some padding to the left of the submit button
        mainPanel.add(submitButton);

        JButton removeButton = new JButton("Remove");
        frame.setVisible(true);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (likeList.getSelectedIndex() != -1) {
                    likeListModel.remove(likeList.getSelectedIndex());
                } else if (dislikeList.getSelectedIndex() != -1) {
                    dislikeListModel.remove(dislikeList.getSelectedIndex());
                }
            }
        });
        mainPanel.add(removeButton);

        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = searchField.getText().trim();
                if (!food.isEmpty() && !likeListModel.contains(food)) {
                    likeListModel.addElement(food);
                    JOptionPane.showMessageDialog(null, food + " has been added to your liked list");
                }
            }
        });

        dislikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = searchField.getText().trim();
                if (!food.isEmpty() && !dislikeListModel.contains(food)) {
                    dislikeListModel.addElement(food);
                    JOptionPane.showMessageDialog(null, food + " has been added to your Disliked list");
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate the user's input
                boolean isValidInput = true;
                for (JCheckBox checkbox : dietCheckboxes) {
                    if (checkbox.isSelected()) {
                        isValidInput = false;
                        break;
                    }
                }
                if (likeListModel.isEmpty() && dislikeListModel.isEmpty()) {
                    isValidInput = false;
                }

                // If the input is valid, process and submit it
                if (isValidInput) {
                    NutritionAPI api = new NutritionAPI();

                    for (Object food : likeListModel.toArray()) {
                        try {
                            String proteinValue = api.getProteinValue(food.toString());
                        } catch (ParserConfigurationException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (SAXException ex) {
                            throw new RuntimeException(ex);
                        }
                        // Process the protein value for the liked food item here
                    }
                    for (Object food : dislikeListModel.toArray()) {
                        try {
                            String proteinValue = api.getProteinValue(food.toString());
                        } catch (ParserConfigurationException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (SAXException ex) {
                            throw new RuntimeException(ex);
                        }
                        // Process the protein value for the disliked food item here
                    }
                    // Process and submit the information here
                    // For example, store them in a database or send them to an API
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select at least one diet preference and add at least one liked or disliked food.", "Invalid input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }
}