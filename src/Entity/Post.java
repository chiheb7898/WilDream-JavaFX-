package Entity;
import java.util.*;

public class Post {
    private int id;
    private String title;
    private String body;
    private Date time;
    private String slug;
    private String file;
    private int user_id;
    private  String type;
    private int likes;
    private String Username;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
    }

    public Post(int id, String title, String body, Date time, String slug, String file, int user_id,String type,int likes) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
        this.slug = slug;
        this.file = file;
        this.user_id = user_id;
        this.type=type;
        this.likes=likes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", body=" + body + ", time=" + time + ", slug=" + slug + ", image=" + file + ", type=" + type + "username= "+Username+'}';
    }
}
