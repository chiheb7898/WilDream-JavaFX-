/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Post;
import Services.PostServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author chihe
 */
public class PostStatsController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart;
    
    
    private List<Post> lp1;
    
    private ObservableList<String> PostTitles = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get an array with the English month names.
        PostServices ps=new PostServices();
        lp1=ps.afficherPosts();
        setPostData(lp1);
        barchart.setBarGap(3);
        barchart.setCategoryGap(40);
        // Convert it to a list and add it to our ObservableList of months.
        //PostTitles.addAll(Arrays.asList());
        
        // Assign the month names as categories for the horizontal axis.
        //xAxis.setCategories(monthNames);
    }
    public void setPostData(List<Post> lp) {
    	// Count the number of people having their birthday in a specific month.
        int[] postCounter = new int[lp.size()];
        for (Post p : lp) {
            int likes =p.getLikes();
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        XYChart.Series series2 = new XYChart.Series();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < lp.size(); i++) {
        	series.getData().add(new XYChart.Data<>(lp.get(i).getTitle(), lp.get(i).getLikes()));
                series2.getData().add(new XYChart.Data<>(lp.get(i).getTitle(), lp.get(i).getComments().size()));
                
        }
        
        barchart.getData().add(series);
        barchart.getData().add(series2);
    }
    public void showBirthdayStatistics() {
        
    }
    
}
