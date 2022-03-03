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
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class AddClassementPController implements Initializable {

    @FXML
    private AnchorPane ajout;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label myFile;
    @FXML
    private ImageView image;
    @FXML
    private Label nom_fichier;
    @FXML
    private TextField textfieldPts;
    @FXML
    private ChoiceBox<Saison> choisYear;
    @FXML
    private TextField textfieldPiloteid;

    
             private Stage stage;
 private Scene scene;
 private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SaisonService ss=new SaisonService();   
     
        choisYear.getItems().addAll(ss.readjustid());
    }    

    @FXML
    private void onCreate(ActionEvent event) {
         Saison s  = choisYear.getSelectionModel().getSelectedItem();
     int year = s.getYear();
     int piloteid = Integer.parseInt(textfieldPiloteid.getText());
   int ptst = Integer.parseInt(textfieldPts.getText());
     
     ClassementPilotes y = new ClassementPilotes(year , piloteid, ptst) ;

       ClassementPilotesService ss=new ClassementPilotesService();   
       ss.insertclassemet_pilotePst(y);
    }

    @FXML
    private void switchToSaison(ActionEvent event) throws IOException  {
          root = FXMLLoader.load(getClass().getResource("/view/pilote.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
    
}
