/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Services.UserSession;
import GUI.EspaceAdminListeUtilisateursController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyana
 */
public class ModifierlistutilisateursController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField adress;
    @FXML
    private JFXTextField numtel;
    @FXML
    private DatePicker datenaissance;
    @FXML
    private ImageView profilepicture;
    @FXML
    private JFXButton btnmodifercompte;
    @FXML
    private TextField filename;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label ImagePath;
    @FXML
    private Label passwordStrength;
    @FXML
    private JFXRadioButton client;
    @FXML
    private JFXRadioButton formateur;
    @FXML
    private TextField tfidU;
    @FXML
    private JFXButton btnlisteutilisateurs;
    @FXML
    private JFXButton btnListeClients;
    @FXML
    private JFXButton btnlistereclamations;
    @FXML
    private JFXButton btnListeRecruteurs;
    @FXML
    private JFXButton btnListeFormateurs;
    UserSession cr  =  new UserSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChooseProfilePicture(ActionEvent event) {
    }

    @FXML
    private void modifiercompte(ActionEvent event) {
        
        // if (table_user.getSelectionModel().getSelectedItem() != null) {
        //    cr.update(new Entity.User(name.getText(), email.getText(),numtel.getText()), table_user.getSelectionModel().getSelectedItem().getId());
           
            
        
    

    }

    @FXML
    private void RedirecttoListeUtilisateursPage(ActionEvent event) {
         try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/SignUp.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
   
    }

    @FXML
    private void RedirecttoListeClientsPage(ActionEvent event) {
    }

    @FXML
    private void RedirecttoListeReclamationsPage(ActionEvent event) {
    }

    @FXML
    private void RedirecttoListeRecruteursPage(ActionEvent event) {
    }

    @FXML
    private void RedirecttoListeFormateursPage(ActionEvent event) {
    }
    
}
