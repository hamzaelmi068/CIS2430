package CIS2430.Labs.Lab5.lab5CIS2430;

public class TextPost {
    private String titleOfPost;
    private String contentofPost;
    private static int postCounter = 0;
    private int postId;
    private RegisterUser user;

    // constructor
    public TextPost(String titleOfPost, String contentofPost, RegisterUser user) throws Exception {
        // validating title
        if (titleOfPost == null || titleOfPost.trim().isEmpty()) {
            throw new Exception("title of the post cant be empty");
        }
        // validating content of post
        if (contentofPost == null || contentofPost.trim().isEmpty()) {
            throw new Exception("content cant be empty....");
        }
        // Validate user
        if (user == null) {
            throw new Exception("Post must have a valid user");
        }

        this.titleOfPost = titleOfPost.trim();
        this.contentofPost = contentofPost.trim();
        this.user = user;

        // generating the post ids
        postCounter++;
        this.postId = postCounter;
    }

    // getter methods (accessor) to retrieve data from our Post class private
    // instances
    public String getTitle() {
        return titleOfPost;
    }

    public String getContent() {
        return contentofPost;
    }

    public RegisterUser getUser() {
        return user;
    }

    // setter methods with error handeling, using try/catch and
    public void setTitle(String titeOfPost) throws Exception {
        if (titeOfPost == null || titleOfPost.trim().isEmpty()) {
            throw new Exception("The title is empty");
        }
        this.titleOfPost = titleOfPost.trim();
    }

    public void setContent(String contentOfPost) throws Exception {
        if (contentOfPost == null || contentOfPost.trim().isEmpty()) {
            throw new Exception("Post content cannot be empty");
        }
        this.contentofPost = contentOfPost.trim();
    }

    @Override
    public String toString() {
        return ("Created By:" + user.getFullName() + "(@<" + user.getResgiterUserName() + ">)" + "\ntitleOfPost: "
                + titleOfPost
                + "\n" + contentofPost);
    }

    @Override
    public boolean equals(Object obj) {
        // check if object compared with itself
        if (this == obj) {
            return true;
        }

        // 2. Check if the object is null or not an instance of Post
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // 3. cast object to post and compare fields
        TextPost otherPost = (TextPost) obj;
        return this.postId == otherPost.postId && this.titleOfPost.equals(otherPost.titleOfPost) &&
                this.contentofPost.equals(otherPost.contentofPost) &&
                this.user.equals(otherPost.user);
    }

}