/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.CurrentUser;
import Entity.Post;
import Services.PostServices;
import Services.ServiceCurrentUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author chihe
 */
public class AjoutPostController implements Initializable {

    @FXML
    private TextArea postbody;
    @FXML
    private Button btnpublier;
    @FXML
    private TextField posttitle;
    @FXML
    private Label bodylable;
    @FXML
    private Label titlelable;
    @FXML
    private ImageView image2;
    @FXML
    private Label labelchoose;
    
    
    private File file;
    private Image image;
    private FileInputStream fis;
    private String filepath;
    private String type;
    
    @FXML
    private Button load;
    
    public static String s;
    public static String s1;
    @FXML
    private VBox vbox1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPost(ActionEvent event) throws IOException, SQLException {
        //try {
            String title= posttitle.getText();
            String body= postbody.getText();
            Date date = new Date();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            //fis= new FileInputStream(file);
            ServiceCurrentUser scu=new ServiceCurrentUser();
            CurrentUser cu=scu.infos();
            Post P= new Post(33,title,body,date,"Slug",filepath,cu.getIduser(),type,0);
            PostServices ps= new PostServices();
            ps.ajouterPost(P);
            FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
            Parent root=loader.load();
            FeedController pdc=loader.getController();
            pdc.afficher();
            FXMLLoader loader1=new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root1=loader1.load();
            DashboardController dbc=loader1.getController();
            dbc.setCenter(root);
        
            load.getScene().setRoot(root1);
        
    }        

    @FXML
    private void loadpath(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi"));
                //new FileChooser.ExtensionFilter("All Files", "*.*"));
            Window mainStage = WildreamFXMain.getPrimaryStage();
            file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            //mainStage.display(selectedFile);
            labelchoose.setText(file.getAbsolutePath());
            image= new Image(file.toURI().toString(),500,500,true,true); // path, prefWidth, preHeight, PreserveRatio, smooth
            //image2.setImage(image);
            File newDes = new File("C:\\wamp64\\www\\wildream\\public\\images" + file.getName());
            String extention= newDes.toString().substring(newDes.toString().lastIndexOf('.')+1);
            System.out.println(extention);
            if ((extention.equals("png"))||(extention.equals("jpg"))||(extention.equals("gif")))
            {
                type="image";
            }
            else if ((extention.equals("mp4"))||(extention.equals("avi")))
            {
                type="video";
            }
            else 
                type="article";
            try {
                /*ProgressIndicator pi=new ProgressIndicator();
                vbox1.getChildren().add(pi);
                pi.progressProperty().bind();*/
                copyContent(file,newDes);
                this.filepath=newDes.getAbsolutePath();
            } catch (Exception ex) {
                System.out.println("null");
            }
             //Path local = Paths.get("C:\\xampp\\php\\www\\ArtBox-CrashTest\\src\\ArtHub\\images\\Postes\\" +   file.getName());
        }
    }
    
    public void copyContent(File a, File b)
        throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);
        try {
            
  
            int n;
  
            
            while ((n = in.read()) != -1) {
                
                out.write(n);
            }
        }
        finally {
            if (in != null) {
  
               
                in.close();
            }
            
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }
}


