/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Circuits;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import service.circuitService;
import view.ItemController;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class VetrineCircuitController implements Initializable {

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
        circuitService sc=new circuitService();
        List<Circuits> cri=sc.read();
        RefrecheTable(cri);
        // TODO
    }    

    @FXML
    private void ChercherMembre(KeyEvent event) {
    
    }

     private void RefrecheTable(List<Circuits> c){
         {
        int column=0;
        int row=1;
        try {
            for(Circuits e:c){

                    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Item.fxml"));
                    AnchorPane pane=fxmlLoader.load();
                    ItemController ic=fxmlLoader.getController();
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
                    grid.setMargin(pane, new Insets(30,30,30,30));
            }
        } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


         

       
    }
    
}
