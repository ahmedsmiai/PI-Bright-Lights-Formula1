/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class UpdateUserController implements Initializable {

         @FXML
    private Label labelUsername;

    @FXML
    private Label labelEmail;

      @FXML
    private Label labelPassword;

    @FXML
    private Label User_id;
    
        @FXML
    private ImageView img;
    
    @FXML
    private PasswordField jPassword;

    @FXML
    private ComboBox<String> comboboxRole;
    
        @FXML
    private Label nom_fichier;
        
            @FXML
    private Label myFile;
        
          @FXML
    private ImageView image;
          
    ObservableList<String> role =FXCollections.observableArrayList("Admin","Organisaeur");

    private UserService us;
    public static User user = new User();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  Timestamp timestamp = null;
        String user_id = Integer.toString(user.getUser_id());
        labelUsername.setText(user.getUsername());
        labelEmail.setText(user.getEmail());
        labelPassword.setText(user.getPassword());
        img.setImage(user.getImg().getImage());
        myFile.setText(user.getImage_name());
            comboboxRole.setItems(FXCollections.observableArrayList("Admin","Organisateur"));
        this.us = new UserService();
        
    } 
    
    @FXML
    void onModifie(ActionEvent event) {   
        String role =(comboboxRole.getValue());
        us.updateRole(user, role);
    }
    
      @FXML
    void onChoisi(ActionEvent event) {
try {
            String filename;
            JFileChooser chooser= new JFileChooser();
            chooser.showOpenDialog(null);
            File f=chooser.getSelectedFile();
            filename = f.getAbsolutePath();
            nom_fichier.setText(filename);
            Image img = new Image(new FileInputStream(filename));
            image.setImage(img);
            //Déplacer l'image
            String newPath="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\images\\User\\";
            File sourceFile=null;
            File destinationFile=null;
            String nFile = f.getName();
            myFile.setText(nFile);
            sourceFile= new File(filename);
            destinationFile=new File(newPath+nFile);
            if(!destinationFile.exists()){
            Files.copy(sourceFile.toPath() , destinationFile.toPath()); 
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Image déjà existe");
                alert.showAndWait();
            }
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
