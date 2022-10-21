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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author chihe
 */
public class AfficherCommentController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text date;
    @FXML
    private Text body;
    
    private int id;
    private int id_post;
    @FXML
    private Button supcmnt;
    private int user_id;
    private String user_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setAffichageComment(Comment c) throws SQLException {
        Service_User su=new Service_User();
        c.setUsername(su.getUserName(c.getId_user()));
        this.user_name=c.getUsername();
        this.username.setText(c.getUsername());
        this.body.setText(c.getBody());
        this.date.setText(c.getTime().toString());
        this.id=c.getId();
        this.id_post=c.getId_post();
        this.user_id=c.getId_user();
        
    }

    @FXML
    private void deletecmnt(ActionEvent event) throws IOException, SQLException {
        
        ServiceCurrentUser scu=new ServiceCurrentUser();
        CurrentUser cu=scu.infos();
        if(cu.getIduser()==user_id){
            CommentServices cs=new CommentServices();
            cs.deleteComment(id);
            System.out.println(this.id_post);
            PostServices ps=new PostServices();
        Post pa=ps.serarchPostsbyID(this.id_post).get(0);
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
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Can't Delete");
            alert.setHeaderText("Mr "+cu.getUsername()+" you can't delete this comment");
            alert.setContentText("Ooops, this comment belongs to "+user_name);

            alert.showAndWait();
            
        }
    }
}
