/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.DocumentException;
import entite.ClassementEquipes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import entite.Saison;
import java.sql.Date;
import java.time.LocalDate;
import service.SaisonService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ClassementEquipesService;
import entite.Membre;
import java.awt.Desktop;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class CequipeController implements Initializable {

    @FXML
    private AnchorPane listedesComptes;
    @FXML
    private TableView<ClassementEquipes> tableRead;
//    private TableColumn<ClassementEquipes, Integer> colonneYear;
//    private TableColumn<ClassementEquipes, Integer> colonneDatedeb;
//    private TableColumn<ClassementEquipes, Integer> colonneDatefin;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExcel1;

    @FXML
    private TableColumn<ClassementEquipes, Integer> colonnepts;
    @FXML
    private TableColumn<ClassementEquipes, Integer> colonnecpid;
    @FXML
    private TableColumn<ClassementEquipes, Integer> colonnesy;
    @FXML
    private TableColumn<ClassementEquipes, String> colonnepid;//// nom
    @FXML
    private TableColumn<ClassementEquipes, Integer> colonnepos;
    public ObservableList<ClassementEquipes> list = FXCollections.observableArrayList();
    private ClassementEquipesService s;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private RadioButton rbID;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rbS;
    @FXML
    private RadioButton rbN;
    @FXML
    private RadioButton rbP;
ClassementEquipesService cc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClassementEquipesService cc = new ClassementEquipesService();
        this.s = new ClassementEquipesService();
        //    cc.findAll();
        cc.position();
        RefreshTable();
    }

    private void RefreshTable() {
        ObservableList<ClassementEquipes> list = FXCollections.observableArrayList();
        list.addAll(s.read2());
        colonnecpid.setCellValueFactory(new PropertyValueFactory("classementE_id"));
        colonnesy.setCellValueFactory(new PropertyValueFactory("saisons_year"));
          colonnepid.setCellValueFactory(new PropertyValueFactory("nom"));
        
        

        
        
                colonnepts.setCellValueFactory(new PropertyValueFactory("points_total"));
      //  colonnepts.setCellValueFactory(new PropertyValueFactory("equipes_equipe_id"));
        colonnepos.setCellValueFactory(new PropertyValueFactory("position"));

        tableRead.setItems(list);
        s.read2();
    }

    @FXML
    private void onCreate(ActionEvent event) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addclassementE.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
        listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onUpdate(ActionEvent event) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateClassementP.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
        listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onExcel(ActionEvent event){
          ClassementEquipesService pp = new ClassementEquipesService();
        try {
            pp.GeneratePdf();
        } catch (DocumentException ex) {
            Logger.getLogger(CequipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    @FXML
    private void onDelete(ActionEvent event) {
        System.out.println("delete equipe controller");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer ce compte ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            ClassementEquipes p = tableRead.getSelectionModel().getSelectedItem();
            int id = p.getClassementE_id();
            ClassementEquipes y = new ClassementEquipes(id);
            ClassementEquipesService us = new ClassementEquipesService();

            us.delete(us.readById(id));
            RefreshTable();
        }
    }

    @FXML
    private void switchToClassement(ActionEvent event) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/classement.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
        listedesComptes.getChildren().setAll(pane);
    }

    @FXML
    private void onStat(ActionEvent event) {
                 FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../view/statE.fxml"));
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
    private void getTri(ActionEvent event) {

        if (rbID.isSelected()) {
            ObservableList<ClassementEquipes> list = FXCollections.observableArrayList();
            list.addAll(s.triID());
            colonnecpid.setCellValueFactory(new PropertyValueFactory("classementE_id"));
            colonnesy.setCellValueFactory(new PropertyValueFactory("saisons_year"));
         //   colonnepid.setCellValueFactory(new PropertyValueFactory("equipes_equipe_id"));
          colonnepid.setCellValueFactory(new PropertyValueFactory("nom"));
            colonnepts.setCellValueFactory(new PropertyValueFactory("points_total"));
            colonnepos.setCellValueFactory(new PropertyValueFactory("position"));

            tableRead.setItems(list);
            s.triID();

        } else if (rbS.isSelected()) {
            ObservableList<ClassementEquipes> list = FXCollections.observableArrayList();
            list.addAll(s.triSaison());
            colonnecpid.setCellValueFactory(new PropertyValueFactory("classementE_id"));
            colonnesy.setCellValueFactory(new PropertyValueFactory("saisons_year"));
        //    colonnepid.setCellValueFactory(new PropertyValueFactory("equipes_equipe_id"));
         colonnepid.setCellValueFactory(new PropertyValueFactory("nom"));
            colonnepts.setCellValueFactory(new PropertyValueFactory("points_total"));
            colonnepos.setCellValueFactory(new PropertyValueFactory("position"));

            tableRead.setItems(list);
            s.triSaison();

        } else if (rbN.isSelected()) {
            ObservableList<ClassementEquipes> list = FXCollections.observableArrayList();
            list.addAll(s.triPilote());
            colonnecpid.setCellValueFactory(new PropertyValueFactory("classementE_id"));
            colonnesy.setCellValueFactory(new PropertyValueFactory("saisons_year"));
        //    colonnepid.setCellValueFactory(new PropertyValueFactory("equipes_equipe_id"));
         colonnepid.setCellValueFactory(new PropertyValueFactory("nom"));
            colonnepts.setCellValueFactory(new PropertyValueFactory("points_total"));
            colonnepos.setCellValueFactory(new PropertyValueFactory("position"));

            tableRead.setItems(list);
            s.triPilote();

        } else if (rbP.isSelected()) {
            ObservableList<ClassementEquipes> list = FXCollections.observableArrayList();
            list.addAll(s.triPos());
            colonnecpid.setCellValueFactory(new PropertyValueFactory("classementE_id"));
            colonnesy.setCellValueFactory(new PropertyValueFactory("saisons_year"));
           // colonnepid.setCellValueFactory(new PropertyValueFactory("equipes_equipe_id"));
            colonnepid.setCellValueFactory(new PropertyValueFactory("nom"));
            colonnepts.setCellValueFactory(new PropertyValueFactory("points_total"));
            colonnepos.setCellValueFactory(new PropertyValueFactory("position"));

            tableRead.setItems(list);
            s.triPos();

        }

    }

}