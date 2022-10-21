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
import Services.Service_User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chihe
 */
public class AfficherPostController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Text body;
    @FXML
    private Text title;
    @FXML
    private AnchorPane main;
    @FXML
    private Text date;
    
    int id;
    Post p;
    
    Image image;
    @FXML
    private FontAwesomeIconView deletebtn;
    @FXML
    private VBox vbox;
    @FXML
    private Label nbcmnts;
    @FXML
    private Label nblikes;
    @FXML
    private Button vp;
    @FXML
    private Button likebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setAffichage(Post p) throws IOException, SQLException {
        Service_User su=new Service_User();
        p.setUsername(su.getUserName(p.getUser_id()));
        this.username.setText(p.getUsername());
        this.body.setText(p.getBody());
        this.title.setText(p.getTitle());
        this.date.setText(p.getTime().toString());
        this.id=p.getId();
        String is=p.getFile();
        this.nblikes.setText(String.valueOf(p.getLikes()));
        nbcmnts.setText(String.valueOf(p.getComments().size()));
        //ImageView imageView= new ImageView(image);
        
        if(p.getType().equals("image"))
        {
        image=new Image("file:"+is,1080,1080,true,true);
        ImageView imageview=new ImageView();
        imageview.setFitHeight(240);
        imageview.setFitWidth(360);
        imageview.setImage(image);
        imageview.setVisible(true);
        vbox.getChildren().add(4,imageview);
        }
        else if (p.getType().equals("video"))
        {
            Media media= new Media(new File(is).toURI().toString());
            MediaPlayer mediaplayer=new MediaPlayer(media);
            mediaplayer.setAutoPlay(true);
            mediaplayer.setVolume(0);
            mediaplayer.setCycleCount(mediaplayer.INDEFINITE);
            MediaView mediaview= new MediaView(mediaplayer);
            mediaview.setFitHeight(240);
            mediaview.setFitWidth(360);
            vbox.getChildren().add(4,mediaview);
        }
        this.p=p;
    }

    @FXML
    private void deletepost(ActionEvent event) throws IOException, SQLException {
        ServiceCurrentUser scu=new ServiceCurrentUser();
        CurrentUser cu=scu.infos();
        if(cu.getIduser()==this.p.getUser_id())
        {
            PostServices ps=new PostServices();
            ps.deletePost(id);
            FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
            Parent root=loader.load();
            FeedController pdc=loader.getController();
            pdc.afficher();
            FXMLLoader loader1=new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root1=loader1.load();
            DashboardController dbc=loader1.getController();
            dbc.setCenter(root);
        
            body.getScene().setRoot(root1);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Can't Delete");
            alert.setHeaderText("Mr "+cu.getUsername()+" you can't delete this comment");
            alert.setContentText("Ooops, this comment belongs to "+this.p.getUsername());

            alert.showAndWait();
        }
    }

    @FXML
    private void voirPlus(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("PostDetails.fxml"));
        Parent root=loader.load();
        PostDetailsController pdc=loader.getController();
        pdc.afficher(p);
       
        
        FXMLLoader loader1=new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root1=loader1.load();
        DashboardController dbc=loader1.getController();
        dbc.setCenter(root);
        
        body.getScene().setRoot(root1);
        /*Stage newWindow= new Stage();
        newWindow.setScene(scene);
        newWindow.show();*/
        
    }

    @FXML
    private void like(ActionEvent event) throws SQLException, IOException {
        PostServices ps=new PostServices();
        ps.addlike(this.p.getId(),this.p.getLikes()+1);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
            Parent root=loader.load();
            FeedController pdc=loader.getController();
            pdc.afficher();
            FXMLLoader loader1=new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root1=loader1.load();
            DashboardController dbc=loader1.getController();
            dbc.setCenter(root);
        
            body.getScene().setRoot(root1);
    }
    
}
