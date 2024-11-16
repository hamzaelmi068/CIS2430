package Labs.Lab4.lab4CIS2430;

public class Post {
    private String titleOfPost;
    private String contentOfPost;
    private static int postCounter = 0; // for generating post ids
    private int postId;
    private User user;

    // constructor
    public Post(String titleOfPost, String contentOfPost, User user) throws Exception {
        // validating title
        if (titleOfPost == null || titleOfPost.trim().isEmpty()) {
            throw new Exception("title of the post cant be empty");
        }
        // validating content of post
        if (contentOfPost == null || contentOfPost.trim().isEmpty()) {
            throw new Exception("content cant be empty....");
        }
        // Validate user
        if (user == null) {
            throw new Exception("Post must have a valid user");
        }

        this.titleOfPost = titleOfPost.trim();
        this.contentOfPost = contentOfPost.trim();
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
        return contentOfPost;
    }

    public User getUser() {
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
        this.contentOfPost = contentOfPost.trim();
    }

    @Override
    public String toString() {
        return ("Created By:" + user.getFullName() + "(@<" + user.getUserName() + ">)" + "\ntitleOfPost: " + titleOfPost
                + "\n" + contentOfPost);
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
        Post otherPost = (Post) obj;
        return this.postId == otherPost.postId && this.titleOfPost.equals(otherPost.titleOfPost) &&
                this.contentOfPost.equals(otherPost.contentOfPost) &&
                this.user.equals(otherPost.user);
    }

}