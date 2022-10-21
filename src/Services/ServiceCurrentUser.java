/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Users;
import Entity.CurrentUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eyana
 */
public class ServiceCurrentUser {
    private String sql;
    

   
    Connection cnx;
    private DataSource conn;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;

    public CurrentUser infos (){
        CurrentUser user = new CurrentUser();

        try {
            

            conn = DataSource.getInstance();
            String requete = "select * from currentuser where id=1";
            pst = conn.getCnx().prepareStatement(requete);
            ResultSet rs1 = pst.executeQuery(requete);
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
                user.setIduser(rs1.getInt(10));
           
            }
            
            System.out.println(count);
            if(count == 0){
                 CurrentUser a = new CurrentUser();
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
    
}