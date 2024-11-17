package CIS2430.Labs.Lab5.lab5CIS2430;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscussionBoardGUI extends JFrame {
    private DiscussionBoard discussionBoard;

    // Panels for different functionalities
    private JPanel registerUserPanel;
    private JPanel createPostPanel;
    private JPanel searchPostsPanel;

    public DiscussionBoardGUI() {
        discussionBoard = new DiscussionBoard();
        setTitle("Discussion Board");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");

        // Create menu items
        JMenuItem registerUserMenuItem = new JMenuItem("Register User");
        JMenuItem createPostMenuItem = new JMenuItem("Create Text Post");
        JMenuItem searchPostsMenuItem = new JMenuItem("Search Posts");

        // Add menu items to the menu
        optionsMenu.add(registerUserMenuItem);
        optionsMenu.add(createPostMenuItem);
        optionsMenu.add(searchPostsMenuItem);
        menuBar.add(optionsMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Create panels
        registerUserPanel = new JPanel();
        createPostPanel = new JPanel();
        searchPostsPanel = new JPanel();

        // Add components to the Register User panel
        registerUserPanel.setLayout(new GridLayout(3, 2));
        registerUserPanel.add(new JLabel("Full Name:"));
        JTextField fullnameField = new JTextField();
        registerUserPanel.add(fullnameField);
        registerUserPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        registerUserPanel.add(usernameField);
        JButton registerButton = new JButton("Register");
        registerUserPanel.add(registerButton);
        JLabel registerStatus = new JLabel();
        registerUserPanel.add(registerStatus);

        // Add components to the Create Post panel
        createPostPanel.setLayout(new GridLayout(4, 2));
        createPostPanel.add(new JLabel("Username:"));
        JTextField postUsernameField = new JTextField();
        createPostPanel.add(postUsernameField);
        createPostPanel.add(new JLabel("Title:"));
        JTextField titleField = new JTextField();
        createPostPanel.add(titleField);
        createPostPanel.add(new JLabel("Post Body:"));
        JTextArea contentArea = new JTextArea();
        createPostPanel.add(contentArea);
        JButton createPostButton = new JButton("Create Post");
        createPostPanel.add(createPostButton);
        JLabel createPostStatus = new JLabel();
        createPostPanel.add(createPostStatus);

        // Add components to the Search Posts panel
        searchPostsPanel.setLayout(new GridLayout(3, 2));
        searchPostsPanel.add(new JLabel("Username:"));
        JTextField searchUsernameField = new JTextField();
        searchPostsPanel.add(searchUsernameField);
        JButton searchButton = new JButton("Search");
        searchPostsPanel.add(searchButton);
        JTextArea searchResults = new JTextArea();
        searchPostsPanel.add(searchResults);

        // Add action listeners
        registerUserMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(registerUserPanel);
            }
        });

        createPostMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(createPostPanel);
            }
        });

        searchPostsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(searchPostsPanel);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String fullName = fullnameField.getText();

                if (username.isEmpty() || fullName.isBlank()) {
                    registerStatus.setText("That Field cannot be empty.");
                } else {
                    discussionBoard.createUser(fullName, username);
                    registerStatus.setText("User registered successfully.");
                }
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = postUsernameField.getText();
                String title = titleField.getText();
                String content = contentArea.getText();
                if (title.isEmpty() || content.isEmpty() || username.isEmpty()) {
                    createPostStatus.setText("All fields must be filled.");
                } else {
                    RegisterUser user = discussionBoard.getUserByUsername(username);
                    if (user == null) {
                        createPostStatus.setText("User not found.");
                    } else {
                        discussionBoard.createTextPost(title, content, user);
                        createPostStatus.setText("Post created successfully.");
                    }
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = searchUsernameField.getText();
                if (username.isEmpty()) {
                    searchResults.setText("Username cannot be blank.");
                } else {
                    String results = discussionBoard.viewAllPostsGivenUserName(username);
                    searchResults.setText(results);
                }
            }
        });

        // Set the initial panel
        switchPanel(registerUserPanel);
    }

    private void switchPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DiscussionBoardGUI().setVisible(true);
            }
        });
    }
}
