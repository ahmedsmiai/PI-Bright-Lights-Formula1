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
import entite.Membre;
import entite.Pilote;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import service.EquipeService;
import service.MembreService;


/**
 * FXML Controller class
 *
 * @author nechi
 */
public class MembreController implements Initializable {
    
    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private TextField recherche;

    @FXML
    private ComboBox<String> filtre;

    @FXML
    private ListView<Equipe> Elist;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add equipe list
        EquipeService es=new EquipeService();
        Elist.getItems().addAll(es.read());
        
        filtre.getItems().add("Par pilote");
        MembreService ms=new MembreService();
        Elist.getSelectionModel().select(0);
        Equipe e=Elist.getSelectionModel().getSelectedItem();
        RefreshGrid(ms.readByEquipe(e));
    }
    
    
     @FXML
    public void getMembres(){
        Equipe e=Elist.getSelectionModel().getSelectedItem();
        MembreService ms=new MembreService();
        List<Pilote> pilote=ms.readByEquipe(e);
        RefreshGrid(pilote); 
    }
    
    
    @FXML
    private void ChercherMembre(){
        //refresh
            Elist.getSelectionModel().select(0);
            Equipe e=Elist.getSelectionModel().getSelectedItem();
            MembreService ms=new MembreService();
            List<Pilote> pilote=ms.SearchByName(recherche.getText());
            RefreshGrid(pilote);
    }
    
    
    
    @FXML
    private void Filtration(){
        if(filtre.getSelectionModel().getSelectedItem().equals("Par pilote")){
            MembreService ms=new MembreService();
            
            //Remplissage tableau
        Elist.getSelectionModel().select(0);
        Equipe e=Elist.getSelectionModel().getSelectedItem();
        List<Pilote> pilote=ms.GetOnlyPilote();
        RefreshGrid(pilote); 
        }
    }
    
    
    public void RefreshGrid(List<Pilote> pilotes){
        int column=0;
        int row=1;
        try {
            for(Pilote p:pilotes){
                    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/ItemMembre.fxml"));
                    AnchorPane pane=fxmlLoader.load();
                    ItemMembreController ic=fxmlLoader.getController();
                    ic.setData(p);
                    
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
                    grid.setMargin(pane, new Insets(0,20,20,20));
                   
            }
        } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getEquipe(Equipe e) {
        System.out.println(e.toString2());
       // Elist.getSelectionModel().select(e);

    }


    

    
}
