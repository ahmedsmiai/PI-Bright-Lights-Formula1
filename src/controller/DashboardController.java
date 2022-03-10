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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class DashboardController implements Initializable {

   @FXML
    private AnchorPane dashboard;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private JFXButton logout;

    @FXML
    private AnchorPane slider;

    @FXML
    private AnchorPane body;
    
     @FXML
    private Button cpt;

    @FXML
    private Circle circle;

    
    private static User user=new User();
    ProfilController pc = new ProfilController();
    
    private UserService us=new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.user.getUser_id() > 0){
            Image im=this.user.getImg().getImage();
            circle.setFill(new ImagePattern(im));
            cpt.setText(this.user.getUsername());
        }
        
    }
    @FXML
    private void OpenParticipation(ActionEvent event) {
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Participation.fxml"));
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
    private void OpenQualifying(ActionEvent event) {
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Qualifying.fxml"));
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
    private void OpenCircuit(ActionEvent event) {
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Circuit.fxml"));
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
    private void OpenCourse(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Course.fxml"));
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
    private void OpenTicket(ActionEvent event) {
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Tickets.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //youusef
    
    
    @FXML
   void onLogOut() throws IOException{ 
       
             try {
                 final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/vitrine.fxml"));
                 final Node node= fxmlLoader.load();
                AnchorPane pane=new AnchorPane(node);
                dashboard.getChildren().setAll(pane);
                 us.logOut(this.user);
                  
             } catch (SQLException ex) {
                 Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
             }

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
    private void OpenEquipe(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Equipe.fxml"));
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
    private void OpenMembre(){
       final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Membre.fxml"));
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
    private void logout(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/vitrine.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            dashboard.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ConnectedAcceuil(User u) {
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
