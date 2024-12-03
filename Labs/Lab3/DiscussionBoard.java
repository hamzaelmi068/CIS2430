package CIS2430.Labs.Lab3;

// imports
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import javax.swing.JFrame;

import CIS2430.Labs.Lab5Final.RegisterUser;
import CIS2430.Labs.Lab5Final.TextPost;

/*
 * Sample Menu
 * (1) Create new user
 * (2) Create new post
 * (3) View all posts with a given username
 * (4) End Program
 */

public class DiscussionBoard {
    // arraylist for storing all posts
    private ArrayList<TextPost> posts;
    // arraylist for storing all users for the discussion board
    private ArrayList<RegisterUser> users;
    // using a hasmap to store indices of post usernames
    private HashMap<String, ArrayList<Integer>> userPostIndices;

    /**
     * Constructor the collections needed for my discussion board
     */
    public DiscussionBoard() {
        posts = new ArrayList<>();
        users = new ArrayList<>();
        userPostIndices = new HashMap<>();
    }

    public static void main(String[] args) {
        // object
        DiscussionBoard board = new DiscussionBoard();
        // user input, using the scanner below
        Scanner scanner = new Scanner(System.in);

        int userChoice;
        do {
            System.out.println("\n");
            System.out.println("***The Discussion board menu***");
            System.out.println("1. Create new User");
            System.out.println("2. Create new post");
            System.out.println("3. View all posts with a given username");
            System.out.println("4. End program.");

            System.out.println("Enter your choice from 1-3: ");
            userChoice = getValidIntInput(scanner, 1, 4);
            // utilizing the try catch exception to handle user errors below
            try {
                switch (userChoice) {
                    case 1:
                        board.createUser(scanner);
                        break;
                    case 2:
                        board.createPost(scanner);
                        break;
                    case 3:
                        board.viewAllPostsGivenUserName(scanner);
                        break;
                    case 4:
                        System.out.println("End program.");
                        break;
                    default:
                        System.out.println("Invalid menu choice, 1-4, try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (userChoice != 4);
    }

    /**
     * Helper method to get valid integer input within a specified range
     */
    private static int getValidIntInput(Scanner scanner, int min, int max) {
        do {
            try {
                System.out.printf("Enter a valid integer input (%d-%d): ", min, max);
                String input = scanner.nextLine();
                int number = Integer.parseInt(input);

                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.printf("Error: Number must be between %d and %d%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer");
            }
        } while (true);
    }

    /**
     * Creates a new user with input validation
     * 
     * @param scanner Scanner object for input
     * @throws Exception if post creation fails
     */
    public void createUser(Scanner scanner) {
        try {
            // Prompting for fullname
            System.out.println("Enter your fullname: ");
            String fullName = scanner.nextLine().trim(); // Remove toLowerCase() for fullName

            // Prompting for username (Optional)
            System.out.println("Enter your username (Optional): ");
            String userName = scanner.nextLine().trim().toLowerCase();

            // If username is not empty, check for uniqueness
            if (!userName.isEmpty()) {
                for (RegisterUser user : users) {
                    if (user.getResgiterUserName().equals(userName)) {
                        System.out.println("That username already exists. Please try again.");
                        return; // Return to menu instead of looping
                    }
                }
            }
            // Create new user (note the order: userName, fullName)
            RegisterUser newUser = new RegisterUser(userName, fullName);
            users.add(newUser);
            System.out.println("User created successfully: " + newUser.toString() + "\n");

        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    /**
     * Creates a new post with input validation
     * 
     * @param scanner Scanner object for input
     * @throws Exception if post creation fails
     */
    public void createPost(Scanner scanner) throws Exception {

        try {

            // checking if there are any users registered first
            if (users.isEmpty()) {
                System.out.println("there arent any users right now, try creating a user first :)");
                return;
            }
            // prompt for username, trim & lowercase
            System.out.println("Enter the username: ");
            String userName = scanner.nextLine().trim().toLowerCase();

            RegisterUser asuer = null;
            for (RegisterUser u : users) {
                System.out.println("Checking user: " + u.getResgiterUserName()); // debugging output
                if (u.getResgiterUserName().equals(userName)) {
                    asuer = u;
                    break;
                }
            }

            if (asuer == null) {
                // if the user is empty or null, we print the message below
                System.out.println("The User is not registered. Please register first.");
                return;
            }

            // prompt for title and content
            System.out.println("Enter the title: ");
            String title = scanner.nextLine();
            System.out.println("Enter the text post content: ");
            String content = scanner.nextLine();

            // creating new object for post
            TextPost apost = new TextPost(title, content, asuer);
            // adding a post to the post array list
            posts.add(apost);

            // updating the hasmap below
            // Update the user's post indices in the HashMap
            if (!userPostIndices.containsKey(userName)) {
                userPostIndices.put(userName, new ArrayList<>());
            }
            userPostIndices.get(userName).add(posts.size() - 1);

            System.out.println("Post created successfully!");

        } catch (Exception e) {
            System.out.println("Error creating post: " + e.getMessage());
            throw e; // Re-throw to be handled by main menu
        }
    }

    /**
     * Displaying all posts by a specific username using HashMaps
     */
    public void viewAllPostsGivenUserName(Scanner scanner) throws Exception {
        try {
            System.out.println("Enter the username: ");
            String userNameSearch = scanner.nextLine().trim().toLowerCase();

            // checking if the user exists in the hashmap that tracks post indices
            if (!userPostIndices.containsKey(userNameSearch)) {
                System.out.println("No posts found with this username");
                return;
            }
            // getting the list of post indices that are lniked to the username from input
            // above
            ArrayList<Integer> userPostIndices = this.userPostIndices.get(userNameSearch);

            // checking if user made any posts yet
            if (userPostIndices.isEmpty()) {
                // if none, we print this error message
                System.out.println("user did not make any posts yet");
                return;
            }

            System.out.println("\n*** Posts by @" + userNameSearch + " ****");
            // Print each post using the stored indices
            for (Integer index : userPostIndices) {
                System.out.println("\n" + posts.get(index));
                System.out.println("-----------------");
            }

        } catch (Exception e) {
            System.out.println("Error retrieving posts: " + e.getMessage());
        }
    }
}