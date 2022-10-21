/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONNECTION.DataSource;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Entity.CurrentUser;
import Entity.Users;
import Services.PostServices;
import java.io.File;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author eyana
 */
public class EspaceClientController implements Initializable {

    @FXML
    private Button btncompte;
    @FXML
    private Button btnmesevenements;
    @FXML
    private Button btnlogout;
    private PreparedStatement pst;
     private ResultSet rs;
    private DataSource conn;
    @FXML
    private Button btnreservation;
    @FXML
    private Button btnoffre;
    private Pane mainpane;
    @FXML
    private BorderPane borderpane;
    @FXML
    private VBox vbox1;
    @FXML
    private Pane mainpane1;
    @FXML
    private ImageView logo;
    @FXML
    private Button homepage;
    @FXML
    private MediaView videomain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            /*Media media= new Media("@Assets/london.mp4");
            MediaPlayer mediaplayer=new MediaPlayer(media);
            mediaplayer.setAutoPlay(true);
            mediaplayer.setVolume(0);
            mediaplayer.setCycleCount(mediaplayer.INDEFINITE);
            videomain.setMediaPlayer(mediaplayer);
            videomain.setFitHeight(760);
            videomain.setFitWidth(1280);*/
    }    


    private void modifiermoncv(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/ModifierCv.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }



    @FXML
    private void mesevenements(ActionEvent event) {
    }

    


    @FXML
    private void Logout(ActionEvent event) throws SQLException {
        conn = DataSource.getInstance();
          conn = DataSource.getInstance();
           String query1 = "TRUNCATE TABLE currentuser ";
           pst = conn.getCnx().prepareStatement(query1);
           pst.executeUpdate();
           System.out.println("CurrentUser table is clear ");
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }

    @FXML
    private void mesreservation(ActionEvent event) {
    }

    @FXML
    private void listposts(ActionEvent event) throws IOException, SQLException {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
            Parent root=loader.load();
            FeedController pdc=loader.getController();
            pdc.afficher();
            FXMLLoader loader1=new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root1=loader1.load();
            DashboardController dbc=loader1.getController();
            dbc.setCenter(root);
        
            mainpane1.getScene().setRoot(root1);
    }

    @FXML
    private void homepage(ActionEvent event) {
    }

    @FXML
    private void profile(ActionEvent event) {
        
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
