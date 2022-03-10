/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import entite.Circuits;
import service.*;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class CircuitController implements Initializable {

    @FXML
    private AnchorPane body;
    @FXML
    private TableView<Circuits> table;
   
    @FXML
    private TableColumn<Circuits, String> nomCircuit;
    @FXML
    private TableColumn<Circuits, String> PaysCircuit;
    @FXML
    private TableColumn<Circuits, Integer> Longeur;
    @FXML
    private TableColumn<Circuits, Integer> CrsDist;
    @FXML
    private TableColumn<Circuits, Integer> capacite;
    @FXML
    private TableColumn<Circuits, String> desc;
    @FXML
    private TextField recherche;
    @FXML
    private Button addCircuit;
    @FXML
    private Button updateCircuit;
    @FXML
    private Button deleteCircuit;
    @FXML
    private Button retourc;
    
    
    UpdateCircuitController upc=new UpdateCircuitController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RefrecheTable();
        // TODO
    }    

    @FXML
    private void OpenAddCircuit(ActionEvent event) {
          final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AjouterCircuit.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CircuitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void OpenUpdateCircuit(ActionEvent event) {
         Circuits c=new Circuits();
        c=table.getSelectionModel().getSelectedItem();
        upc.setCircuit(c);
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateCircuit.fxml"));
            final Node node;
             try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CircuitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void DeleteCircuit(ActionEvent event) {
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette équipe ?");
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
            circuitService es= new circuitService();
            es.delete(table.getSelectionModel().getSelectedItem().getCircuit_id());
            RefrecheTable();
        }
        
    }

    @FXML
    private void ChercherMembre(KeyEvent event) {
        circuitService cs=new circuitService();
        ObservableList<Circuits> list = FXCollections.observableArrayList();
        list.addAll(cs.SearchByName(recherche.getText()));
        
        nomCircuit.setCellValueFactory(new PropertyValueFactory ("nom") );
        PaysCircuit.setCellValueFactory(new PropertyValueFactory ("pays") );
        Longeur.setCellValueFactory(new PropertyValueFactory ("lang") );
        CrsDist.setCellValueFactory(new PropertyValueFactory("course_distance") );
        desc.setCellValueFactory(new PropertyValueFactory ("description") );
        capacite.setCellValueFactory(new PropertyValueFactory("capacite") );
        table.setItems(list);

    

    }

    private void RefrecheTable(){

        circuitService cs=new circuitService();
        ObservableList<Circuits> list = FXCollections.observableArrayList();
        list.addAll(cs.read());
       
        nomCircuit.setCellValueFactory(new PropertyValueFactory ("nom") );
        PaysCircuit.setCellValueFactory(new PropertyValueFactory ("pays") );
        Longeur.setCellValueFactory(new PropertyValueFactory ("lang") );
        CrsDist.setCellValueFactory(new PropertyValueFactory("course_distance") );
        desc.setCellValueFactory(new PropertyValueFactory ("description") );
        capacite.setCellValueFactory(new PropertyValueFactory("capacite") );
        table.setItems(list);
    }

    @FXML
    private void Retour(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
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
