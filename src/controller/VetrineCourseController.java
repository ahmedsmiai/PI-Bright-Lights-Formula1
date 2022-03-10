/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.CourseController;
import entite.Circuits;
import entite.course;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import service.CourseService;
import service.circuitService;
import view.ItemController;
import view.Item_1Controller;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class VetrineCourseController implements Initializable {

    @FXML
    private AnchorPane body;
 
    @FXML
    private TextField recherche;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CourseService sc=new CourseService();
        List<course> cri=sc.read();
        RefrecheTable(cri);
        // TODO
    }    

     private void RefrecheTable(List<course> c){
         {
        int column=0;
        int row=1;
        try {
            for(course e:c){

                    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Item_1.fxml"));
                    AnchorPane pane=fxmlLoader.load();
                    Item_1Controller ic=fxmlLoader.getController();
                    ic.setData(e);
                    
                    if(column == 2){
                        column=0;
                        row++;
                    }
                    grid.add(pane,column++ , row);
                    //set width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    grid.setMargin(pane, new Insets(50,50,50,50));
            }
        } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    @FXML
    private void ChercherMembre(KeyEvent event) {
    }
}
