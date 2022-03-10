/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

import entite.Equipe;
import entite.Membre;
import entite.Pilote;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.MembreService;

/**
 *
 * @author nechi
 */

public class ItemMembreController implements Initializable{

    @FXML
    private Label nom;

    @FXML
    private Label role;

    @FXML
    private Label nat;

    @FXML
    private Label date;

    @FXML
    private Label num;
    
     @FXML
    private ImageView image;
     @FXML
    private Label numLAb;

    private Pilote pilote;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    
    public void setData(Pilote p){
        String path="/view/images/membre/"+p.getImage();
        this.pilote=p;
        Image img=new Image(getClass().getResourceAsStream(path));
        image.setImage(img);
        nom.setText(p.getNom());
        role.setText(p.getRole());
        nat.setText(p.getNationalite());
        date.setText(p.getDate_naissance().toString());
        if(p.getNumero() > 0){
            String nbr=Integer.toString(p.getNumero());
            num.setText(nbr);
        }else{
            num.setVisible(false);
            numLAb.setVisible(false);
        }
        
    }
    
    
    @FXML
    public void GetEquipe(){
        Membre m=new Membre(this.pilote.getMembre_id(),this.pilote.getNom(),this.pilote.getImage(),this.pilote.getRole(),this.pilote.getNationalite(),this.pilote.getDate_naissance());
        MembreService ms=new MembreService();
        Equipe e=ms.getEquipe(m);
        MembreController mc=new MembreController();
        mc.getEquipe(e);
    }

}
