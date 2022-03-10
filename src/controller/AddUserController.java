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
public class AddUserController implements Initializable {

     @FXML
    private ImageView image;
     
     
    @FXML
    private Label labelTel;
     
       @FXML
    private TextField textfieldUsername;

    @FXML
    private TextField textfieldEmail;

       @FXML
    private PasswordField jpassword;
       
           @FXML
    private TextField textfieldTel;
          @FXML
    private Label myFile;
          
            @FXML
    private Label nom_fichier;
    
    @FXML
    private ComboBox<String> comboboxRole;
    
      ObservableList<String> role =FXCollections.observableArrayList("Admin","Organisaeur");
 
    
    private UserService us;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       comboboxRole.setItems(FXCollections.observableArrayList("Admin","Organisateur"));
        this.us = new UserService();
    }   
@FXML
    void Select(ActionEvent event) {


  
    }
    
    @FXML
    void onAjoute(ActionEvent event) {
        int status = 0 ;
        Timestamp timestamp = null;
        String username = textfieldUsername.getText();
        String email = textfieldEmail.getText();
        String password= jpassword.getText();
        String role =(comboboxRole.getValue());
       
        String image_name = myFile.getText();
     
        String tel = textfieldTel.getText();
      if(textfieldTel.getText().matches("^[0-9]+$") && textfieldTel.getText().length() != 8){
          labelTel.setText("veuillez saisir un correct numero");
      }
      else{
          User u = new User (username, email,tel, password, timestamp, role, image_name,status);
        us.insertUserPst(u); 
      }
        
       
          
        
        
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
                image.setVisible(true);
                //Déplacer l'image
                String newPath="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\images\\User\\";
                File sourceFile=null;
                File destinationFile=null;
                String nFile = f.getName();
                myFile.setText(nFile);
                sourceFile= new File(filename);
                destinationFile=new File(newPath+nFile);
                Files.copy(sourceFile.toPath() , destinationFile.toPath()); 
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   


   
    

    
}
