/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Post;


import Entity.Users;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.util.Duration;




/**
 *
 * @author Maryem
 */
public class Service_User {
    
    private PreparedStatement pst = null;
    private PreparedStatement pst2 = null;
    private final DataSource conn;
    private String sql;
    ResultSet rs;
      public static Users currentUser = new Users();
    public Service_User() throws SQLException{
        conn = DataSource.getInstance();
    }
    

    
    public int SingIn(Users si1) {
               try {
            pst = conn.getCnx().prepareStatement("SELECT * FROM users WHERE  email = ? and password = ? and roles=? ");
            pst.setString(1, si1.getEmail());
            pst.setString(2, si1.getPassword());
           pst.setInt(3, 2);
            rs = pst.executeQuery();
     if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getString(2));
                 
                    currentUser.setId(1);
                    currentUser.setUsername(rs.getString(2));
                currentUser.setEmail(rs.getString(4));
                currentUser.setRoles(rs.getString(12));
                
                  
                 
                   
                    return 1;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_User.class.getName()).log(Level.SEVERE, null, ex);
        }
      return 0;
    }

    
    public int VerifierCompte(Users verif, String code) {
          try {
            String requete = "SELECT * FROM users Where password =? and roles= ? and email = ?  ";
            pst = conn.getCnx().prepareStatement(requete);
            pst.setString(1, verif.getPassword());
            pst.setInt(2, 2);
            pst.setString(3, verif.getEmail());
          

            rs = pst.executeQuery();

            boolean v = rs.next();

            if (v == true) {
                if ((rs.getString(10).equals(code)) == false) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Account not verified "
                    + "\n code is not correct");
            alert.showAndWait();
                    return 0;
                } else {
                    int idU = rs.getInt("id");
                    System.out.println(idU);
                    String req = "UPDATE users set enabled=? where id=? ";
                    try {
                        pst2 = conn.getCnx().prepareStatement(req);
                        pst2.setInt(1, 1);
                        pst2.setInt(2, idU);
                        pst2.executeUpdate();
                          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Account has been verified");
            alert.showAndWait();
                        return 1;
                    } catch (SQLException ex) {
                        Logger.getLogger(Service_User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_User.class.getName()).log(Level.SEVERE, null, ex);
        }
          return 0;
    }

   

   
    public String getMd5(String input) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    
    }

    /**
     *
     * @return
     */
   
   
      public static Users getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Users currentUser) {
        Service_User.currentUser = currentUser;
    }
    public String getUserName(int id)
    {
        try {
            List<Users> users=new ArrayList<>();
            String sql="select * from users where id='"+id+"'";
            pst = conn.getCnx().prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                Users u= new Users();
                u.setId(rs.getInt(1));
                u.setEmail(rs.getString(2));
                u.setUsername(rs.getString(4));
                users.add(u);
                return u.getUsername();
            }
            //return users;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return null;
        
    }
    
}
