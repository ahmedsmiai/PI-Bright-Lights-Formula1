/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

import com.jfoenix.controls.JFXButton;
import controller.DashboardController;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.UserService;

/**
 *
 * @author nechi
 */
public class VitrineController implements Initializable{
    
     @FXML
    private AnchorPane vitrine;

    @FXML
    private HBox menu;

    @FXML
    private JFXButton inscrire;

    @FXML
    private JFXButton login;

    @FXML
    private AnchorPane body;
    
    @FXML
    private Circle circle;
    
    @FXML
    private AnchorPane compte;
    
    @FXML
    private Button btnc;

    @FXML
    private Button btnclose;
    
      @FXML
    private Label nom;

    @FXML
    private Label email;

    @FXML
    private Label tel;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    ProfilSpectateurController ps = new ProfilSpectateurController();
ResetPasswordController rpc = new ResetPasswordController();
   public static User user = new User();
   
      UserService us = new UserService();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //compte.setVisible(false);
        if(this.user.getUser_id()==0){
            login.setVisible(true);
            inscrire.setVisible(true);
            btnclose.setVisible(false);
            btnc.setVisible(false);
        }else{
           login.setVisible(false);
            inscrire.setVisible(false);
            btnclose.setVisible(false);
            btnc.setVisible(true);
            
            Image im=user.getImg().getImage();
            circle.setFill(new ImagePattern(im));
            nom.setText(user.getUsername());
            email.setText(user.getEmail());
            tel.setText(user.getTel());
        }
        
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(compte);
        slide.setToX(230);
        slide.play();
        compte.setTranslateX(0);
        
    }
     @FXML
    private void OpenRes(){
       final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VitrineResultatSaison.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML void OpenPart(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VitrineParticipation.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
     @FXML
    void OpenCircuit(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VetrineCircuit.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void OpenCourse(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VetrineCourse.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void Close(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(compte);
        slide.setToX(230);
        slide.play();
        compte.setTranslateX(0);
        btnclose.setVisible(false);
        btnc.setVisible(true);
    }
    
    @FXML
    void MonCompte(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(compte);
        slide.setToX(0);
        slide.play();
        compte.setTranslateX(230);
        btnclose.setVisible(true);
        btnc.setVisible(false);
    }
    
    @FXML
    private void OpenEquipe(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/Equipe.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void connexion(ActionEvent event) throws IOException {
           
           root = FXMLLoader.load(getClass().getResource("/view/vitrine/Login.fxml"));
                     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
    }
    
    
   
    
    
      @FXML
    void onDeconnexion(ActionEvent event){
         try {
             System.out.println("logout"+this.user.toString());
             us.logOut(this.user);
             login.setVisible(true);
             inscrire.setVisible(true);
             btnclose.setVisible(false);
             btnc.setVisible(false);
         } catch (SQLException ex) {
             Logger.getLogger(VitrineController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    @FXML
    private void OpenMembre(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/Membre.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
     void setConnected(User u){
       
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
     
     
      @FXML
    void inscrire() throws IOException {
         root = FXMLLoader.load(getClass().getResource("/view/vitrine/Inscription.fxml"));
                     //stage = (Stage)( (Node)event.getSource()).getScene().getWindow();
                     stage=new Stage();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
    }
     
     @FXML
    void ChangerMotdePasse(ActionEvent event) throws IOException {
            //rpc.setResetConnected(this.user);
            ProfilSpectateurController psc=new ProfilSpectateurController();
            psc.setResetConnected(user);
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/ProfilSpectateur.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
    }
    
}
