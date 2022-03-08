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
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class AddSaisonController implements Initializable {

    @FXML
    private AnchorPane ajout;
    @FXML
    private TextField textfieldYear;
    @FXML
    private Label myFile;
    @FXML
    private Label nom_fichier;
    @FXML
    private DatePicker calenderDd;
    @FXML
    private DatePicker calenderDf;
    @FXML
    private Button btnAjouter;

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
    private void switchToSaison(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/Saison22.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onCreate(ActionEvent event) {

        LocalDate datDeb = calenderDd.getValue();
        LocalDate datFin = calenderDf.getValue();
        if (textfieldYear.getText().matches("^[0-9]+$") && textfieldYear.getText().length() == 4) {
            if ( datFin.isAfter(datDeb) ) {
                int year = Integer.parseInt(textfieldYear.getText());

                Saison s = new Saison(year, datDeb, datFin);

                SaisonService ss = new SaisonService();

                ss.inserSaisonPst(s);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("notification");
                alert.setHeaderText(null);
                alert.setContentText("ajout success!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("date erreur");
                alert.setHeaderText(null);
                alert.setContentText("date debut > date fin ");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("year erreur");
            alert.setHeaderText(null);
            alert.setContentText("verifier saison year ");
            alert.showAndWait();
        }

    }

}
