/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import service.*;
import entite.Circuits;
import entite.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import service.circuitService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author manaa
 */
public class AjouterCourseController implements Initializable {

    @FXML
    private TextField nomcourse;
    @FXML
    private DatePicker date;
    @FXML
    private Button add;
    @FXML
    private ImageView image;
    @FXML
    private Label myFile;
    @FXML
    private ComboBox<Circuits> Circuit;
    @FXML
    private ComboBox<Saison> saison;
    @FXML
    private ComboBox<User> Responsable;
    @FXML
    private Button retourc;
    @FXML
    private AnchorPane body;
    @FXML
    private ImageView img;
    @FXML
    private ImageView img11;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         circuitService es=new circuitService();
         es.read().forEach((e) -> {
             Circuit.getItems().add(e);
        });
          SaisonService s=new SaisonService();
        s.read().forEach((sa) -> {
            saison.getItems().add(sa);
        });
        
          UserService us=new UserService();
          
          us.readOrganisateur().forEach((su) -> {
              Responsable.getItems().add(su);
          });
          

          
          
          // TODO
    }    

    @FXML
    private void hundleButtonAction(ActionEvent event) throws ParseException {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Circuits cr= Circuit.getSelectionModel().getSelectedItem();
         Saison sai = saison.getSelectionModel().getSelectedItem();
         User us = Responsable.getSelectionModel().getSelectedItem();
         LocalDate dat = date.getValue();
         if(dat.isAfter(LocalDate.now())){
        course c1;
        c1 = new course(nomcourse.getText(),df.parse(date.getValue().toString()),cr,sai,us);
        CourseService cs=new CourseService();
        cs.insert(c1);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("le course est ajouté avec succé");
        alert.showAndWait();
    }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("date erreur");
                alert.setHeaderText(null);
                alert.setContentText("la date saisie n'est pas valide ");
                alert.showAndWait();
            }}

    @FXML
    private void Retour(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Course.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
