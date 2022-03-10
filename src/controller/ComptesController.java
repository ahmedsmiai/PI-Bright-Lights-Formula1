/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import static controller.UpdateUserController.user;
import entite.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class ComptesController implements Initializable {

       @FXML
    private AnchorPane listedesComptes;
       
         @FXML
    private ComboBox<String> combobox;
       
        @FXML
    private TableView<User> tableRead;
        
      @FXML
    private TableColumn<User,Integer> colonneUser_id;

    @FXML
    private TableColumn<User,Integer> colonneUsername;

    @FXML
    private TableColumn<User,Integer> colonneEmail;

    @FXML
    private TableColumn<User,Integer> colonneNumTel;

    @FXML
    private TableColumn<User, ImageView> colonneImage;

    @FXML
    private TableColumn<User,String> colonneRole;
    
        @FXML
    private TextField recherche;
        @FXML
    private Label nom_excel;

    @FXML
    private Label excelFile;
      
        public  ObservableList<User> list = FXCollections.observableArrayList();
        
        
    
      
      
         private UserService us;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnDelete;
    
    UpdateUserController uc= new UpdateUserController();
    
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        this.us = new UserService();
        List<User> liste=us.read();
        RefreshTable(liste);
        tableRead.refresh();
        
    } 
        @FXML
   void rechercher(KeyEvent event) {
       List<User> liste=us.SearchByTel(recherche.getText());
       ObservableList<User> list=FXCollections.observableArrayList();   
        list.addAll(liste);
       colonneUser_id.setCellValueFactory(new PropertyValueFactory("User_id"));
        colonneUsername.setCellValueFactory(new PropertyValueFactory("Username"));
        colonneEmail.setCellValueFactory(new PropertyValueFactory("Email"));
        colonneNumTel.setCellValueFactory(new PropertyValueFactory("tel"));
        colonneImage.setCellValueFactory(new PropertyValueFactory("img"));
        colonneRole.setCellValueFactory(new PropertyValueFactory("Role"));
        tableRead.setItems(list);
       tableRead.refresh();
    }
  
    
     @FXML 
    private void onCreate() throws IOException{
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/AddUser.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);
    }
     @FXML
    private void onUpdate(ActionEvent event) throws IOException {
        
        User u;
        u=tableRead.getSelectionModel().getSelectedItem();
        uc.setUserUpdate(u);
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/UpdateUser.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);

    }
    private void RefreshTable(List<User> liste){
           ObservableList<User> list=FXCollections.observableArrayList();   
        list.addAll(liste);
        colonneUser_id.setCellValueFactory(new PropertyValueFactory("User_id"));
        colonneUsername.setCellValueFactory(new PropertyValueFactory("Username"));
        colonneEmail.setCellValueFactory(new PropertyValueFactory("Email"));
        colonneNumTel.setCellValueFactory(new PropertyValueFactory("tel"));
        colonneImage.setCellValueFactory(new PropertyValueFactory("img"));
        colonneRole.setCellValueFactory(new PropertyValueFactory("Role"));
        tableRead.setItems(list);
        //tableRead.refresh();
    }
       @FXML
   private void onDelete(ActionEvent event) {
Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer ce compte ?");
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
            UserService us= new UserService();
            us.delete(tableRead.getSelectionModel().getSelectedItem().getUser_id());
            RefreshTable(us.read());
        }
}  
    @FXML
   private void onExcel(ActionEvent event) throws IOException {
       //text file, should be opening in default text editor
        File file = new File("");
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        
        //let's try to open PDF file
        file = new File("C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\Users.xlsx");
        if(file.exists()) desktop.open(file);
   us.excel();
    }
   
     public void setUserUpdate(User u) {
      user.setUser_id(u.getUser_id());
      user.setUsername(u.getUsername());
      user.setEmail(u.getEmail());
      user.setPassword(u.getPassword());
      user.setCreate_time(u.getCreate_time());
      user.setRole(u.getRole());
      user.setImage_name(u.getImage_name());
      user.setImg(u.getImg());
      user.setStatus(u.getStatus());
      user.setTel(u.getTel());
       
        System.out.println(u.toString());    }
    
}

