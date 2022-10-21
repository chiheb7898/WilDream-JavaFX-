/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import CONNECTION.DataSource;
import Entity.Users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.BoxBlur;

import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
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

/**
 * FXML Controller class
 *
 * @author eyana
 */
public class AjouterUserController implements Initializable {
     private DataSource conn;
    private PreparedStatement pst = null;
    // private Connection conn;
    private String sql;
    ResultSet rs;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField tfidU;
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
    private JFXButton btnaddaccount;
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
    private JFXRadioButton recruteur;
    @FXML
    private JFXButton btnlisteutilisateurs;
    @FXML
    private JFXButton btnListeClients;
    @FXML
    private JFXButton btnlistereclamations;
    @FXML
    private JFXRadioButton admin;
    @FXML
    private JFXButton btnListeRecruteurs;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance();
        password.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!"".equals(newValue)) {
                updatePasswordStrength(newValue);
            } else {
                passwordStrength.setText("");
            }
        });
        filename.setEditable(false);
        datenaissance.setEditable(false);

        try {
            autoOrderNOP();

            //  tfidU.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tfidU.setText("" + autoOrderNOP());
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }


  
    }   

    @FXML
    private void ChooseProfilePicture(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String imagepath = file.toURI().toURL().toString();
            System.out.println("file:" + imagepath);
            Image image = new Image(imagepath);
            System.out.println("height:" + image.getHeight() + "\nWidth:" + image.getWidth());
            profilepicture.setImage(image);
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
          
            alert.showAndWait();
        }

    }
    
    private static final String[] VALID_EXTENSIONS = new String[]{".png", ".jpg", "jpeg", "gif", "bmp"};
    final String newStyle = "-fx-background-radius: 50em;\n"
            + "    -fx-min-width: 50px;\n"
            + "    -fx-min-height: 50px; \n"
            + "    -fx-max-width: 50px;\n"
            + "    -fx-max-height: 50px;\n"
            + "    -fx-background-color: #6fb52c;\n"
            + "    -fx-text-fill: #ffffff;\n"
            + "    -fx-border-color: #ffffff;\n"
            + "    -fx-border-width: 2px; \n"
            + "    -fx-background-insets:0;"
            + "    -fx-border-radius: 50em;";
    
      private boolean isPasswordValid(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    private boolean isEmailValid(String email) {
        String pattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        return email.matches(pattern);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        String pattern = "^[0-9]{8}$";
        return phoneNumber.matches(pattern);
    }

    private boolean isFormFilled() {
        if (tfidU.getText().equals("") || name.getText().equals("") || password.getText().equals("")
                || email.getText().equals("") || numtel.getText().equals("") || datenaissance.getValue() == null
                || adress.getText().equals("") || filename.getText().equals("")) {
            return false;
        }
        return true;
    }
//mdp strong weak average
    private int calculatePasswordStrength(String password) {

        //total score of password
        int iPasswordScore = 0;

        if (password.length() < 8) {
            return 0;
        } else if (password.length() >= 10) {
            iPasswordScore += 2;
        } else {
            iPasswordScore += 1;
        }

        //if it contains one digit, add 2 to total score
        if (password.matches("(?=.*[0-9]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one lower case letter, add 2 to total score
        if (password.matches("(?=.*[a-z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one upper case letter, add 2 to total score
        if (password.matches("(?=.*[A-Z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one special character, add 2 to total score
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            iPasswordScore += 2;
        }

        return iPasswordScore;

    }
//verif si formulaire est rempli sinon si vide msg d'erreur
    private boolean isFormValid() {
        String errorLog = "";
        boolean flag = true;
        if (!isFormFilled()) {
            errorLog += "* You must fill all required fields ( * ) !\n";
            flag = false;
        } else {
            if (!isPasswordValid(password.getText())) {
                errorLog += "* Invalid password !\n";
                flag = false;
            }
            if (!isEmailValid(email.getText())) {
                errorLog += "* Invalid email !\n";
                flag = false;
            }
            if (!isPhoneNumberValid(numtel.getText())) {
                errorLog += "* Invalid phone number !\n";
                flag = false;
            }
        }
     
        return flag;
    }

    private void updatePasswordStrength(String value) {
        if (calculatePasswordStrength(value) < 5) {
            passwordStrength.setText("( weak )");
            passwordStrength.setTextFill(Color.web("#ff0505"));
        } else if (calculatePasswordStrength(value) == 5) {
            passwordStrength.setText("( average )");
            passwordStrength.setTextFill(Color.web("#ed701b"));
        } else if (calculatePasswordStrength(value) >= 8) {
            passwordStrength.setText("( strong )");
            passwordStrength.setTextFill(Color.web("#6fb52c"));
        }
    }

    public boolean validateNomP() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(name.getText());
        if (m.find() && m.group().equals(name.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateEmaillP() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if (m.find() && m.group().equals(email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }
     public boolean validatePasswordP() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (password.getText().matches(pattern)) {

            if (password.getText().equals(password.getText())) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Check your password confirmation");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }
    }
//ctrl de saisie dateNaissance(champ pas vide)
    public boolean validateDateP() {
        if (datenaissance.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;
        }

        Date date = java.sql.Date.valueOf(datenaissance.getValue());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        Calendar c = Calendar.getInstance();
        if (c.getTime().after(date) == true && c.getTime().equals(date) == false) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;

        }
    }
//ctrl de saisie mdp
    public boolean validatePasswordC() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (password.getText().matches(pattern) && password.getText().equals(password.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }

    }



    @FXML
    private void addaccount(ActionEvent event)throws SQLException, FileNotFoundException, MalformedURLException, IOException, MessagingException {
       
       
        //  if(isFormFilled()){
        if (isFormFilled() == true && validateEmaillP() || validateDateP() == true || isPhoneNumberValid(newStyle) || validateNomP() || validatePasswordC()) {
            Integer.valueOf(tfidU.getText());
            String nom = name.getText();
            String mail = email.getText();
            String pswd = password.getText();
            String img = ImagePath.getText();
            
            

        
            Integer.valueOf(numtel.getText());

            // String birthDate = this.birthDate.getText();
            String address = adress.getText();
            Date.valueOf(datenaissance.getValue());

            try {
                conn = DataSource.getInstance();
                String sql1 = "insert into users (id,username,email,password,roles,birthDate,adresse,tel,image)values(?,?,?,?,?,?,?,?,?)";
                pst = conn.getCnx().prepareStatement(sql1);
                pst.setInt(1, Integer.valueOf(tfidU.getText()));
                pst.setString(2, nom);
           
                pst.setString(3, mail);

                pst.setString(4, pswd);
                
                pst.setInt(8, Integer.valueOf(numtel.getText()));
                
                pst.setDate(6, Date.valueOf(datenaissance.getValue()));
                
                pst.setString(7, address);
                
                pst.setString(9, img);
                
             
                   if (client.isSelected()) {
                    pst.setString(5, "Client");
                } else {
                    pst.setString(5, "Admin");
                }

                //}
                //pst.setString(10,String.valueOf(role.getSelectionModel().getSelectedItem()) );
                //if ( role.getSelectionModel().getSelectedIndex() == 0) {
                //    pst.setString(5, "Client");
                //} else if (role.getSelectionModel().getSelectedIndex() == 1) {
                //    pst.setString(5, "Recruteur");
                // } else if (role.getSelectionModel().getSelectedIndex() == 2) {
                //    pst.setString(5, "Formateur");
                //} else {
                //    pst.setString(5, "Admin");

                //}

                pst.executeUpdate();
                System.out.println("Personne Ajoutée");
                    

                
            } catch (SQLException ex) {
                System.out.println(ex);

            } finally {
                pst.close();
                BackAutomaticly(event);
                //}
            }

        }
    
    
    }
     private void BackAutomaticly(ActionEvent event) throws IOException {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/EspaceAdminListeUtilisateurs.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }
         
          

     private void clearFields() {
        tfidU.setText(null);
        name.setText(null);
        email.setText(null);
        password.setText(null);
        datenaissance.setValue(null);
        adress.setText(null);
        numtel.setText(null);

    }
//dernier user inscrit
    private int autoOrderNOP() throws SQLException {
        int nop = 0;
        try {
            conn = DataSource.getInstance();

            String sql2 = "select max(id) from users";
            pst = conn.getCnx().prepareStatement(sql2);
            rs = pst.executeQuery();
            if (rs.next()) {
                nop = rs.getInt(1);

            }

            nop++;
            pst.close();
            rs.close();

        } catch (SQLException e) {
        }
        return nop;

    }


    @FXML
    private void RedirecttoListeUtilisateursPage(ActionEvent event) {
    }

    @FXML
    private void RedirecttoListeClientsPage(ActionEvent event) {
    }




    @FXML
    private void RedirecttoListeReservationsPage(ActionEvent event) {
    }

    @FXML
    private void RedirecttoListeRecruteursPage(ActionEvent event) {
    }
    
}
