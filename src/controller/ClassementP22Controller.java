/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDate;

import entite.ClassementPilotes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import service.ClassementPilotesService;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class ClassementP22Controller implements Initializable {

    @FXML
    private AnchorPane listedesComptes;
    @FXML
    private TableView<ClassementPilotes> tableRead;
    @FXML
    private TableColumn<ClassementPilotes, Integer> colonneclassement_id;
    @FXML
    private TableColumn<ClassementPilotes, Integer> colonnesaisons_year;
    @FXML
    private TableColumn<ClassementPilotes, Integer> colonnepilotes_pilote_id;
    @FXML
    private TableColumn<ClassementPilotes, Integer> points_total;
    
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnDelete;
   
public ObservableList<ClassementPilotes> list = FXCollections.observableArrayList();
         private ClassementPilotesService s;
    @FXML
    private Button btnExcel1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          this.s = new ClassementPilotesService();
        RefreshTable();
    }    
    
     private void RefreshTable(){
           ObservableList<ClassementPilotes> list=FXCollections.observableArrayList();   
        list.addAll(s.read());
        colonneclassement_id.setCellValueFactory(new PropertyValueFactory("classementP_id "));
        colonnesaisons_year.setCellValueFactory(new PropertyValueFactory("saisons_year "));
        colonnepilotes_pilote_id.setCellValueFactory(new PropertyValueFactory("pilotes_pilote_id "));
       points_total.setCellValueFactory(new PropertyValueFactory("points_total"));
       
        tableRead.setItems(list);
        s.read();
    }
     
    @FXML
    private void onCreate(ActionEvent event) throws IOException{
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddClassementP.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onUpdate(ActionEvent event) throws IOException{
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateClassementP.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);
    }



    @FXML
    private void onDelete(ActionEvent event) {
        
Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer ce compte ?");
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
            
            ClassementPilotes p  = tableRead.getSelectionModel().getSelectedItem() ;
            int id = p.getClassementP_id();
            ClassementPilotes y = new ClassementPilotes(id ) ;
            ClassementPilotesService us= new ClassementPilotesService();
            us.delete(y);
            RefreshTable();
        }
        
    }

    @FXML
    private void switchToClassement(ActionEvent event)throws IOException {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/classement.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onStat(ActionEvent event) {
    }
    
        @FXML
    private void onExcel(ActionEvent event) {
    }
}
    
