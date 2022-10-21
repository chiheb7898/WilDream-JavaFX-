/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONNECTION.DataSource;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import Entity.Users;
import Entity.CurrentUser;
import Services.ServiceCurrentUser;
import Services.UserSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem ben arfa
 */
public class ModifierprofileController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField adress;
    @FXML
    private JFXTextField numtel;
    @FXML
    private DatePicker datenais;
    Connection cnx;
    private DataSource conn;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;
    @FXML
    private Label emailmodifie;
    @FXML
    private Label passwordmodifie;
    @FXML
    private Label usernamemodifie;
    @FXML
    private Label telephonemodifie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCurrentUser daoU = new ServiceCurrentUser();
        CurrentUser result = daoU.infos();
         try {
        
            conn = DataSource.getInstance();
            String requete = "select * from users where id="+result.getIduser()+"";
            pst = conn.getCnx().prepareStatement(requete);
            ResultSet rs1 = pst.executeQuery(requete);
            
            while(rs1.next()){
                
                username.setText(rs1.getString(4));
                email.setText(rs1.getString(2));
                numtel.setText(rs1.getString(5));
                adress.setText(rs1.getString(7));
                
               
           
            }
        
            
            } catch (SQLException ex) {
            Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        ServiceCurrentUser daoU = new ServiceCurrentUser();
        CurrentUser result = daoU.infos();
        try {
        String nom = username.getText();
        String mail = email.getText();
        String tel = numtel.getText();
        String add = adress.getText();
        LocalDate date = datenais.getValue();
        
        String req ="UPDATE users  SET username='"+nom+"',email='"+mail+"',tel='"+tel+"',adresse='"+add+"',birthDate='"+date+"' where id="+result.getIduser()+" ";
    
             pst = conn.getCnx().prepareStatement(req);
             pst.executeUpdate(req);
            System.out.println("profile modified");
            
        } catch (SQLException ex) {
            System.out.println("Problem");
            System.out.println(ex.getMessage());
            
        }
    System.out.println("now redirecting to your profile page");
    try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/Profile.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }
    
}
