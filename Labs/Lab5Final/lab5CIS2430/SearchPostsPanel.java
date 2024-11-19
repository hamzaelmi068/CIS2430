package CIS2430.Labs.Lab5Final.lab5CIS2430;

import javax.swing.*; // this includes JFrame, JPanel, JButton, JTextArea

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// SearchPostsPanel.java
public class SearchPostsPanel extends JPanel {
    private DiscussionBoard discussionBoard;
    private JTextField searchUsernameField;
    private JTextArea searchResults;
    private JTextArea messageArea; // adding a text area for the messages from console

    public SearchPostsPanel(DiscussionBoard discussionBoard) {
        this.discussionBoard = discussionBoard;
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        // Create a panel to hold the search form
        JPanel searchFormPanel = new JPanel(new GridLayout(3, 2));

        // Add the search post label at the top
        JLabel searchPostLabel = new JLabel("Search Post");
        searchPostLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the label
        add(searchPostLabel, BorderLayout.NORTH);

        // Add components to the search form panel
        searchFormPanel.add(new JLabel("Username:"));
        searchUsernameField = new JTextField();
        searchFormPanel.add(searchUsernameField);

        JButton searchButton = new JButton("Search");
        searchFormPanel.add(searchButton);

        searchResults = new JTextArea();
        searchResults.setEditable(false); // Make it read-only
        searchFormPanel.add(new JScrollPane(searchResults)); // Add scrolling

        // Add message panel for error and success messages
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // Add scrolling
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        // Add the form panel to the center of SearchPostsPanel
        add(searchFormPanel, BorderLayout.CENTER);
        // Add the message panel to the bottom of SearchPostsPanel
        add(messagePanel, BorderLayout.SOUTH);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = searchUsernameField.getText();
                if (username.isEmpty()) {
                    addMessage("Error: Username cannot be blank.");
                } else {
                    String results = discussionBoard.viewAllPostsGivenUserName(username);
                    if (results.isEmpty() || results.equals("No posts found for user: " + username)) {
                        addMessage("Notice: No posts found for user - " + username);
                    } else {
                        addMessage("Success: Found posts for the User: " + username);
                        searchResults.setText(results);
                    }
                    searchUsernameField.setText(""); // Clear the search field after search
                }
            }
        });
    }

    // Helper method to add messages to the text area
    private void addMessage(String message) {
        messageArea.append(message + "\n");
        // Auto-scroll to the bottom
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}
