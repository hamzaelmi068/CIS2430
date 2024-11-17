package CIS2430.Labs.Lab5.lab5CIS2430;

public class RegisterUser {
    private String userName;
    private String fullName;
    private static int defaultUser = 0;

    // constructor for my registeruser class
    public RegisterUser(String userName, String fullName) throws Exception {
        // checking if username is empty
        if (userName == null || userName.trim().isEmpty()) {
            // generating default user if none is provided, or empty
            defaultUser++;
            this.userName = "user" + defaultUser;
        } else {
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

    // mutator and accessors
    public String getResgiterUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    // setter for username
    public void setRegisterUserName(String userName) throws Exception {
        if (userName == null || userName.trim().isEmpty()) {
            throw new Exception("username cant be empty.");
        } else {
            // validate username format
            if (userName.trim().contains(" ")) {
                throw new Exception("username has to be a single word");
            }
            this.userName = userName.trim().toLowerCase();
        }
    }

    // accessor for fullname
    public void setFullName(String fullName) throws Exception {
        // validating fullname, error handeling
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new Exception("full name cant be empty");
        }
        this.fullName = fullName.trim(); // eliminating leading whitespaces from string
    }

    @Override
    // tostring method below
    public String toString() {
        return fullName + " (Username: " + userName + ")";
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
        RegisterUser otherUser = (RegisterUser) obj;
        return this.userName.equals(otherUser.userName) && this.fullName.equals(otherUser.fullName);
    }

}
