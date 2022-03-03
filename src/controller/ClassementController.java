/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class ClassementController implements Initializable {

    @FXML
    private AnchorPane classement;

         private Stage stage;
         private Scene scene;
         private Parent root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToClassementPilotes(ActionEvent event) {
    }

    @FXML
    private void switchToSaison(ActionEvent event) throws IOException {
       
         root = FXMLLoader.load(getClass().getResource("/view/Saison22.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void switchToClassementEquipes(ActionEvent event) {
    }

    @FXML
    private void switchToMain(ActionEvent event) {
    }
    
}
