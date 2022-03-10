/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entite.Equipe;
import entite.Membre;
import entite.Pilote;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import service.EquipeService;
import service.MembreService;
import service.PiloteService;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class MembreController implements Initializable {

    @FXML
    private AnchorPane body;
    
     @FXML
    private ListView<Equipe> eqlist;
     
     @FXML
     private ComboBox filtre;
    
    @FXML
    private TableView<Pilote> table;
    @FXML
    private TableColumn<Pilote,Integer> id;
    @FXML
    private TableColumn<Pilote,ImageView> image;
    @FXML
    private TableColumn<Pilote,String> nom;
    @FXML
    private TableColumn<Pilote,String> nat;
    @FXML
    private TableColumn<Pilote,String> role;
    @FXML
    private TableColumn<Pilote,Date> date;
    @FXML
    private TableColumn<Pilote,Integer> nm;
    
    
    @FXML
    private TextField recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //remplir filre
        filtre.getItems().add("Par pilote");
        //Remplisser la liste des équipes
        EquipeService es=new EquipeService();
        for(Equipe e:es.read()){
            eqlist.getItems().add(e);
        }
        
        
        //refresh
            eqlist.getSelectionModel().select(0);
            Equipe e=eqlist.getSelectionModel().getSelectedItem();
            MembreService ms=new MembreService();
            List<Pilote> pilote=ms.readByEquipe(e);
            RefreshTable(pilote);
            
    }
    
    
    @FXML
    private void Filtration(){
        if(filtre.getSelectionModel().getSelectedItem().equals("Par pilote")){
            MembreService ms=new MembreService();
            
            //Remplissage tableau
        eqlist.getSelectionModel().select(0);
        Equipe e=eqlist.getSelectionModel().getSelectedItem();
        List<Pilote> pilote=ms.GetOnlyPilote();
        RefreshTable(pilote); 
        }
    }
    
     @FXML
    public void getMembres(){
        Equipe e=eqlist.getSelectionModel().getSelectedItem();
        MembreService ms=new MembreService();
        List<Pilote> pilote=ms.readByEquipe(e);
        RefreshTable(pilote); 
    }
    
    
    @FXML
    public void getEquipe(){
        MembreService ms=new MembreService();
        Membre m=table.getSelectionModel().getSelectedItem();
        Equipe e=ms.getEquipe(m);
       eqlist.getSelectionModel().select(e);
    }
    
    @FXML
    public void OpenAddMembre(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AjouterMembre.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void DeleteMembre(){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette équipe ?");
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
            PiloteService es= new PiloteService();
            es.delete(table.getSelectionModel().getSelectedItem().getMembre_id());
            
            //refresh
            eqlist.getSelectionModel().select(0);
            Equipe e=eqlist.getSelectionModel().getSelectedItem();
            MembreService ms=new MembreService();
            List<Pilote> pilote=ms.readByEquipe(e);
            RefreshTable(pilote);
        }
        
    }
    
    
    
    
    private void RefreshTable(List<Pilote> pilote){
       
        
        //Remplissage tableau
        ObservableList<Pilote> tab=FXCollections.observableArrayList();
        tab.addAll(pilote);
            id.setCellValueFactory(new PropertyValueFactory("membre_id"));
            image.setCellValueFactory(new PropertyValueFactory("img"));
            nom.setCellValueFactory(new PropertyValueFactory("nom"));
            role.setCellValueFactory(new PropertyValueFactory("role"));
            nat.setCellValueFactory(new PropertyValueFactory("nationalite"));
            date.setCellValueFactory(new PropertyValueFactory("date_naissance"));
            nm.setCellValueFactory(new PropertyValueFactory("numero"));
            table.setItems(tab); 
            table.refresh();
    }

    @FXML
    private void ChercherMembre(){
        //refresh
            eqlist.getSelectionModel().select(0);
            Equipe e=eqlist.getSelectionModel().getSelectedItem();
            MembreService ms=new MembreService();
            List<Pilote> pilote=ms.SearchByName(recherche.getText());
            RefreshTable(pilote);
    }
    
    
    @FXML
    private void OpenUpdateMembre() throws IOException{
        ModifierMembreController mec=new ModifierMembreController();
        Pilote p=new Pilote();
        p=table.getSelectionModel().getSelectedItem();
        //get équipe with id membre and add to pilote
        Membre m=new Membre(p.getMembre_id(),p.getNom(),p.getImage(),p.getRole(),p.getNationalite(),p.getDate_naissance());
        MembreService ms=new MembreService();
        Equipe e=ms.getEquipe(m);
        p.setEquipe(e);
        
        //end get equipe
        mec.setMembre(p);
         //Get ressource
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ModifierMembre.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
    }
}
