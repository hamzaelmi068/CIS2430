package Labs.Lab4.lab4CIS2430;

public class User {
    private String userName;
    private String fullName;
    private static int defaultUser = 0;

    // constructor for user class below
    public User(String userName, String fullName) throws Exception {

        // checking if username is empty, if it is, we default it below
        if (userName == null || userName.trim().isEmpty()) {
            // Generate default username if none provided
            defaultUser++;
            this.userName = "user" + defaultUser;
        } else {
            // Validate username format (assuming single word requirement)
            if (userName.trim().contains(" ")) {
                throw new Exception("Username has to be a single word");
            }
            this.userName = userName.trim().toLowerCase();
        }
        // checking if fullname is empty, error handeling with exception
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new Exception("Fullname cant be empty");
        }

        this.fullName = fullName;

    }

    // getters for my User class
    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUserName(String userName) throws Exception {
        if (userName == null || userName.trim().isEmpty()) {
            throw new Exception("Username cant be empty..");
        } else {
            // Validate username format (assuming single word requirement)
            if (userName.trim().contains(" ")) {
                throw new Exception("Username has to be a single word");
            }
            this.userName = userName.trim().toLowerCase();
        }
    }

    public void setFullName(String fullName) throws Exception {
        // validating fullname, error handeling
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new Exception("full name cant be empty");
        }
        this.fullName = fullName.trim(); // eliminating leading whitespaces from string
    }

    @Override
    public String toString() {
        return fullName + " (Username:" + userName + ")"; // toString provides a string representation of the object
    }

    @Override
    public boolean equals(Object obj) {
        // 1. check if the obj is compared with itself
        if (this == obj) {
            return true;
        }

        // 2. check if object is null or not an instance of user
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // 3. cast object to user and compare
        User otherUser = (User) obj;
        return this.userName.equals(otherUser.userName) && this.fullName.equals(otherUser.fullName);
    }
}