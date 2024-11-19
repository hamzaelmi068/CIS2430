package CIS2430.Labs.Lab5Final.lab5CIS2430;

import javax.swing.*; // this includes JFrame, JPanel, JButton, JTextArea

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePostPanel extends JPanel {
    private DiscussionBoard discussionBoard;
    private JTextField postUsernameField;
    private JTextArea contentArea;
    private JLabel createPostStatus;
    private JTextArea messageArea; // text area for messages

    public CreatePostPanel(DiscussionBoard discussionBoard) {
        this.discussionBoard = discussionBoard;
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel createFormPanel = new JPanel(new GridLayout(3, 2));

        JLabel createPostLabel = new JLabel("Create Post");
        createPostLabel.setHorizontalAlignment(JLabel.CENTER);
        add(createPostLabel, BorderLayout.NORTH);

        createFormPanel.add(new JLabel("Username:"));
        postUsernameField = new JTextField();
        createFormPanel.add(postUsernameField);

        createFormPanel.add(new JLabel("Post Body:"));
        contentArea = new JTextArea();
        createFormPanel.add(contentArea);

        JButton createPostButton = new JButton("Create Post");
        createFormPanel.add(createPostButton);
        createPostStatus = new JLabel();
        createFormPanel.add(createPostStatus);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // Add scrolling
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        createPostButton.addActionListener(e -> {
            String username = postUsernameField.getText();
            String content = contentArea.getText();
            if (content.isEmpty() || username.isEmpty()) {
                addMessage("Error: Fields cant be empty");
            } else {
                RegisterUser user = discussionBoard.getUserByUsername(username);
                if (user == null) {
                    addMessage("Error: User not found - " + username);
                } else {
                    discussionBoard.createTextPost(content, user);
                    addMessage("Success: Post Created by " + username + " - Content: " + content);
                    // Clear the fields after successful post creation
                    postUsernameField.setText("");
                    contentArea.setText("");
                }
            }
        });

        add(createFormPanel, BorderLayout.CENTER);
        // adding the message panel to the main panel
        add(messagePanel, BorderLayout.SOUTH);
    }

    // Helper method to add messages to the text area
    private void addMessage(String message) {
        messageArea.append(message + "\n");
        // Auto-scroll to the bottom
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}