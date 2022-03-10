/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entite.ClassementPilotes;
import entite.Saison;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import service.ClassementPilotesService;
import service.SaisonService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatEvenetController implements Initializable {

    @FXML
    private PieChart myPiechart;
    @FXML
    private Label caption;
    @FXML
    private Label numstat;
    int nbrtotale=0;
    double st;
    @FXML
    private Rectangle rectangle;
    @FXML
    private ComboBox<Saison> choisYear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        

     SaisonService ss = new SaisonService();
        choisYear.getItems().addAll(ss.readjustid());
        Saison s =  choisYear.getSelectionModel().getSelectedItem();
        int year = s.getYear();
        System.out.println(year);

    }    
    
    @FXML 
    void choosY (){
        
        int year =  choisYear.getSelectionModel().getSelectedItem().getYear();
        stat (year);
    }
    void stat ( int year ){
        
                
            ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        ClassementPilotesService ev = new ClassementPilotesService();
        
        List<ClassementPilotes>  ListeDesEvent = ev.readbys(year);
       for(ClassementPilotes e: ListeDesEvent){
           pieChartData.add(new Data(e.getNom(),e.getPoints_total()));
           nbrtotale+=e.getPoints_total();
       }
        myPiechart.setData(pieChartData);
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : myPiechart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(Math.round(data.getPieValue())));
                numstat.setText(String.valueOf((data.getPieValue()/nbrtotale)*100 + " %"));
                System.out.println(nbrtotale);
               
             }
        });
    
}

        
        
    }
}
