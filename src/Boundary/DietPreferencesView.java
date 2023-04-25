package src.Boundary;


import src.Entity.Profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DietPreferencesView {
    private JFrame frame;
    private JList<String> likeList;
    private DefaultListModel<String> likeListModel;
    private JList<String> dislikeList;
    private DefaultListModel<String> dislikeListModel;
    private JTextField searchField;
    private JButton likeButton;
    private JButton dislikeButton;
    private JButton submitButton;
    private ArrayList<JCheckBox> dietCheckboxes;
    private JPanel panel;

    public DietPreferencesView(Profile profile) {
        frame = new JFrame("Diet Preferences");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel dietPanel = new JPanel(new GridLayout(4, 1));
        dietPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        JLabel dietLabel = new JLabel("Select your diet preferences:");
        dietPanel.add(dietLabel);

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

        JPanel likePanel = new JPanel(new BorderLayout());
        likePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        JLabel likeLabel = new JLabel("Foods you like:");
        likePanel.add(likeLabel, BorderLayout.NORTH);

        likeListModel = new DefaultListModel<>();
        likeList = new JList<>(likeListModel);
        JScrollPane likeListScroller = new JScrollPane(likeList);
        likeListScroller.setPreferredSize(new Dimension(250, 80));
        likePanel.add(likeListScroller, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JLabel searchLabel = new JLabel("Search for food:");
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        likeButton = new JButton("Add to liked list");
        searchPanel.add(likeButton);

        JPanel dislikePanel = new JPanel(new BorderLayout());
        dislikePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        JLabel dislikeLabel = new JLabel("Foods you dislike:");
        dislikePanel.add(dislikeLabel, BorderLayout.NORTH);

        dislikeListModel = new DefaultListModel<>();
        dislikeList = new JList<>(dislikeListModel);
        JScrollPane dislikeListScroller = new JScrollPane(dislikeList);
        dislikeListScroller.setPreferredSize(new Dimension(250, 80));
        dislikePanel.add(dislikeListScroller, BorderLayout.CENTER);

        dislikeButton = new JButton("Add to disliked list");
        dislikePanel.add(dislikeButton, BorderLayout.SOUTH);

        leftPanel.add(dietPanel, BorderLayout.NORTH);


        leftPanel.add(likePanel, BorderLayout.CENTER);
        leftPanel.add(dislikePanel, BorderLayout.SOUTH);


        mainPanel.add(leftPanel, BorderLayout.WEST);


        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel selectedFoodsLabel = new JLabel("Selected foods:");
        rightPanel.add(selectedFoodsLabel, BorderLayout.NORTH);


        DefaultListModel<String> selectedFoodsListModel = new DefaultListModel<>();
        JList<String> selectedFoodsList = new JList<>(selectedFoodsListModel);
        JScrollPane selectedFoodsScroller = new JScrollPane(selectedFoodsList);
        selectedFoodsScroller.setPreferredSize(new Dimension(250, 200));
        rightPanel.add(selectedFoodsScroller, BorderLayout.CENTER);

// Create the submit button and add it to the rightPanel
        submitButton = new JButton("Submit");
        rightPanel.add(submitButton, BorderLayout.SOUTH);

// Add the rightPanel to the mainPanel
        mainPanel.add(rightPanel, BorderLayout.CENTER);

// Add the mainPanel to the frame
        frame.add(mainPanel);

// Set the frame visible
        frame.setVisible(true);
    }

    public void addSearchListener(ActionListener listener) {
        searchField.addActionListener(listener);
    }

    public void addLikeButtonListener(ActionListener listener) {
        likeButton.addActionListener(listener);
    }

    public void addDislikeButtonListener(ActionListener listener) {
        dislikeButton.addActionListener(listener);
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public void clearSearchText() {
        searchField.setText("");
    }

    public String getSelectedDiet() {
        String selectedDiet = "";
        for (JCheckBox checkbox : dietCheckboxes) {
            if (checkbox.isSelected()) {
                selectedDiet += checkbox.getText() + " ";
            }
        }
        return selectedDiet.trim();
    }

    public ArrayList<String> getSelectedLikedFoods() {
        ArrayList<String> selectedLikedFoods = new ArrayList<>();
        for (int i = 0; i < likeListModel.size(); i++) {
            selectedLikedFoods.add(likeListModel.getElementAt(i));
        }
        return selectedLikedFoods;
    }

    public ArrayList<String> getSelectedDislikedFoods() {
        ArrayList<String> selectedDislikedFoods = new ArrayList<>();
        for (int i = 0; i < dislikeListModel.size(); i++) {
            selectedDislikedFoods.add(dislikeListModel.getElementAt(i));
        }
        return selectedDislikedFoods;
    }

    public void addToLikedList(String food) {
        likeListModel.addElement(food);
    }

    public void addToDislikedList(String food) {
        dislikeListModel.addElement(food);
    }

    public void removeFromLikedList(String food) {
        likeListModel.removeElement(food);
    }

    public void removeFromDislikedList(String food) {
        dislikeListModel.removeElement(food);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }


        public JFrame getFrame() {
        return frame;
    }

    public ArrayList<JCheckBox> getDietCheckboxes() {
        return dietCheckboxes;
    }

    public JList<String> getLikeList() {
        return likeList;
    }

    public DefaultListModel<String> getLikeListModel() {
        return likeListModel;
    }

    public JList<String> getDislikeList() {
        return dislikeList;
    }

    public DefaultListModel<String> getDislikeListModel() {
        return dislikeListModel;
    }

    public String getSearchFieldText() {
        return searchField.getText();
    }

    public void addLikeListener(ActionListener likeListener) {
        likeButton.addActionListener(likeListener);
    }

    public void addDislikeListener(ActionListener dislikeListener) {
        dislikeButton.addActionListener(dislikeListener);
    }

    public void addSubmitListener(ActionListener submitListener) {
        submitButton.addActionListener(submitListener);
    }

}




