/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import CONNECTION.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Entity.Users;
import Entity.CurrentUser;
import Services.Service_User;
import Services.ServiceCurrentUser;
import Services.UserSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem ben arfa
 */
public class ProfileController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField datenais;
    @FXML
    private TextField numtel;
    @FXML
    private TextField adress;
    @FXML
    private TextField role;
    Connection cnx;
    private DataSource conn;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;

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
                
                email.setText(rs1.getString(2));
                 
                username.setText(rs1.getString(4));
                numtel.setText(rs1.getString(5));  
                datenais.setText(rs1.getString(6));
                adress.setText(rs1.getString(7));
                role.setText(rs1.getString(8));
           
            }
        
            
            } catch (SQLException ex) {
            Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/Modifierprofile.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/EspaceClient.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }
    
}
