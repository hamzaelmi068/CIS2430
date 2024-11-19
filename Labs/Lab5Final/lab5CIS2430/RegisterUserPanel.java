package CIS2430.Labs.Lab5Final.lab5CIS2430;

import javax.swing.*; // this includes JFrame, JPanel, JButton, JTextArea

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// RegisterUserPanel.java
public class RegisterUserPanel extends JPanel {
    private DiscussionBoard discussionBoard;
    private JTextField fullnameField; // text field for the fullname
    private JTextField usernameField; // text field for username
    private JLabel registerStatus;
    private JTextArea messageArea; // text area for the messages

    public RegisterUserPanel(DiscussionBoard discussionBoard) {
        this.discussionBoard = discussionBoard;
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        // Create a panel to hold the Register User form
        JPanel registerFormPanel = new JPanel(new GridLayout(3, 2));

        // Add the "Register User" label at the top
        JLabel registerUserLabel = new JLabel("Register User");
        registerUserLabel.setHorizontalAlignment(JLabel.CENTER);
        add(registerUserLabel, BorderLayout.NORTH);

        // Add components to the Register Form panel
        registerFormPanel.add(new JLabel("Full Name:"));
        fullnameField = new JTextField();
        registerFormPanel.add(fullnameField);

        registerFormPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        registerFormPanel.add(usernameField);

        JButton registerButton = new JButton("Register");
        registerFormPanel.add(registerButton);
        registerStatus = new JLabel();
        registerFormPanel.add(registerStatus);

        // creating a message display panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // Add scrolling
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String fullName = fullnameField.getText();

            if (username.isEmpty() || fullName.isBlank()) {
                addMessage("Error: Fields cannot be empty.");
            } else {
                discussionBoard.createUser(fullName, username);
                addMessage("Success: User registered - " + fullName + " (" + username + ")");
                // Clear the fields after successful registration
                fullnameField.setText("");
                usernameField.setText("");
            }
        });

        // Add panels to main panel
        add(registerFormPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    // Helper method to add messages to the text area
    private void addMessage(String message) {
        messageArea.append(message + "\n");
        // Auto-scroll to the bottom
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}