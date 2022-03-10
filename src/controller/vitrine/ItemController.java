/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

/**
 *
 * @author nechi
 */

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import entite.Equipe;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class ItemController implements Initializable {
      @FXML
    private AnchorPane item;

    @FXML
    private ImageView image;
    
    @FXML
    private Label nom;

    @FXML
    private Label voiture;

    @FXML
    private Label pays;
    
     @FXML
    private ImageView flag;
    
    private static Equipe equipe=new Equipe();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }

    public void setData(Equipe e) {
        String path="/view/images/"+e.getLogo();
        this.equipe=e;
        Image img=new Image(getClass().getResourceAsStream(path));
        image.setImage(img);
        nom.setText(e.getNom());
        voiture.setText(e.getVoiture());
        pays.setText(e.getPays_origin());
        
        
        //set flag
          try {
              HttpResponse<String> response = Unirest.get("https://country-flags.p.rapidapi.com/png/"+this.equipe.getPays_origin())
                      .header("x-rapidapi-host", "country-flags.p.rapidapi.com")
                      .header("x-rapidapi-key", "6374605caamsh91683f4665ad66bp1b3d43jsn758cb99682fb")
                      .asString();
              
              Image img2=new Image(response.getRawBody());
            flag.setImage(img2);
          } catch (UnirestException ex) {
              Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    

        
    
}
    
    
