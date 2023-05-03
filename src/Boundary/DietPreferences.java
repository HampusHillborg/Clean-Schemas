package src.Boundary;

import org.xml.sax.SAXException;
import src.API.NutritionAPI;
import src.Entity.Profile;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.HashSet;

/**

 The DietPreferences class allows the user to choose their dietary preferences
 through a graphical interface. The class creates JPanel and includes various
 checkboxes that allow the user to select their preferred diets. The selected diets
 are used to filter a list of food items that are displayed to the user.
 Additionally, the class includes a method to check if a given food item is part
 of one of the selected diets, and a method to update the displayed food items
 based on the selected diets and any user-added items.
 */

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

    // Add the arrays of food items for each diet here
    private String[] vegetarianFoods = { "Tofu", "Bönor", "Cashewnötter", "Valnötter", "Frön", "Spannmål", "Tomat",
            "Linser", "Kikärtor", "Tempeh", "Spenat", "Broccoli", "Sötpotatis", "Äpplen" };
    private String[] glutenFreeFoods = { "Ris", "Potatis", "Quinoa", "Majs", "Bönor", "Nötter", "Frön", "Grönsaker",
            "Äpplen", "Kikärtor", "Hirs", "Bovete", "Durra", "Amarant" };
    private String[] veganFoods = { "Tofu", "Tempeh", "Seitan", "Bönor", "Linser", "Kikärtor", "Quinoa", "Bulgur",
            "Korn", "Fullkornsris", "Havre", "Äpplen", "Guacamole" };
    private String[] dairyFreeFoods = { "Mandelmjölk", "Kokosmjölk", "Sojamjölk", "Cashewost", "Näringsjäst",
            "Olivolja" };
    private String[] carnivoreFoods = { "Nötkött", "Fläskkött", "Kyckling", "Fisk", "Ägg", "Smör", "Ost" };
    private String[] ketoFoods = { "Kyckling", "Lax", "Ägg", "Cashewnötter", "Pumpakärnor", "Olivolja", "Kokosolja",
            "Avokado" };
    private String[] halalFoods = { "Kycklingbröst", "Nötfärs", "Fisk", "Blåbär", "Jordgubbar", "Aubergine",
            "Mozzarella" };
    private String[] pescatarianFoods = { "Lax", "Räkor", "Grönsaker", "Blåbär", "Havre", "Cashewnötter", "Frön" };
    private String[] paleoFoods = { "Nötfärs", "Kycklingbröst", "Lax", "Ägg", "Aubergine" };

    private String[] diets = { "Vegetarian", "Gluten-free", "Vegan", "Dairy-free", "Carnivore", "Keto", "Halal",
            "Pescatarian", "Paleo" };

    private String[] getFoodsForDiet(String diet) {
        switch (diet) {
            case "Vegetarian":
                return vegetarianFoods;
            case "Gluten-free":
                return glutenFreeFoods;
            case "Vegan":
                return veganFoods;
            case "Dairy-free":
                return dairyFreeFoods;
            case "Carnivore":
                return carnivoreFoods;
            case "Keto":
                return ketoFoods;
            case "Halal":
                return halalFoods;
            case "Pescatarian":
                return pescatarianFoods;
            case "Paleo":
                return paleoFoods;
            default:
                return new String[] {};
        }
    }

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
        dietCheckboxes = new JCheckBox[diets.length];
        for (int i = 0; i < diets.length; i++) {
            dietCheckboxes[i] = new JCheckBox(diets[i]);
            dietCheckboxes[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    updateDisplayedFoods();
                }
            });
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
                if (!food.isEmpty()) {
                    if (!likeListModel.contains(food) && !dislikeListModel.contains(food)) {
                        likeListModel.addElement(food);
                        JOptionPane.showMessageDialog(null, food + " has been added to your liked list");
                    } else {
                        JOptionPane.showMessageDialog(null, food + " is already in one of the lists", "Duplicate item",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        dislikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = searchField.getText().trim();
                if (!food.isEmpty()) {
                    if (!dislikeListModel.contains(food) && !likeListModel.contains(food)) {
                        dislikeListModel.addElement(food);
                        JOptionPane.showMessageDialog(null, food + " has been added to your disliked list");
                    } else {
                        JOptionPane.showMessageDialog(null, food + " is already in one of the lists", "Duplicate item",
                                JOptionPane.WARNING_MESSAGE);
                    }
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
                        isValidInput = true;
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
                    JOptionPane.showMessageDialog(frame,
                            "Please select at least one diet preference and add at least one liked or disliked food.",
                            "Invalid input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     Updates the displayed list of foods based on the selected diet checkboxes and any user-added items.
     First, it stores the user-added items and removes any non-diet items from the list.
     Then, it populates the list with foods for each selected diet checkbox.
     Finally, it adds back the user-added items to the list.
     This method is called whenever a diet checkbox is selected or deselected.
     */
    private void updateDisplayedFoods() {
        // Store user-added items
        HashSet<String> userAddedItems = new HashSet<>();
        for (int i = 0; i < likeListModel.getSize(); i++) {
            String item = likeListModel.getElementAt(i);
            if (!isDietItem(item)) {
                userAddedItems.add(item);
            }
        }

        // Clear and repopulate the list
        likeListModel.clear();
        for (int i = 0; i < dietCheckboxes.length; i++) {
            if (dietCheckboxes[i].isSelected()) {
                String[] selectedFoods = getFoodsForDiet(diets[i]);

                for (String food : selectedFoods) {
                    if (!likeListModel.contains(food)) {
                        likeListModel.addElement(food);
                    }
                }
            }
        }

        // Add back user-added items
        for (String item : userAddedItems) {
            likeListModel.addElement(item);
        }
    }

    /**
     Checks if an item is a diet item by iterating through the available diets and their associated foods.
     @param item the item to check
     @return true if the item is a diet item, false otherwise
     */
    private boolean isDietItem(String item) {
        for (String diet : diets) {
            String[] foods = getFoodsForDiet(diet);
            for (String food : foods) {
                if (item.equals(food)) {
                    return true;
                }
            }
        }
        return false;
    }

}