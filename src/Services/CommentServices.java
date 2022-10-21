/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Comment;
import Entity.Post;
import CONNECTION.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author chihe
 */
public class CommentServices {
    Connection cnx;
    PreparedStatement ste;

    public CommentServices() {
        cnx= DataSource.getInstance().getCnx();
    }
    
    public void ajouterComment(Comment c){
       
        try {
            String sql= "insert into comment(body,created,user_id,post_id)"+"values (?,?,?,?)";
            ste=cnx.prepareStatement(sql);
            ste.setString(1, c.getBody());
            ste.setDate(2, new java.sql.Date(c.getTime().getTime()));
            ste.setInt(3,c.getId_user());
            ste.setInt(4, c.getId_post());
            ste.executeUpdate();
            System.out.println("Comment Ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Comment> afficherComments(int id){
        try {
            List<Comment> comments=new ArrayList<>();
            String sql="select * from comment where post_id="+id;
            ste = cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while (rs.next()){
                Comment c= new Comment();
                c.setId(rs.getInt(1));
                c.setBody(rs.getString(2));
                c.setTime(rs.getDate(3));
                c.setId_user(rs.getInt(4));
                c.setId_post(rs.getInt(5));
                comments.add(c);               
            }
            
            return comments;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return null;
    }
    public void deleteComment(int id){
        try {
            String sql1= "delete from comment where id='"+id+"'";
            ste=cnx.prepareStatement(sql1);
            ste.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("comment Deleted");
        
    }
}
