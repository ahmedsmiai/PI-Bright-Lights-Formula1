/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Circuits;
import entite.*;
import java.io.IOException;
import service.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.sql.Timestamp;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class AjouterTicketsController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private ImageView image;
    @FXML
    private Label myFile;
    @FXML
    private ComboBox<String> Type;
    private static User u = new User();
    private static course cs = new course();
    @FXML
    private AnchorPane body;
    @FXML
    private Label prix;

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Type.getItems().add("VIP");
        Type.getItems().add("LOGUE");
        Type.getItems().add("TRIBUNE");
        Type.getItems().add("VIRAGE DROITE");
        Type.getItems().add("VIRAGE GAUCHE");
        
    } 
    
    
    @FXML
    private void afficherprix(){
        
           }
    
   
    @FXML
    private void hundleButtonAction(ActionEvent event) {
        UserService us=new UserService();
        User userc=us.getUserConnected();
        String r = Type.getSelectionModel().getSelectedItem();
        Tickets t;
        t = new Tickets(cs, userc,r);
        TicketService ts=new TicketService();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de Reservation Tickets");
        alert.setContentText("Le prix de votre ticket est  :  "+ts.AfficherPrix(r));
        Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
        
        ts.insert(t);
        }
        
        System.out.println("ajouterticketcontroller logne 90 "+t.getTickets_id());
//         final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Course.fxml"));
//        final Node node;
//        try {
//            node = fxmlLoader.load();
//            AnchorPane pane=new AnchorPane(node);
//            body.getChildren().setAll(pane);
//        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
          
    }
    public void setCourse(course e) {
        cs.setCourse_nom(e.getCourse_nom());
        cs.setCourse_id(e.getCourse_id());
        cs.setCircuit_circuit_id(e.getCircuit_circuit_id());
        cs.setDate(e.getDate());
        cs.setSaison_year(e.getSaison_year());
            }
    
}
