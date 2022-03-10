/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entite.Circuits;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class ItemController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label pays;
    @FXML
    private Label longeur;
    @FXML
    private Label distance;
    @FXML
    private Label capacite;
    @FXML
    private Label description;
    @FXML
    private AnchorPane Item;
    private static Circuits c = new Circuits();
    @FXML
    private ImageView idis;
    @FXML
    private ImageView icap;
    @FXML
    private ImageView idesc;
    @FXML
    private ImageView inon;
    @FXML
    private ImageView ip;
    @FXML
    private ImageView ilong;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Circuits crs) {
        
        Image i=new Image("view/images/spec.png");
         icap.setImage(i);
         Image ii=new Image("view/images/pays.png");
         ip.setImage(ii);
         Image iii=new Image("view/images/circuit_black.png");
         inon.setImage(iii);
         Image iiii=new Image("view/images/longeur.png");
         ilong.setImage(iiii);
                 Image iiiii=new Image("view/images/desc.jpg");
         idesc.setImage(iiiii);
         Image iiiiii=new Image("view/images/cap.jpg");
         idis.setImage(iiiiii);
        nom.setText(crs.getNom());
        pays.setText(crs.getPays());
        String s=Integer.toString(crs.getLang());
        longeur.setText(s );
        distance.setText(Integer.toString(crs.getCourse_distance()));
        capacite.setText(Integer.toString(crs.getCapacite()));
        description.setText(crs.getDescription());
        
    }
        
        
        
    
}
