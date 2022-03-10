/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author nechi
 */
import entite.Equipe;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import service.EquipeService;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class EquipeController implements Initializable {
    @FXML
    private TableView<Equipe> table;
    @FXML
    private TableColumn<Equipe,Integer> Cid;
    @FXML
    private TableColumn<Equipe,String> Cnom;
    @FXML
    private TableColumn<Equipe,ImageView> Clogo;
    @FXML
    private TableColumn<Equipe,String> Cvoiture;
    @FXML
    private TableColumn<Equipe,String> Cpays;
    @FXML
    private TableColumn<Equipe,Button> actions;
    @FXML
    public AnchorPane body;
    
    @FXML
    public TextField recherche;
    
    @FXML
    private Button pdf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        RefreshTable();
    }
    
    
    @FXML
    private void ChercherEquipe(){
       EquipeService es=new EquipeService();
       ObservableList<Equipe> tab=FXCollections.observableArrayList();
       tab.addAll(es.SearchByName(recherche.getText()));
       Cid.setCellValueFactory(new PropertyValueFactory("equipe_id"));
        Cnom.setCellValueFactory(new PropertyValueFactory("nom"));
        Clogo.setCellValueFactory(new PropertyValueFactory("img"));
        Cvoiture.setCellValueFactory(new PropertyValueFactory("voiture"));
        Cpays.setCellValueFactory(new PropertyValueFactory("pays_origin"));
        table.setItems(tab);
    }
    
    
    @FXML
    private void GeneratePdf() throws IOException{
        double x=Math.random() * ( 100 - 999 );
        String xchar=Double.toString(x);
        String name="Equipe"+xchar;
        EquipeService es=new EquipeService();
        es.GeneratePdf(name);
        Notifications not=Notifications.create()
                .title("Succé")
                .text("Votre fichier pdf est enregistré dans le dossier documents\n sous le nom"+name+".pdf")
                .hideCloseButton()
                .position(Pos.TOP_RIGHT);
                not.darkStyle();
                not.showInformation();
        String path="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\documents\\" + name + ".pdf";
        File f=new File(path);
        Desktop.getDesktop().open(f);
    }
    
    
    @FXML
    private void DeleteEquipe(){
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette équipe ? \n Si tu veux supprimer cet équipe tous ses membres va étre supprimé");
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
            EquipeService es= new EquipeService();
            es.delete(table.getSelectionModel().getSelectedItem().getEquipe_id());
            RefreshTable();
            
            
            Notifications not=Notifications.create()
                .title("Succé")
                .text("L'équipe supprimé avec succés")
                .hideCloseButton()
                .position(Pos.TOP_RIGHT);
                not.darkStyle();
                not.showInformation();
        }
        
        
        
    }
    
    private void RefreshTable(){
        EquipeService es=new EquipeService();
        
        //RemplissageTableau
        ObservableList<Equipe> tab=FXCollections.observableArrayList();
        tab.addAll(es.readWithImageView());        
        Cid.setCellValueFactory(new PropertyValueFactory("equipe_id"));
        Cnom.setCellValueFactory(new PropertyValueFactory("nom"));
        Clogo.setCellValueFactory(new PropertyValueFactory("img"));
        Cvoiture.setCellValueFactory(new PropertyValueFactory("voiture"));
        Cpays.setCellValueFactory(new PropertyValueFactory("pays_origin"));
        table.setItems(tab);
    }
    
    //Method page équipe
    @FXML 
    private void OpenAddEquipe() throws IOException{
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AjouterEquipe.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
    }
    
    
    @FXML
    private void OpenUpdateEquipe() throws IOException{
        ModifierEquipeController mec=new ModifierEquipeController();
        Equipe e=new Equipe();
        e=table.getSelectionModel().getSelectedItem();
        mec.setEquipe(e);
         //Get ressource
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ModifierEquipe.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
    }
    
}
