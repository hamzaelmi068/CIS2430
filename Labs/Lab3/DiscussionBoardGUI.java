package CIS2430.Labs.Lab3;

import javax.swing.*; // this includes JFrame, JPanel, JButton, JTextArea

import CIS2430.Labs.Lab5Final.lab5CIS2430.CreatePostPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CIS*2430 F24 Lab 5: Discussion Board GUI
 * Hamza Elmi
 * student ID: 1305966
 * 
 * Compile: javac CIS2430/Labs/Lab5/lab5CIS2430/*.java
 * Run: java CIS2430.Labs.Lab5.lab5CIS2430.DiscussionBoardGUI
 */

/*
 * Sample Menu
 * (1) Create new user
 * (2) Create new post
 * (3) View all posts with a given username
 * (4) End Program
 */

public class DiscussionBoardGUI extends JFrame {
    private DiscussionBoard discussionBoard;
    private RegisterUserPanel registerUserPanel;
    private CreatePostPanel createPostPanel;
    private SearchPostsPanel searchPostsPanel;

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
        registerUserPanel = new RegisterUserPanel(discussionBoard);
        createPostPanel = new CreatePostPanel(discussionBoard);
        searchPostsPanel = new SearchPostsPanel(discussionBoard);

        // Add action listeners for menu items
        registerUserMenuItem.addActionListener(e -> switchPanel(registerUserPanel));
        createPostMenuItem.addActionListener(e -> switchPanel(createPostPanel));
        searchPostsMenuItem.addActionListener(e -> switchPanel(searchPostsPanel));

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
        SwingUtilities.invokeLater(() -> new DiscussionBoardGUI().setVisible(true));
    }
}