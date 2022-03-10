/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

import controller.AddUserController;
import entite.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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

/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class ProfilSpectateurController implements Initializable {

     @FXML
    private TextField emailTextfield;

    @FXML
    private TextField UsernameTextfield;

    @FXML
    private TextField textFieldTel;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private ImageView image;

    @FXML
    private Label nom_fichier;

    @FXML
    private Label myFile;

    @FXML
    private Label labelUser_id;

   public static User user = new User();
   private UserService us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         String user_id = Integer.toString(user.getUser_id());
        labelUser_id.setText(user_id);
        UsernameTextfield.setText(user.getUsername());
        emailTextfield.setText(user.getEmail());
        textFieldPassword.setText(user.getPassword());
        image.setImage(user.getImg().getImage());
        String tel = user.getTel();
        textFieldTel.setText(tel);
        this.us = new UserService();
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
           System.out.println("ProfilControllerSpectateur"+u.toString());
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
    
      public void setResetConnected(User u){
       
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
