
package Services;

import Entity.Post;
import CONNECTION.DataSource;
import Entity.Users;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostServices {
    Connection cnx;
    PreparedStatement ste;
    PreparedStatement ste1;
    Service_User su;

    public PostServices() {
        cnx= DataSource.getInstance().getCnx();
    }
    
    public void ajouterPost(Post p){
       
        try {
            String sql= "insert into post(title,body,time,slug,image,type,likes,user_id)"+"values (?,?,?,?,?,?,?,?)";
            ste=cnx.prepareStatement(sql);
            ste.setString(1, p.getTitle());
            ste.setString(2, p.getBody());
            ste.setDate(3, new java.sql.Date(p.getTime().getTime()));
            ste.setString(4, p.getSlug());
            ste.setString(5, p.getFile());
            ste.setString(6, p.getType());
            ste.setInt(8, p.getUser_id());
            ste.setInt(7,p.getLikes());
            ste.executeUpdate();
            System.out.println("Post Ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Post> afficherPosts(){
        try {
            List<Post> posts=new ArrayList<>();
            String sql="select * from post";
            ste = cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while (rs.next()){
                Post p= new Post();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setBody(rs.getString(3));
                p.setTime(rs.getDate(4));
                p.setSlug(rs.getString(5));
                p.setFile(rs.getString(6));
                p.setType(rs.getString(7));
                p.setUser_id(rs.getInt(9));
                p.setLikes(rs.getInt(8));
                CommentServices cs=new CommentServices();
                p.setComments(cs.afficherComments(p.getId()));
                posts.add(p);               
            }
            
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return null;
    }
    
    public List<Post> serarchPosts(String ch){
        try {
            List<Post> posts=new ArrayList<>();
            String sql="select * from post where body like '%"+ch+"%'";
            ste = cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while (rs.next()){
                Post p= new Post();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setBody(rs.getString(3));
                p.setTime(rs.getDate(4));
                p.setSlug(rs.getString(5));
                p.setFile(rs.getString(6));
                p.setType(rs.getString(7));
                p.setUser_id(rs.getInt(9));
                p.setLikes(rs.getInt(8));
                CommentServices cs=new CommentServices();
                p.setComments(cs.afficherComments(p.getId()));
                posts.add(p);   
            }
            
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return null;
    }
    public void deletePost(int id){
        try {
            String sql1= "delete from comment where post_id='"+id+"'";
            ste=cnx.prepareStatement(sql1);
            ste.executeUpdate();
            
            String sql= "delete from post where id='"+id+"'";
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void addlike(int id,int likes) throws SQLException
    {
        String sql1= "update post set likes='"+likes+"' where id='"+id+"'";
        ste=cnx.prepareStatement(sql1);
        ste.executeUpdate();
        System.out.println("like");
    }
    public List<Post> serarchPostsbyID(int ch){
        try {
            List<Post> posts=new ArrayList<>();
            String sql="select * from post where id='"+ch+"'";
            ste = cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while (rs.next()){
                Post p= new Post();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setBody(rs.getString(3));
                p.setTime(rs.getDate(4));
                p.setSlug(rs.getString(5));
                p.setFile(rs.getString(6));
                p.setType(rs.getString(7));
                p.setUser_id(rs.getInt(9));
                p.setLikes(rs.getInt(8));
                CommentServices cs=new CommentServices();
                p.setComments(cs.afficherComments(p.getId()));
                posts.add(p);               
            }
            
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return null;
    }
    
}
