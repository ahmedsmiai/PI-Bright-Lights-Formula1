/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import service.UserService;


/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class AcceuilController implements Initializable {

    @FXML
    private AnchorPane slider;
    
    @FXML
    private BorderPane BorderPane;
    
    @FXML
    private Label usernameLabel;
    
       @FXML
    private ImageView imgProfil;
    
    @FXML
    private AnchorPane body;
    
    ProfilController pc = new ProfilController();
   public static  User user = new User();
       private UserService us;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(this.user == null){
            usernameLabel.setVisible(false);
            imgProfil.setVisible(false);
           
        }else{
           usernameLabel .setVisible(true);
           imgProfil .setVisible(true);
               usernameLabel.setText(user.getUsername());
            imgProfil.setImage(user.getImg().getImage());
        }
        this.us = new UserService();
        
         
          
             
        System.out.println("accueil controller "+this.user.toString());
    }

    
    @FXML
    private void OpenComptes(){
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Comptes.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        @FXML
    void openProfil(ActionEvent event) {
        pc.setConnected(user);
            System.out.println("openProfil"+user.toString());
      
               final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Profil.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       @FXML
   void onLogOut(ActionEvent event) throws SQLException {   
            us.logOut(this.user);
            usernameLabel.setVisible(false);
            imgProfil.setVisible(false);
            
             
   }
   
   
   public void ConnectedAcceuil(User u){
       System.out.println("ConnectedAccueil "+u.toString());
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

