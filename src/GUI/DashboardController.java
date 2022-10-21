/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PostServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author chihe
 */
public class DashboardController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Pane mainpane;
    @FXML
    private ImageView logo;
    @FXML
    private Button addbutton;
    @FXML
    private Button showbutton;
    @FXML
    private Button statsbutton;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Button homePage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadUI("feed");
    } 
    private void loadUI(String ui){
        Parent root = null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui+".FXML"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }

    @FXML
    private void addinterface(ActionEvent event) {
        loadUI("AjoutPost");
        /*addbutton.setStyle("-fx-background-color: #198754;");
        showbutton.setStyle("-fx-background-color: #1b1b1b;");
        statsbutton.setStyle("-fx-background-color: #1b1b1b;");*/
    }

    @FXML
    private void showinterface(ActionEvent event) {
        loadUI("feed");
        /*showbutton.setStyle("-fx-background-color: #198754;");
        statsbutton.setStyle("-fx-background-color: #1b1b1b;");
        addbutton.setStyle("-fx-background-color: #1b1b1b;");*/
    }

    @FXML
    private void statsinterface(ActionEvent event) { 
        loadUI("PostStats");
        /*statsbutton.setStyle("-fx-background-color: #198754;");
        showbutton.setStyle("-fx-background-color: #1b1b1b;");
        addbutton.setStyle("-fx-background-color: #1b1b1b;");*/
    }
    
    public void setCenter(Parent Root)
    {
        borderpane.setCenter(Root);
    }

    @FXML
    private void backhome(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("EspaceClient.fxml"));
        Parent root=loader.load();
        EspaceClientController ecc=loader.getController();
        
        vbox.getScene().setRoot(root);
    }
    
}
