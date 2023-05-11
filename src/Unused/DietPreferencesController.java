package src.Unused;


import org.xml.sax.SAXException;
import src.API.NutritionAPI;
import src.Boundary.DietPreferencesView;
import src.Entity.Profile;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The DietPreferencesController class handles the user's diet preferences input in the DietPreferencesView.
 *  It validates the user's input and processes it if it is valid. It also adds action listeners to the view's
 *  submit, like, and dislike buttons.
 */
/*
public class DietPreferencesController {
    private DietPreferencesView view;
    private Profile model;

    /**
     * Constructor for the DietPreferencesController class.
     * @param model the Profile model
     * @param view the DietPreferencesView view
     */

    /*
    public DietPreferencesController(Profile model, DietPreferencesView view) {
        this.model = model;
        this.view = view;
        view.addSubmitListener(new SubmitListener());
        view.addLikeListener(new LikeListener());
        view.addDislikeListener(new DislikeListener());
    }

    /**
     * ActionListener for the submit button.
     * Validates the user's input and processes and submits it if it is valid.
     */
        /*
    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validate the user's input
            boolean isValidInput = true;
            for (JCheckBox checkbox : view.getDietCheckboxes()) {
                if (checkbox.isSelected()) {
                    isValidInput = false;
                    break;
                }
            }
            if (view.getLikeListModel().isEmpty() && view.getDislikeListModel().isEmpty()) {
                isValidInput = false;
            }

            // If the input is valid, process and submit it
            if (isValidInput) {
                NutritionAPI api = new NutritionAPI();

                for (Object food : view.getLikeListModel().toArray()) {
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
                for (Object food : view.getDislikeListModel().toArray()) {
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
                JOptionPane.showMessageDialog(view.getFrame(),
                        "Please select at least one diet preference and add at least one liked or disliked food.",
                        "Invalid input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * ActionListener for the like button.
     * Adds a food item to the user's liked list if it is not already in the liked or disliked list.
     */
    /*class LikeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String food = view.getSearchFieldText().trim();
            if (!food.isEmpty()) {
                if (!view.getLikeListModel().contains(food) && !view.getDislikeListModel().contains(food)) {
                    view.getLikeListModel().addElement(food);
                    JOptionPane.showMessageDialog(null, food + " has been added to your liked list");
                } else {
                    JOptionPane.showMessageDialog(null, food + " is already in one of the lists", "Duplicate item",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    /**
     * A class that listens for the dislike button to be clicked and adds the entered food item to the dislike list
     * in the view if it is not already in either the like or dislike list.
     */
        /*
    class DislikeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String food = view.getSearchFieldText().trim();
            if (!food.isEmpty()) {
                if (!view.getDislikeListModel().contains(food) && !view.getLikeListModel().contains(food)) {
                    view.getDislikeListModel().addElement(food);
                    JOptionPane.showMessageDialog(null, food + " has been added to your disliked list");
                } else {
                    JOptionPane.showMessageDialog(null, food + " is already in one of the lists", "Duplicate item",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}

         */
