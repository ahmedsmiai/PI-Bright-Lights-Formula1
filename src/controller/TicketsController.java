/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.java.swing.plaf.windows.resources.windows;
import controller.MesTicketsController;
import controller.UpdateTicketsController;
import entite.Circuits;
import entite.Tickets;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import service.TicketService;
import entite.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import service.circuitService;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class TicketsController implements Initializable {

    @FXML
    private AnchorPane body;
    @FXML
    private TableView<Tickets> table;
    @FXML
    private TableColumn<Tickets, String> nomUser;
    @FXML
    private TableColumn<Tickets, String> nomCourse;
    @FXML
    private TableColumn<Tickets, Date> Datecourse;
    @FXML
    private TableColumn<Tickets, String> type;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField recherche;
    @FXML
    private Button qr;
    private static final User uu = new User();
    windows wn;

    /**
     * Initializes the controller class.
     */
    
    private Stage stage;
    private Scene scene;
    private Parent root;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         RefreshTable();
    }    

    @FXML
    private void OpenUpdateTickets(ActionEvent event) {
         UpdateTicketsController ut = new UpdateTicketsController();
         Tickets c=new Tickets();
        c=table.getSelectionModel().getSelectedItem();
//        System.out.println(c.toString());
         ut.setTickets(c);
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateTickets.fxml"));
            final Node node;
             try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MesTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void DeleteTickets(ActionEvent event) {
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette équipe ?");
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
            TicketService es= new TicketService();
            es.delete(table.getSelectionModel().getSelectedItem().getTickets_id());
            RefreshTable();
    }
    }
        private void RefreshTable(){
        TicketService es=new TicketService();
        
        //RemplissageTableau
        ObservableList<Tickets> tab=FXCollections.observableArrayList();
        tab.addAll(es.read());        
         nomUser.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getUser_id().getUsername()));
        nomCourse.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getCourse().getCourse_nom()));
        Datecourse.setCellValueFactory(celData -> new SimpleObjectProperty<>(celData.getValue().getCourse().getDate()));
        type.setCellValueFactory(new PropertyValueFactory("type"));
        
        table.setItems(tab);
        
    }
   

    @FXML
    private void AfficheQR(ActionEvent event) {
        try {
            TicketService ts = new TicketService();
            ts.readQR(table.getSelectionModel().getSelectedItem().getTickets_id());
            int id=  table.getSelectionModel().getSelectedItem().getTickets_id();
            QRcodeController qrc= new QRcodeController();
            qrc.setQR(id);
//      final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/QRcode.fxml"));
//            final Node node;
//             try {
//            node = fxmlLoader.load();
//            AnchorPane pane=new AnchorPane(node);
//            body.getChildren().setAll(pane);
//        } catch (IOException ex) {
//            Logger.getLogger(MesTicketsController.class.getName()).log(Level.SEVERE, null, ex);
//        }

root = FXMLLoader.load(getClass().getResource("/view/QRcode.fxml"));
//stage = (Stage)((Node)event.getSource()).getScene().getWindow();
stage=new Stage();
scene = new Scene(root);
stage.setScene(scene);
stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
