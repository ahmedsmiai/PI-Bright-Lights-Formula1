/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

import com.jfoenix.controls.JFXTextField;

import entite.User;
import java.net.URL;
import java.sql.Timestamp;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.UserService;


/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class ResetPasswordController implements Initializable {

      @FXML
    private JFXTextField passwordtextfield;
      
      @FXML
    private Label labelUserid;
    @FXML
    private JFXTextField confirmpaswordtextfield;
    
    public static User user = new User();
             private UserService us;
             
                /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           this.us = new UserService();
    } 
     @FXML
    void onUpdatePass(ActionEvent event) {  
         System.out.println("reset"+this.user.toString());
        String password= passwordtextfield.getText();
        us.updatePassword(this.user, password);
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
    

