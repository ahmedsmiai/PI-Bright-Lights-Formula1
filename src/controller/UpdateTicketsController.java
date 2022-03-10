/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Tickets;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class UpdateTicketsController implements Initializable {

    @FXML
    private AnchorPane body;
    @FXML
    private Button add;
    @FXML
    private ImageView image;
    @FXML
    private Label myFile;
    @FXML
    private ComboBox<String> Type;
    @FXML
    private Label prix;
    private static final Tickets t = new Tickets();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Type.getItems().add("VIP");
        Type.getItems().add("LOGUE");
        Type.getItems().add("TRIBUNE");
        Type.getItems().add("VIRAGE DROITE");
        Type.getItems().add("VIRAGE GAUCHE");
        prix.setVisible(false);
        // TODO
    }    

    @FXML
    private void hundleButtonAction(ActionEvent event) {
        String r = Type.getSelectionModel().getSelectedItem();
        
        
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de Reservation Tickets");
        alert.setContentText("Voulez-vous vraiment Reserver cette tickets ?");
         Optional<ButtonType> action=alert.showAndWait();
        if(action.get() == ButtonType.OK){
        TicketService ts=new TicketService();
        ts.update(r, t);
        };
        
    }
     public void setTickets(Tickets e) {
        t.setTickets_id(e.getTickets_id());
        t.setCourse(e.getCourse());
        t.setUser_id(e.getUser_id());
        t.setType(e.getType());
        
            }
}
