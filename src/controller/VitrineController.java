/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author nechi
 */
public class VitrineController implements Initializable{
    
    @FXML
    private AnchorPane body;
    
    @FXML
    private HBox menu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void OpenRes(){
       final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VitrineResultatSaison.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML void OpenPart(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/VitrineParticipation.fxml"));
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
    private void OpenEquipe(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/Equipe.fxml"));
        final Node node;
        try {
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
