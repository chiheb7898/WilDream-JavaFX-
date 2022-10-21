/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Comment;
import Entity.CurrentUser;
import Entity.Post;
import Services.CommentServices;
import Services.PostServices;
import Services.ServiceCurrentUser;
import Services.Service_User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chihe
 */
public class PostDetailsController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text date;
    @FXML
    private Text body;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vbox1;
    @FXML
    private TextArea cmntbody;
    @FXML
    private Button addcmnt;
    private Post p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    public void afficher(Post P) throws IOException, SQLException{
       
        this.username.setText(P.getUsername());
        this.body.setText(P.getBody());
        this.date.setText(P.getTime().toString());
        List<Comment> comments = P.getComments();
        String is=P.getFile();
        
        PostServices ps= new PostServices();
        
        if(P.getType().equals("image"))
        {
        Image image =new Image("file:"+is,1080,1080,true,true);
        ImageView imageview=new ImageView();
        imageview.setFitHeight(280);
        imageview.setFitWidth(520);
        imageview.setImage(image);
        imageview.setVisible(true);
            System.out.println("image");
        vbox1.getChildren().add(imageview);
        }
        else if (P.getType().equals("video"))
        {
            System.out.println(new File(is).toURI().toString());
            Media media= new Media(new File(is).toURI().toString());
            MediaPlayer mediaplayer=new MediaPlayer(media);
            mediaplayer.setVolume(0);
            mediaplayer.setAutoPlay(true);
            mediaplayer.setCycleCount(mediaplayer.INDEFINITE);
            MediaView mediaview= new MediaView(mediaplayer);
            mediaview.setFitHeight(280);
            mediaview.setFitWidth(520);
            System.out.println("video");
            vbox1.getChildren().add(mediaview);
        }
        //List<Post> posts = ps.afficherPosts();
        
        
        for(int i=0;i<comments.size();i++)
        {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherComment.fxml"));
            AnchorPane anchorPane=loader.load();
            AfficherCommentController apc=loader.getController();
            apc.setAffichageComment(comments.get(i));           
            vbox.getChildren().add(i,anchorPane);
            vbox.setMargin(anchorPane,new Insets(3, 3, 5, 5));
        }
        this.p=P;
        /*FXMLLoader loader=new FXMLLoader();
        //loader.load(getClass().getResource("AfficherComment.fxml"));
        AnchorPane anchorPane=loader.load(getClass().getResource("AfficherComment.fxml"));
        AfficherCommentController apc=loader.getController();
        //apc.setAffichageComment(comments.get(1));
        vbox.getChildren().add(anchorPane);*/

        
            
    }

    @FXML
    private void AjoutCemmentaire(ActionEvent event) throws IOException, SQLException {
        String body= cmntbody.getText();
        Date date = new Date();
        ServiceCurrentUser scu=new ServiceCurrentUser();
        CurrentUser cu=scu.infos();
        Comment c=new Comment(33, body, date,cu.getIduser() ,this.p.getId());
        CommentServices cs=new CommentServices();
        cs.ajouterComment(c);
        
        PostServices ps=new PostServices();
        Post pa=ps.serarchPostsbyID(p.getId()).get(0);
        Service_User su=new Service_User();
        pa.setUsername(su.getUserName(pa.getUser_id()));
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("PostDetails.fxml"));
        Parent root=loader.load();
        PostDetailsController pdc=loader.getController();
        pdc.afficher(pa);
            
        FXMLLoader loader1=new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root1=loader1.load();          
        DashboardController dbc=loader1.getController();
        dbc.setCenter(root);
        
        this.body.getScene().setRoot(root1);      
    }
    
}
