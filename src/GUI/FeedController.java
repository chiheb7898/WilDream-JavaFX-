/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Post;
import Services.PostServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author chihe
 */
public class FeedController implements Initializable {

    @FXML
    private GridPane postGrid;
    
    private List <Post> posts;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    public Window retWindow()
    {
        return postGrid.getScene().getWindow();
    }

    public GridPane getPostGrid() {
        return postGrid;
    }

    public void setPostGrid(GridPane postGrid) {
        this.postGrid = postGrid;
    }

    @FXML
    private void search(KeyEvent event) throws SQLException {
        postGrid.getChildren().clear();
        int column=0;
        int row=1;
        PostServices ps= new PostServices();
        posts=ps.serarchPosts(this.search.getText());
        //System.out.println(posts.toString());
        try {
        for(int i=0;i<posts.size();i++)
        {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherPost.fxml"));
        AnchorPane anchorPane=loader.load();
        AfficherPostController apc=loader.getController();
        apc.setAffichage(posts.get(i));
            if (column==2) {
                column=0;
                ++row;     
            }
        postGrid.add(anchorPane, column++, row);
        GridPane.setMargin(anchorPane,new Insets(10, 10, 10, 10));
        }
        }
        catch (IOException ex) {
                Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void afficher() throws SQLException{
        int column=0;
        int row=1;
        PostServices ps= new PostServices();
        posts=ps.afficherPosts();
        try {
        for(int i=0;i<posts.size();i++)
        {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherPost.fxml"));
        AnchorPane anchorPane=loader.load();
        AfficherPostController apc=loader.getController();
        apc.setAffichage(posts.get(i));
            if (column==2) {
                column=0;
                ++row;     
            }
        postGrid.add(anchorPane, column++, row);
        GridPane.setMargin(anchorPane,new Insets(10, 10, 10, 10));
        }
        }
        catch (IOException ex) {
                Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
