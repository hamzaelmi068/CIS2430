package CIS2430.Labs.Lab5Final.lab5CIS2430;

import java.util.ArrayList;

/**
 * CIS*2430 F24 Lab 5: Discussion Board GUI
 * Hamza Elmi
 * student ID: 1305966
 * 
 * Compile: javac CIS2430/Labs/Lab5Final/lab5CIS2430/*.java
 * Run: java CIS2430.Labs.Lab5Final.lab5CIS2430.DiscussionBoardGUI
 */

/*
 * Sample Menu
 * (1) Create new user
 * (2) Create new post
 * (3) View all posts with a given username
 * (4) End Program
 */

public class DiscussionBoard {
    private ArrayList<RegisterUser> users;
    private ArrayList<TextPost> posts;

    public DiscussionBoard() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public void createUser(String fullName, String userName) {
        try {
            if (!userName.isEmpty()) {
                for (RegisterUser user : users) {
                    if (user.getResgiterUserName().equals(userName)) {
                        System.out.println("That username already exists. Please try again.");
                        return; // Return to menu instead of looping
                    }
                }
            }
            RegisterUser newUser = new RegisterUser(userName, fullName);
            users.add(newUser);
            System.out.println("User created successfully: " + newUser.toString());
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public void createTextPost(String content, RegisterUser user) {
        try {
            TextPost newPost = new TextPost(content, user);
            posts.add(newPost);
            System.out.println("Post created successfully: " + newPost.toString());
        } catch (Exception e) {
            System.out.println("Error creating post: " + e.getMessage());
        }
    }

    public String viewAllPostsGivenUserName(String userName) {
        StringBuilder results = new StringBuilder();
        for (TextPost post : posts) {
            if (post.getUser().getResgiterUserName().equalsIgnoreCase(userName)) {
                results.append(post.toString()).append("\n");
            }
        }
        if (results.length() == 0) {
            return "No posts found for user: " + userName;
        } else {
            return results.toString();
        }
    }

    public RegisterUser getUserByUsername(String userName) {
        for (RegisterUser user : users) {
            if (user.getResgiterUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null; // Return null if user not found
    }
}
