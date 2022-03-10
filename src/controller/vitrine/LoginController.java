/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.AcceuilController;
import controller.DashboardController;
import controller.DashboardOrgController;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.swing.UIManager.getString;
import service.UserService;
import utils.Datasource;

/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    
    @FXML
    private AnchorPane Login;
    @FXML
    private JFXTextField textfieldEmail;

    @FXML
    private Label labelCnx;
    @FXML
    private JFXPasswordField textfieldPassword;
    
       @FXML
    private Label myLabel;
       DashboardController aa = new DashboardController();
       DashboardOrgController dc=new DashboardOrgController();
       VitrineController vc = new VitrineController();
        UserService us = new UserService();
          User u = new User();
          private static final String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
          
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
         this.us = new UserService();
    }   
      @FXML
      void onLogin(ActionEvent event) throws IOException, SQLException, Exception {
          
        String email = textfieldEmail.getText();
        String password = textfieldPassword.getText();
        
        if (textfieldEmail.getText().isEmpty()) 
        { labelCnx.setText("email invalid et mot de passe invalide");}
             else if(textfieldPassword.getText().isEmpty())
        {labelCnx.setText("email invalid et mot de passe invalide");}
             
       else if (!email.matches(EMAIL_PATTERN)){
            labelCnx.setText("email invalid et mot de passe invalide");
        }
        
       
   
        else{
              User u = us.login(email,password);
          
            if (u != null && "Admin".equals(u.getRole())) {
                System.out.println("fel onLogin "+u.toString());
                     aa.ConnectedAcceuil(u);
                     root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
                     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
            }
                else if (u != null &&"Organisateur".equals(u.getRole())){
                   System.out.println("onLogin organisateur"+u.toString());
                   dc.ConnectedAcceuil(u);
                     root = FXMLLoader.load(getClass().getResource("/view/dashboardOrg.fxml"));
                     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
                }
               else if (u != null &&"Spectateur".equals(u.getRole())){
                   System.out.println("logincontroller"+u.toString());
                     vc.setConnected(u);
                     root = FXMLLoader.load(getClass().getResource("/view/vitrine/vitrine.fxml"));
                     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();

                       }else{
                   System.out.println("null");
               }
             }
    
      
    }
      
    
    @FXML
    void onPageInscrire(ActionEvent event) throws IOException {
final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Vitrine/Inscription.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            Login.getChildren().setAll(pane);
            
            
    }
    
    
}
