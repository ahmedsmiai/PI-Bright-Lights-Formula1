/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import entite.Saison;
import java.sql.Date;
import java.time.LocalDate;
import service.SaisonService;
import java.io.IOException;
import entite.ClassementEquipes;
import java.sql.Date;
import java.time.LocalDate;
import service.ClassementEquipesService;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class AddclassementEController implements Initializable {

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
    

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox<ClassementEquipes> choiceP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SaisonService ss = new SaisonService();
 ClassementEquipesService pp = new ClassementEquipesService() ;
        choisYear.getItems().addAll(ss.readjustid());
        choiceP.getItems().addAll(pp.readjustnom());

    }

    @FXML
    private void onCreate(ActionEvent event) {
        
        
       //  ClassementPilotesService pp = new ClassementPilotesService() ;
       ClassementEquipesService ss = new ClassementEquipesService();
     
        int ptst = Integer.parseInt(textfieldPts.getText());
        
         Saison s = choisYear.getSelectionModel().getSelectedItem();
          int year = s.getYear();
         
         ClassementEquipes p = ss.convert( choiceP.getSelectionModel().getSelectedItem().getNom());
         int piloteid = p.getEquipes_equipe_id();
     
      
        ClassementEquipes y = new ClassementEquipes(year, piloteid, ptst);
      //  ClassementPilotesService ss = new ClassementPilotesService();
        int v ;
        v = ss.verifajout(piloteid, year);
        if (v==0) {
        if (textfieldPts.getText().matches("^[0-9]+$") && textfieldPts.getText().length() < 4 && ptst<1000 ){
       
        ss.insertclassemet_equipePst(y);  /////////ajout

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("notification");
        alert.setHeaderText(null);
        alert.setContentText("ajout success!");
        alert.showAndWait();
        
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pts erreur");
                alert.setHeaderText(null);
                alert.setContentText("points depasse limit (3 char)");
                alert.showAndWait();
        }
        }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("verif erreur");
                alert.setHeaderText(null);
                alert.setContentText("pilote deja existe dans cette saison");
                alert.showAndWait();
        }
    }

    @FXML
    private void switchToSaison(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/Cequipe.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}