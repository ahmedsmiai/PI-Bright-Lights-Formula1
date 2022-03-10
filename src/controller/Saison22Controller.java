/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDate;
import service.SaisonService;
import entite.Saison;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
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
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import service.SaisonService;
import view.StatEvenetController;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class Saison22Controller implements Initializable {

    @FXML
    private AnchorPane listedesComptes;
    @FXML
    private TableView<Saison> tableRead;
    @FXML
    private TableColumn<Saison, Integer> colonneYear;
    @FXML
    private TableColumn<Saison, LocalDate> colonneDatedeb;
    @FXML
    private TableColumn<Saison, LocalDate> colonneDatefin;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnDelete;
   
public ObservableList<Saison> list = FXCollections.observableArrayList();
         private SaisonService s;
    @FXML
    private Button btnExcel1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          this.s = new SaisonService();
        RefreshTable();
    }    
    
     private void RefreshTable(){
           ObservableList<Saison> list=FXCollections.observableArrayList();   
        list.addAll(s.read());
        colonneYear.setCellValueFactory(new PropertyValueFactory("year"));
        colonneDatedeb.setCellValueFactory(new PropertyValueFactory("date_debut"));
        colonneDatefin.setCellValueFactory(new PropertyValueFactory("date_fin"));
       
        tableRead.setItems(list);
        s.read();
    }
     
    @FXML
    private void onCreate(ActionEvent event) throws IOException{
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddSaison.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onUpdate(ActionEvent event) throws IOException{
         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateSaison.fxml"));
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
            
            Saison s  = tableRead.getSelectionModel().getSelectedItem() ;
            int year = s.getYear();
            LocalDate db = s.getDate_debut();
            LocalDate df = s.getDate_fin();
            Saison y = new Saison(year, db, df) ;
            SaisonService us= new SaisonService();
            
           int v= us.verifD(y);
           if (v>0){
                 Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("erreur");
                al.setHeaderText(null);
                al.setContentText("saison deja utilise , vous ne pouvez pas le supprimer");
                al.showAndWait();
            }else {
            
            
            us.delete(y);
            RefreshTable();}
        }
        
    }
    
//        @FXML
//    private void onDelete(ActionEvent event) {
//        System.out.println("delete pilote controller");
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation de suppresssion");
//        alert.setContentText("Voulez-vous vraiment supprimer ce compte ?");
//        Optional<ButtonType> action = alert.showAndWait();
//        if (action.get() == ButtonType.OK) {
//
//            Saison p = tableRead.getSelectionModel().getSelectedItem();
//            int id = p.getYear();
//            Saison y = new Saison(id);
//            SaisonService us = new SaisonService();
//
//            us.delete(us.readById(id));
//            RefreshTable();
//        }
//    }

    @FXML
    private void switchToClassement(ActionEvent event)throws IOException {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/classement.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onStat(ActionEvent event) throws IOException{
 FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../view/StatEvenet.fxml"));
                            try {
                                loader.load();
                                
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                         
                            StatEvenetController atc = loader.getController();
                            

      
                Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    
        @FXML
    private void onExcel(ActionEvent event) {
    }
    
}
