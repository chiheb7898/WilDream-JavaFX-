/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONNECTION.DataSource;
import Entity.Users;
import Services.UserSession;
import static Services.Service_User.currentUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import Services.Service_User;
import Services.UserSession;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.CheckBox;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;


import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.CurrentUser;
import Services.JavaMailUtil;
 



/**
 * FXML Controller class
 *
 * @author eyana
 */
public class LoginController implements Initializable {
            

    private int minute;
    private int hour;
    private int second;
    private String am_pm;
    UserSession US;
    private boolean Timer;
    private Integer tseconds;
    private Calendar cal;
//    private DataBaseConnection dc;
    private PreparedStatement pst = null;
    private DataSource conn;
    private String sql;
    ResultSet rs;
     PreparedStatement ps;
    ResultSet res;
    @FXML
    private Label lblErrorMail;
    
    @FXML
    private Label lblTime;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton inscriptionbtn;
    @FXML
    private JFXButton loginbtn;
    @FXML
    private CheckBox cbpassword1;

    /**
     * Initializes the controller class.
     *  @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
        
        conn = DataSource.getInstance();


        try {
            pst = conn.getCnx().prepareStatement("select email from users");
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

       



        
    }
    
    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.millis(Calendar.getInstance().get(Calendar.MILLISECOND)), (ActionEvent event) -> {
            cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            am_pm = (cal.get(Calendar.AM_PM) == 0) ? "AM" : "PM";
            lblTime.setText(String.format("   %02d : %02d : %02d %s", hour, minute, second, am_pm));
            if (Timer) {
                if (tseconds == 0) {
                    Timer = false;
                    //timer.setText("Time Out");
                } else {
                    //timer.setText(tseconds.toString());
                    tseconds--;
                }
            }
        }), new KeyFrame(Duration.millis(Calendar.getInstance().get(Calendar.MILLISECOND))));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
   
     private boolean isEmailValid(String email) {
        String pattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        return email.matches(pattern);
    }


     @FXML
    private void Login(ActionEvent event) throws SQLException, IOException, Exception {
       
        
        
        UserSession daoU = new UserSession();
        Users result = daoU.login(email.getText());
        JavaMailUtil mail = new JavaMailUtil();
           String emailu=result.getEmail();
           JavaMailUtil.sendMail(emailu);
              
            System.out.println("verified ");
            try {
                conn = DataSource.getInstance();
                String sql1 = "insert into currentuser (iduser,username,email,password,roles,adresse,tel,image)values(?,?,?,?,?,?,?,?)";
                pst = conn.getCnx().prepareStatement(sql1);
                
                pst.setInt(1,result.getIdu());
                
                pst.setString(2,result.getUsername() );
           
                pst.setString(3, result.getEmail());

                pst.setString(4, result.getPassword());
                
                pst.setString(5,result.getRoles() );
               
           
                pst.setString(6, result.getAdresse());

                pst.setString(7, result.getTel());
                pst.setString(8, result.getImage());
                
                

                pst.executeUpdate();
                System.out.println("current user added");
                
                
            
            } catch (SQLException ex) {
                System.out.println(ex);

            
            }
            

        if (password.getText().equals( result.getPassword()))
        {    
            
            
            switch (result.getRoles()) {
                case "Admin":
                    {   
                        currentUser.getEmail();
                        System.out.println(result.getRoles());
                        // TODO: Proceed to other page
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/GUI/EspaceAdmin.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("Espace Admin");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                case "Client":
                    {
                        currentUser.getEmail();
                        // TODO: Proceed to other page
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/GUI/EspaceClient.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("Espace Client");
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                
                default:
                    break;
            }
                
        
            
            
            System.out.println(currentUser.getEmail());
        }
        
   
           else
       {
           Alert alert = new Alert(Alert.AlertType.ERROR, "ERREUR!!", ButtonType.OK);
           alert.showAndWait();
       }
           }
    
@FXML
    private void redirecttoregister(ActionEvent event) {
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
    }
    
                 

    
       
     

   
   
    

