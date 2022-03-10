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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import entite.*;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class CourseController implements Initializable {

    @FXML
    private AnchorPane body;
    @FXML
    private TableView<course> table;

    @FXML
    private TableColumn<course, String> nomCourse;
    @FXML
    private TableColumn<course, Date> Datecourse;
    @FXML
    private TableColumn<course, String> circuitcourse;
    @FXML
    private TableColumn<course, Integer> saisoncourse;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField recherche;
    @FXML
    private Button Tickets;
    @FXML
    private TableColumn<course, String> responsable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RefreshTable();
        // TODO
    }

    @FXML
    private void OpenAddCourse(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AjouterCourse.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane = new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CircuitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OpenUpdateCourse(ActionEvent event) {
        UpdateCourseController uc = new UpdateCourseController();
        course c = new course();
        c = table.getSelectionModel().getSelectedItem();
//        System.out.println(c.toString());
        uc.setCourse(c);
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateCourse.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane = new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteCourse(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette équipe ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            CourseService es = new CourseService();
            es.delete(table.getSelectionModel().getSelectedItem().getCourse_id());
            RefreshTable();

        }
    }

    @FXML
    private void ChercherCourse(KeyEvent event) {
        CourseService cs = new CourseService();
        ObservableList<course> tab = FXCollections.observableArrayList();
        tab.addAll(cs.SearchByName(recherche.getText()));

        nomCourse.setCellValueFactory(new PropertyValueFactory("course_nom"));
        Datecourse.setCellValueFactory(new PropertyValueFactory("date"));
       circuitcourse.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCircuit_circuit_id().getNom()));
        saisoncourse.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getSaison_year().getYear()));
        responsable.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getUsers().getUsername()));
        table.setItems(tab);

    }

    private void RefreshTable() {
        CourseService es = new CourseService();

        //RemplissageTableau
        ObservableList<course> tab = FXCollections.observableArrayList();
        tab.addAll(es.read());

        nomCourse.setCellValueFactory(new PropertyValueFactory("course_nom"));
        Datecourse.setCellValueFactory(new PropertyValueFactory("date"));
        circuitcourse.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCircuit_circuit_id().getNom()));
        saisoncourse.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getSaison_year().getYear()));
        responsable.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getUsers().getUsername()));
        table.setItems(tab);

    }

    @FXML
    private void OpenTickets(ActionEvent event) {
        
        TicketService ts = new TicketService();
        course c = new course();
        c = table.getSelectionModel().getSelectedItem();
        AjouterTicketsController ATC = new AjouterTicketsController();
        ATC.setCourse(c); //        System.out.println(c.toString());
       if(ts.countTicketsNTUser(c.getCourse_id(), c)){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AjouterTickets.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane = new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }}
       else{ Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("date erreur");
                alert.setHeaderText(null);
                alert.setContentText("la date saisie n'est pas valide ");
                alert.showAndWait();}

    }

}
