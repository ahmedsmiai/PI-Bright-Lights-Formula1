/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AjouterTicketsController;
import controller.CourseController;
import entite.Circuits;
import entite.course;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class Item_1Controller implements Initializable {

    @FXML
    private AnchorPane Item;
    @FXML
    private Label nomc;
    @FXML
    private Label datec;
    @FXML
    private Label circuit;
    @FXML
    private ImageView img;
    @FXML
    private ImageView imgg;
    @FXML
    private ImageView imggg;
    
    @FXML
    private Label id;

    private static course co=new course();
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void AcheterTicket() throws IOException {
        TicketService ts = new TicketService();
        AjouterTicketsController ATC = new AjouterTicketsController();
        ATC.setCourse(co); //        System.out.println(c.toString());
       if(ts.countTicketsNTUser(co.getCourse_id(), co)){
           root = FXMLLoader.load(getClass().getResource("/view/AjouterTickets.fxml"));
                     stage=new Stage();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.show();
//        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AjouterTickets.fxml"));
//        final Node node;
//        try {
//            node = fxmlLoader.load();
//            AnchorPane pane = new AnchorPane(node);
//            body.getChildren().setAll(pane);
//        } catch (IOException ex) {
//            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
       else{ Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("date erreur");
                alert.setHeaderText(null);
                alert.setContentText("la date saisie n'est pas valide ");
                alert.showAndWait();}

    }
    
     public void setData(course c) {
         co.setCircuit_circuit_id(c.getCircuit_circuit_id());
         co.setCourse_id(c.getCourse_id());
         co.setCourse_nom(c.getCourse_nom());
         co.setDate(c.getDate());
         co.setSaison_year(c.getSaison_year());
         co.setUsers(c.getUsers());
         Image i=new Image("view/images/circuit_black.png");
         imgg.setImage(i);
         Image ii=new Image("view/images/date.png");
         img.setImage(ii);
         Image iii=new Image("view/images/course_black.png");
         imggg.setImage(iii);
        String pattern = "MM-dd-yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date xx = c.getDate();
        String d = df.format(xx);
        nomc.setText(c.getCourse_nom());
        datec.setText(d);
        circuit.setText(c.getCircuit_circuit_id().getNom());
        String idd=Integer.toString(c.getCourse_id());
        id.setText(idd);
                
    }
    
}
