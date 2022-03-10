/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Circuits;
import entite.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import service.CourseService;
import service.*;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class UpdateCourseController implements Initializable {

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
    private static course c = new course();
    @FXML
    private ComboBox<User> Responsable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        nomcourse.setText(c.getCourse_nom());
        
 //       ZoneId defaultZoneId = ZoneId.systemDefault();
//        Instant instant = c.getDate().toInstant();
 //       LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
 //       date.setValue(localDate);
        
        Circuit.getSelectionModel().select(c.getCircuit_circuit_id());
        saison.getSelectionModel().select(c.getSaison_year());
        Responsable.getSelectionModel().select(c.getUsers());
        
        
        
    }    

    @FXML
    private void hundleButtonAction(ActionEvent event) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           Circuits crs=Circuit.getSelectionModel().getSelectedItem();
           Saison sai = saison.getSelectionModel().getSelectedItem();
            User us = Responsable.getSelectionModel().getSelectedItem();
       LocalDate dat = date.getValue();
         if(dat.isAfter(LocalDate.now())){
        course c1;
        c1 = new course(c.getCourse_id(),nomcourse.getText(),df.parse(date.getValue().toString()),crs,sai,us);
        CourseService cs=new CourseService();
        cs.update(c1);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("le course est modifié avec succé");
        alert.showAndWait();
    }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("date erreur");
                alert.setHeaderText(null);
                alert.setContentText("la date saisie n'est pas valide ");
                alert.showAndWait();
            }
    }
     public void setCourse(course e) {
        c.setCourse_nom(e.getCourse_nom());
        c.setCourse_id(e.getCourse_id());
        c.setCircuit_circuit_id(e.getCircuit_circuit_id());
        c.setDate(e.getDate());
        c.setSaison_year(e.getSaison_year());
        c.setUsers(e.getUsers());
    }
    
}
