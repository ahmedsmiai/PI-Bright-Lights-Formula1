/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import entite.Saison;
import java.sql.Date;
import java.time.LocalDate;
import service.SaisonService;
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

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class DelSaisonController implements Initializable {

    @FXML
    private AnchorPane ajout;
    private TextField textfieldYear;
    @FXML
    private Button btnSuprimer;
    @FXML
    private Label myFile;
    @FXML
    private ImageView image;
    @FXML
    private Label nom_fichier;
    private DatePicker calenderDd;
    private DatePicker calenderDf;
    
            private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private ChoiceBox<Saison> yearChoise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SaisonService ss=new SaisonService();   
     
        yearChoise.getItems().addAll(ss.readjustid());
    }    

    @FXML
    private void onDelete(ActionEvent event) {
        // int year = Integer.parseInt(textfieldYear.getText());
         Saison s  = yearChoise.getSelectionModel().getSelectedItem();
     int year = s.getYear();
    
   
        Saison y = new Saison(year ) ;

       SaisonService ss=new SaisonService();   
       ss.delete(y);
    }

    @FXML
    private void switchToSaison(ActionEvent event)  throws IOException{
  root = FXMLLoader.load(getClass().getResource("/view/pilote.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
    
}
