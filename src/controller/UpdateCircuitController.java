/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Circuits;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import service.circuitService;

/**
 * FXML Controller class
 *
 * @author manaa
 */
public class UpdateCircuitController implements Initializable {

    @FXML
    private Label LabNum;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfCoursedistance;
    @FXML
    private TextField tfCapacite;
    @FXML
    private Button add;
    @FXML
    private TextField tfpays;
    @FXML
    private TextField tflongeur;
    @FXML
    private TextArea tadesc;
    private static Circuits c = new Circuits();
    @FXML
    private ImageView imgNom;
    @FXML
    private ImageView imgPays;
    @FXML
    private ImageView ImgCourse;
    @FXML
    private ImageView ImgCap;
    @FXML
    private ImageView ImgLong;
    
    
    Image tic=new Image("/view/images/true.jpg");
    Image notic=new Image("/view/images/false.png");
    private boolean controlnom;
    private boolean controlpays;
    private boolean controlcourse;
    private boolean controlcap;
    private boolean controllong;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO tfnom.setText(c.getNom());
        controlnom=false;
    controlpays=false;
    controlcourse=false;
    controlcap=false;
    controllong=false;
        
    String cap=Integer.toString(this.c.getCapacite());
    tfCapacite.setText(cap);
    
    String course=Integer.toString(this.c.getCourse_distance());
    tfCoursedistance.setText(course);
    
    String l=Integer.toString(this.c.getLang());
    tflongeur.setText(l);
    
    tfnom.setText(this.c.getNom());
    tfpays.setText(this.c.getPays());
    tadesc.setText(this.c.getDescription());
    
        
    }    

    @FXML
    private void UpdateCircuit(ActionEvent event) {
        Circuits
        c1 = new Circuits(c.getCircuit_id(),tfnom.getText(),tfpays.getText(),Integer.parseInt(tflongeur.getText()),Integer.parseInt(tfCoursedistance.getText()),tadesc.getText(),Integer.parseInt(tfCapacite.getText()));
        circuitService cs=new circuitService();
        cs.update(c1);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("le circuit est Modifier avec succé");
        alert.show();
    }
    
    
    public void setCircuit(Circuits e) {
        this.c.setCircuit_id(e.getCircuit_id());
        this.c.setNom(e.getNom());
        this.c.setCapacite(e.getCapacite());
        this.c.setCourse_distance(e.getCourse_distance());
        this.c.setLang(e.getLang());
        this.c.setPays(e.getPays());
        this.c.setDescription(e.getDescription());
        System.out.println(e.toString());    }

    
    @FXML
    private void verifnom(KeyEvent event) {
         if(tfnom.getText().length() < 6){
            controlnom=false;
            
            imgNom.setImage(notic);
        }else{
            controlnom=true;
            
            imgNom.setImage(tic);
        }
        
    }

    @FXML
    private void verifcd(KeyEvent event) {
        String s=Integer.toString(Integer.parseInt(tfCoursedistance.getText()));
        if(s.length() < 3){
            controlcourse=false;
            
            ImgCourse.setImage(notic);
        }else{
            controlcourse=true;
            
            ImgCourse.setImage(tic);
        }
    }

    @FXML
    private void verifdesc(KeyEvent event) {
        String s=Integer.toString(Integer.parseInt(tfCapacite.getText()));
        if(s.length() < 3){
            controlcap=false;
            
            ImgCap.setImage(notic);
        }else{
            controlcap=true;
            
            ImgCap.setImage(tic);
        }
        
       
        
    }

    @FXML
    private void verifpays(KeyEvent event) {
                 if(tfpays.getText().length() < 4){
            controlpays=false;
            
            imgPays.setImage(notic);
        }else{
            controlpays=true;
            
            imgPays.setImage(tic);
        }
    }

    @FXML
    private void veriflong(KeyEvent event) {
         String s=Integer.toString(Integer.parseInt(tflongeur.getText()));
         if(s.length() < 3){
            controllong=false;
            
            ImgLong.setImage(notic);
        }else{
            controllong=true;
            
            ImgLong.setImage(tic);
        }
         
        

    }
    
}
