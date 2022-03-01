/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Saison;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.SaisonService;

/**
 * FXML Controller class
 *
 * @author qwiw
 */
public class CSguiController implements Initializable {

    @FXML
    private TextField textfieldYear;
    @FXML
    private Button btnAs;
    @FXML
    private DatePicker calenderDd;
    @FXML
    private DatePicker calenderDf;
//private SaisonService saisonService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // this.saisonService = new SaisonService();
    }    

    @FXML
    private void onCreate(ActionEvent event) {
      
        int year = Integer.parseInt(textfieldYear.getText());
    
        LocalDate datDeb = calenderDd.getValue();
        
        LocalDate datFin = calenderDf.getValue();
        
        Saison s = new Saison(year , datDeb, datFin) ;

       SaisonService ss=new SaisonService();   
       ss.inserSaisonPst(s);
    }
    
}
