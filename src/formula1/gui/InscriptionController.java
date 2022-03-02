/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1.gui;



import entite.User;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.UserService;


/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField textfieldUsername;
    @FXML
    private TextField textfieldPassword;
    @FXML
    private Button btnsubmit;
    @FXML
    private TextField textfieldEmail;

    private UserService userService;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.userService = new UserService();
    }    

    @FXML
    private void onCreate(ActionEvent event) {
    String role ="spectateur";
    Timestamp timestamp = null;
    String username = textfieldUsername.getText();
    String email = textfieldEmail.getText();
    String password = textfieldPassword.getText();
    
    
    User u;
        u = new User(username,email,password,timestamp,role);
        userService.insertUserPst(u);
    }
    
}