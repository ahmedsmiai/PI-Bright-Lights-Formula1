/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class SaisonController implements Initializable {

    @FXML
    private AnchorPane Saison;
    @FXML
    private Button btnpageS11;

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
    private void switchToAddSaison(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("/view/AddSaison.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void switchToUpdSaison(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("/view/Updatesaison.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void switchToShowSaison(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("/view/Saison22.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void switchToDelSaison(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("/view/DelSaison.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void switchToStats(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("/view/saison.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void switchToClassemen(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("/view/classement.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
    
}
