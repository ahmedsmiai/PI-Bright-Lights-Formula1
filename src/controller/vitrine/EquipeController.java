/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

/**
 *
 * @author nechi
 */

import controller.DashboardController;
import entite.Equipe;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import service.EquipeService;


/**
 * FXML Controller class
 *
 * @author nechi
 */
public class EquipeController implements Initializable {
    
    @FXML
    private AnchorPane body;

    @FXML
    private GridPane grid;
    
     @FXML
    private ScrollPane scroll;
     
       @FXML
    private TextField recherche;

    @FXML
    private ComboBox<String> filtre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filtre.getItems().add("Par pays");
        EquipeService es=new EquipeService();
        List<Equipe> equipes=es.read();
        RefreshGrid(equipes);
    }
    
    @FXML
    public void ChercherEquipe(){
        EquipeService es=new EquipeService();
        RefreshGrid(es.SearchByName(recherche.getText()));
    }
    
    
    @FXML
    public void Filtrer(){
        String clef=filtre.getSelectionModel().getSelectedItem();
        EquipeService es=new EquipeService();
        RefreshGrid(es.filtrer(clef));
    }
    
    public void RefreshGrid(List<Equipe> equipes){
        int column=0;
        int row=1;
        try {
            for(Equipe e:equipes){

                    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/Item.fxml"));
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
                    grid.setMargin(pane, new Insets(20));
            }
        } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    

    
}
