package src.Boundary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DietPreferences window = new DietPreferences();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DietPreferences() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Diet Preferences");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
    
        mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        String[] diets = {"Vegetarian", "Gluten-free", "Vegan", "Dairy-free", "Carnivore", "Keto", "Islamic", "Pescatarian", "Keto", "Paleo"};
        dietCheckboxes = new JCheckBox[diets.length];
        for (int i = 0; i < diets.length; i++) {
            dietCheckboxes[i] = new JCheckBox(diets[i]);
            checkboxPanel.add(dietCheckboxes[i]);
        }
        mainPanel.add(checkboxPanel);
    
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        searchField = new JTextField(10);
        searchField.setMaximumSize(new Dimension(350, 100)); // set the preferred size
        listPanel.add(searchField);
    
        likeButton = new JButton("Like");
        dislikeButton = new JButton("Dislike");
        listPanel.add(likeButton);
        listPanel.add(dislikeButton);
    
        likeListModel = new DefaultListModel<>();
        likeList = new JList<>(likeListModel);
        JScrollPane likeListScrollPane = new JScrollPane(likeList);
        likeListScrollPane.setMaximumSize(new Dimension(300, 350)); // set the preferred size
        listPanel.add(likeListScrollPane);
    
        dislikeListModel = new DefaultListModel<>();
        dislikeList = new JList<>(dislikeListModel);
        JScrollPane dislikeListScrollPane = new JScrollPane(dislikeList);
        dislikeListScrollPane.setMaximumSize(new Dimension(300, 350)); // set the preferred size
        listPanel.add(dislikeListScrollPane);
    
        mainPanel.add(listPanel);
    
        submitButton = new JButton("Submit");
        mainPanel.add(submitButton);
    
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = searchField.getText().trim();
                if (!food.isEmpty() && !likeListModel.contains(food)) {
                    likeListModel.addElement(food);
                }
            }
        });
    
        dislikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = searchField.getText().trim();
                if (!food.isEmpty() && !dislikeListModel.contains(food)) {
                    dislikeListModel.addElement(food);
                }
            }
        });
    
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process and submit the information here
                // For example, store them in a database or send them to an API
            }
        });
    }
    
}