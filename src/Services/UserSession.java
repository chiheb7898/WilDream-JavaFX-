/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Maryem
 */
public class UserSession {
    
    private String sql;
    

    private LocalDate last_login;
    public static Users currentUser = new Users();
    Connection cnx;
    private DataSource conn;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;

    public Users login(String email){
        Users user = new Users();

        try {
            String requete = "select * from Users where email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs1 = st.executeQuery(requete);
            int count = 0;
            while(rs1.next()){
                count ++;
                user.setId(rs1.getInt(1));
                user.setUsername(rs1.getString(4));
                user.setPassword(rs1.getString(3));
                user.setEmail(rs1.getString(2));
             
                user.setRoles(rs1.getString(8));
                user.setTel(rs1.getString(5));
                user.setAdresse(rs1.getString(7));
                user.setImage(rs1.getString(9));
                user.setIdu(rs1.getInt(10));
                currentUser.setId(rs1.getInt(1));
                currentUser.setUsername(rs1.getString(4));
                currentUser.setPassword(rs1.getString(3));
                currentUser.setEmail(rs1.getString(2));
             
                currentUser.setRoles(rs1.getString(8));
                currentUser.setTel(rs1.getString(5));
                currentUser.setAdresse(rs1.getString(7));
                currentUser.setImage(rs1.getString(9));
                currentUser.setIdu(rs1.getInt(10));
            
            }
            
            System.out.println(count);
            if(count == 0){
                 Users a = new Users();
                 a.setPassword("");
                return a;
            }else{
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public UserSession() {
        this.cnx = DataSource.getInstance().getCnx();
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDate last_login) {
        this.last_login = last_login;
    }

    public UserSession UserSessionLogOut() {
        currentUser.setUsername("");
        currentUser.setEmail("");
        currentUser.setId(0);
        return null;
    }
//verifier login lors de la connexion
    public static boolean isLogedIn(Users user) {
        if ((user.getEmail() == "" && user.getUsername() == "" && user.getRoles() == "") || (user.getBirthDate() == null  && user.getAdresse() == null)) {
            return false;
        } else {
            return true;
        }
    }
  

    public boolean isValidUser(String Email) {
        String req = "SELECT * FROM `users` WHERE email =\'" + Email + "\'";
        try {
            rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.toString());
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
    
    public void update(Users e,int id) {
       try {
     
            System.out.println(e.toString());
            System.out.println(id);            
            String requete3 = "UPDATE User SET username=?,email=?,numtel=? WHERE id=?";
            PreparedStatement pst2 = DataSource.getInstance().cnx.prepareStatement(requete3);
           
                
                pst.setString(1, e.getUsername());
           
                pst.setString(2, e.getEmail());
               
                
                pst.setString(3, e.getTel());
                
                
                pst.setInt(4, id);
     
            pst2.executeUpdate();
            System.out.println("Transport updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }
            
  
}