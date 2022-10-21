/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author chihe
 */
public class WildreamFXMain extends Application {
    
    private static Stage pStage;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/accueil.fxml"));
            Scene scene = new Scene(root, 1280,760);
            primaryStage.setTitle("Wildream");
            primaryStage.setScene(scene);
            setPrimaryStage(primaryStage);
            pStage = primaryStage;
            primaryStage.show();
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(WildreamFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        this.pStage = pStage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
