/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entite.Saison;
import java.sql.Date;
import java.time.LocalDate;
import service.SaisonService;
import java.io.IOException;
import entite.ClassementPilotes;
import java.sql.Date;
import java.time.LocalDate;
import service.ClassementPilotesService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class UpdateClassementPController implements Initializable {

    @FXML
    private AnchorPane ajout;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label myFile;
    @FXML
    private Label nom_fichier;
    @FXML
    private ChoiceBox<ClassementPilotes>yearChoise;

    
          private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private TextField textfieldPts;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ClassementPilotesService ss=new ClassementPilotesService();   
     
        yearChoise.getItems().addAll(ss.readjustid());
    }    

    @FXML
    private void onUpdate(ActionEvent event) {
        int pts = Integer.parseInt(textfieldPts.getText());
        ClassementPilotes s  = yearChoise.getSelectionModel().getSelectedItem();
     int id = s.getClassementP_id();
             ClassementPilotes y = new ClassementPilotes(id , pts) ;

       ClassementPilotesService ss=new ClassementPilotesService();   
    if (textfieldPts.getText().matches("^[0-9]+$") && textfieldPts.getText().length() == 4 && pts>999){

       ss.update(y);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("notification");
        alert.setHeaderText(null);
        alert.setContentText("success!");
        alert.showAndWait();
    }else {
         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pts erreur");
                alert.setHeaderText(null);
                alert.setContentText("points depasse limit (3 char)");
                alert.showAndWait();
    }
    }
    @FXML
    private void switchToSaison(ActionEvent event)throws IOException {
                  root = FXMLLoader.load(getClass().getResource("/view/pilote.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
    
}
