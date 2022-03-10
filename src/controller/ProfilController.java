/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.vitrine.VitrineController;
//import static controller.vitrine.DashboardController.user;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import service.UserService;



public class ProfilController implements Initializable {
    
    
     @FXML
    private TextField emailTextfield;

    @FXML
    private TextField UsernameTextfield;

    @FXML
    private TextField textFieldTel;

    @FXML
    private TextField textFieldPassword;
    
     @FXML
    private Label labelUser_id;

    @FXML
    private ImageView image;
    
    @FXML
    
    private Label nom_fichier;
    @FXML Label myFile;

   VitrineController vc = new VitrineController ();
    private UserService us;
    public static   User user = new User();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if(this.user == null){
            this.labelUser_id.setVisible(false);
            this.UsernameTextfield.setVisible(false);
            this.emailTextfield.setVisible(false);
            this.textFieldPassword.setVisible(false);
            this.image.setVisible(false);
            this.textFieldTel.setVisible(false);
           
        }else{
           labelUser_id .setVisible(true);
           UsernameTextfield .setVisible(true);
            emailTextfield .setVisible(true);
           textFieldPassword .setVisible(true);
            image .setVisible(true);
           textFieldTel .setVisible(true);
            String user_id = Integer.toString(user.getUser_id());
        labelUser_id.setText(user_id);
        UsernameTextfield.setText(user.getUsername());
        emailTextfield.setText(user.getEmail());
        textFieldPassword.setText(user.getPassword());
        image.setImage(user.getImg().getImage());
        
        textFieldTel.setText(user.getTel());
        myFile.setText(user.getImage_name());
        }
        this.us = new UserService();
       
     
         
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
            String newPath="C:\\Users\\win10LIGHT\\Desktop\\Formula1\\src\\View\\images\\User\\";
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
       @FXML
    void onChange(ActionEvent event) {
        
        int user_id = Integer.parseInt(labelUser_id.getText());
        String username = UsernameTextfield.getText();
        String password = textFieldPassword.getText();
        String image_name = myFile.getText();
        String tel = textFieldTel.getText();
        
        
        User u = new User(user_id,username,tel, password,image_name);
        
        us.updateProfil(u);   
           System.out.println("ProfilController"+u.toString());
    }

     void setConnected(User u){
        System.out.println("Profiil"+u.toString());
      this.user.setUser_id(u.getUser_id());
      this.user.setUsername(u.getUsername());
      this.user.setEmail(u.getEmail());
      this.user.setPassword(u.getPassword());
      this.user.setCreate_time(u.getCreate_time());
      this.user.setRole(u.getRole());
      this.user.setImage_name(u.getImage_name());
      this.user.setImg(u.getImg());
      this.user.setStatus(u.getStatus());
      this.user.setTel(u.getTel());  
}
    
}
