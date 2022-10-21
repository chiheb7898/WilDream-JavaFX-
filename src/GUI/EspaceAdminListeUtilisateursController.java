/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONNECTION.DataSource;
import Entity.Users;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;

/**
 * FXML Controller class
 *
 * @author eyana
 */
public class EspaceAdminListeUtilisateursController implements Initializable {

    @FXML
    private JFXButton btnlisteutilisateurs;
    @FXML
    private TableView<Users> table_user;
    @FXML
    private TableColumn<Users, Integer> id;
    @FXML
    private TableColumn<Users, String> name;
    @FXML
    private TableColumn<Users, String> email;
    @FXML
    private TableColumn<Users, String> role;
    @FXML
    private TableColumn<Users, String> adresse;
    @FXML
    private TableColumn<Users, String> tel;
    @FXML
    private Button edit;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button exel;
    @FXML
    private Button printbtn;
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private DataSource conn;
    private ObservableList<Users> data;
    @FXML
    private VBox vbox;
    @FXML
    private Pane mainpane;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InitUser();
        } catch (SQLException ex) {
            Logger.getLogger(EspaceAdminListeUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        show();
    } 
    
    
     public void InitUser() throws SQLException {
        conn = DataSource.getInstance();     
        try {
            data = FXCollections.observableArrayList();
            
            String sql = "SELECT id,username,email,roles,adresse,tel FROM Users";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = DataSource.getInstance();
               
                data.add(new Users(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("roles"),rs.getString("adresse"),rs.getString("tel")));
            }
                                           
                                           
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
            System.out.println("erreur!!!!");
        }
        
    }
     
    public void show(){
    
     
        name.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        role.setCellValueFactory(new PropertyValueFactory<Users,String>("roles"));
        adresse.setCellValueFactory(new PropertyValueFactory<Users,String>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<Users,String>("tel"));
       
        table_user.setItems(data); 
    }
     
   
    @FXML
  private void Imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
         printNode(table_user);
    }
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout
        = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX= pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
    double scaleY= pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    node.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, node);
      if (success) {
        job.endJob();

      }
    }
    node.getTransforms().remove(scale);
  }

    @FXML
  

 private void ExportExcel(ActionEvent event) throws FileNotFoundException, IOException {
         try {
           
            conn = DataSource.getInstance();
            Statement stmt1 = conn.getCnx().createStatement();
            //Variable counter for keeping tracofk  number of rows inserted.  
            int counter = 1;
            FileOutputStream fileOut = null;
           
            String sql = "Select * from users";

            //Creation of New Work Book in Excel and sheet.  
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            //Creating Headings in Excel sheet.  
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 1).setCellValue("id");//Row Name1  
            rowhead.createCell((short) 2).setCellValue("username");//Row Name2  
            rowhead.createCell((short) 3).setCellValue("email");//Row Name3  
            rowhead.createCell((short) 5).setCellValue("roles");//Row Name4
            rowhead.createCell((short) 8).setCellValue("adresse");//Row Name5 
            rowhead.createCell((short) 9).setCellValue("tel");//Row Name5 

            ResultSet rs = stmt1.executeQuery(sql);
            while (rs.next()) {
                //Insertion in corresponding row  
                HSSFRow row = sheet.createRow((int) counter);
                /* Activity, Username, TIME_OF_EVENT are row names  
          * corresponding to table  
          * in related Database. */
                CellStyle dateCellStyle = hwb.createCellStyle();
                CellStyle dateCellStyle1 = hwb.createCellStyle();
                CreationHelper createHelper = hwb.getCreationHelper();
                //Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
//                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//                dateCellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

                row.createCell((short) 1).setCellValue(rs.getInt("id"));
                row.createCell((short) 2).setCellValue(rs.getString("username"));
                row.createCell((short) 3).setCellValue(rs.getString("email"));
                row.createCell((short) 5).setCellValue(rs.getString("roles"));
                row.createCell((short) 8).setCellValue(rs.getString("adresse"));
                row.createCell((short) 9).setCellValue(rs.getString("tel"));

//                row.createCell((short) 3).setCellStyle(dateCellStyle);
//                row.createCell((short) 3).setCellValue(rs.getDate("date"));
            
//                Cell dateS = row.createCell((short) 4);
//                dateS.setCellValue(rs.getDate("dates"));
//                dateS.setCellStyle(dateCellStyle);
//
//
//                Cell dateE = row.createCell((short) 5);
//                dateE.setCellValue(rs.getDate("datee"));
//                dateE.setCellStyle(dateCellStyle1);

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);

                counter++;
                try {
                      
                    fileOut = new FileOutputStream("Users.xls");
                    hwb.write(fileOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Close all the parameters once writing to excel is compelte.  
            fileOut.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("votre fichier a ete exporter veuillez verifier votre explorateur");
            alert.showAndWait();
            rs.close();
            stmt1.close();
            
            alert.setTitle("Excel created");
            alert.setHeaderText(null);
            alert.setContentText("votre fichier a été créé veuillez vérifier votre explorateur");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void statistique(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/EspaceAdminStat.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }

    private void logout(ActionEvent event) throws SQLException {
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
    private void RedirecttoListeUtilisateursPage(ActionEvent event) {
    }

    @FXML
    private void edituserlist(ActionEvent event) {
    }

    @FXML
    private void adduserlist(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/AjouterUser.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }

    @FXML
    private void deleteuserlist(ActionEvent event) throws SQLException {
        conn = DataSource.getInstance();
        int selectedIndex = table_user.getSelectionModel().getSelectedIndex();
        Users m = (Users) table_user.getSelectionModel().getSelectedItem();
        int tempItemTag = m.getId();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getId()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = DataSource.getInstance();
                String query1 = "DELETE FROM Users WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                table_user.getItems().remove(selectedIndex);
            }
        }

    }

    
}
