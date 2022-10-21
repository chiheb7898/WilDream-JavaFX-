/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONNECTION ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class DataSource {
       public Connection cnx;
        String url="jdbc:mysql://localhost:3306/pidev";
        String login="root";
        String pwd="";
        static DataSource ds;

    public DataSource() {
            try {
                cnx= DriverManager.getConnection(url,login, pwd);
                System.out.println("Connection avec succes");
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public static DataSource getInstance(){
        if(ds==null)
        {
            ds= new DataSource();
        }
        
        return ds;
    }

    public Connection getCnx() {
        return cnx;
    }

    public Connection getConnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
