/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Equipe;
import entite.Membre;
import entite.Pilote;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import service.EquipeService;
import service.MembreService;
import service.PiloteService;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class ModifierMembreController implements Initializable {
    
    

    @FXML
    private AnchorPane body;

    @FXML
    private Button add;

    @FXML
    private Label LabNum;

    @FXML
    private TextField nom;

    @FXML
    private TextField nat;

    @FXML
    private ComboBox<String> ListRole;

    @FXML
    private ComboBox<Equipe> equipe;

    @FXML
    private TextField TextNum;

    @FXML
    private DatePicker date;

    @FXML
    private Button choosefile;

    @FXML
    private Label file;

    @FXML
    private Label myFile;

    @FXML
    private ImageView image;
    
    private static Pilote p=new Pilote();
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Remplisser la liste des équipes
        EquipeService es=new EquipeService();
        for(Equipe e:es.read()){
            equipe.getItems().add(e);
        }
        
        //Remplisser la liste des roles
        ListRole.getItems().add("Chef d'équipe");
        ListRole.getItems().add("Mécanicien");
        ListRole.getItems().add("Pilote");
        
        
        //Remplissez les textfield
        nom.setText(p.getNom());
        ListRole.getSelectionModel().select(p.getRole());
        nat.setText(p.getNationalite());
        equipe.getSelectionModel().select(p.getEquipe());
        Date d=p.getDate_naissance();
        String[] dateString=d.toString().split("-");
        int year=Integer.parseInt(dateString[0]);
        int month=Integer.parseInt(dateString[1]);
        int day=Integer.parseInt(dateString[2]);
        date.setValue(LocalDate.of(year,month,day));
        if(p.getRole().equals("Pilote")){
            LabNum.setVisible(true);
            TextNum.setVisible(true);
            String num=Integer.toString(p.getNumero());
            TextNum.setText(num);
        }else{
            LabNum.setVisible(false);
            TextNum.setVisible(false);
        }
        
        file.setText(this.p.getImage());
        myFile.setText(this.p.getImage());
        image.setImage(this.p.getImg().getImage());
        
        
        
    }    

    @FXML
    private void UpdateMembre() throws IOException{
        Equipe e=equipe.getSelectionModel().getSelectedItem();
        //kenou membre 3adi w raddineh pilote nzidou f table pilote
        if(!(this.p.getRole().equals("Pilote")) && ListRole.getSelectionModel().getSelectedItem().equals("Pilote") ){
            int num=Integer.parseInt(TextNum.getText());
            Pilote pl=new Pilote(this.p.getMembre_id(),num);
            PiloteService ps=new PiloteService();
            ps.insertSeulementPilote(pl);
        }
        if(this.p.getPilote_id() != 0){
            //convert localdate to date
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate ld=date.getValue();
            Date d=Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
            int num=Integer.parseInt(TextNum.getText());
            File f=new File(myFile.getText());
            String image=f.getName();
            Pilote pilote=new Pilote(this.p.getPilote_id(),num,this.p.getMembre_id(),nom.getText(),image,ListRole.getSelectionModel().getSelectedItem(),nat.getText(),d,e);
            PiloteService ps=new PiloteService();
            ps.update(pilote);
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Modification");
            alert.setContentText("Le membre est modifié avec succé");
            alert.show();
            
            //Return to list
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Membre.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        }else{
            //convert localdate to date
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate ld=date.getValue();
            Date d=Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
            File f=new File(myFile.getText());
            String image=f.getName();
            Membre m=new Membre(this.p.getMembre_id(),nom.getText(),image,ListRole.getSelectionModel().getSelectedItem(),nat.getText(),d,e);
            MembreService ms=new MembreService();
            ms.update(m);
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Modification");
            alert.setContentText("Le membre est modifié avec succé");
            alert.show();
            
            
            //Return to list
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Membre.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane=new AnchorPane(node);
            body.getChildren().setAll(pane);
        }
        
    }
    
    @FXML
    private void ShowNumero(ActionEvent event){
        String r=ListRole.getSelectionModel().getSelectedItem();
        if(r.equals("Pilote")){
            //Show Numero
            LabNum.setVisible(true);
            TextNum.setVisible(true);
        }else{
            //Hide Numero
            LabNum.setVisible(false);
            TextNum.setVisible(false);
        }
    }
    
    
    @FXML
    private void ChooseFile() throws FileNotFoundException{
        try {
            String filename;
            JFileChooser chooser= new JFileChooser();
            chooser.showOpenDialog(null);
            File f=chooser.getSelectedFile();
            filename = f.getAbsolutePath();
            file.setText(filename);
            Image img = new Image(new FileInputStream(filename));
            image.setImage(img);
            //Déplacer l'image
            String newPath="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\images\\membre\\";
            File sourceFile=null;
            File destinationFile=null;
            String nFile = f.getName();
            myFile.setText(nFile);
            sourceFile= new File(filename);
            destinationFile=new File(newPath+nFile);
            if(!destinationFile.exists()){
               Files.copy(sourceFile.toPath() , destinationFile.toPath()); 
            }else{
                Alert alert=new Alert(AlertType.ERROR);
                alert.setContentText("Image déjà existe");
                alert.showAndWait();
            }
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setMembre(Pilote p) {
        this.p.setPilote_id(p.getPilote_id());
        this.p.setNumero(p.getNumero());
        this.p.setMembre_id(p.getMembre_id());
        this.p.setNom(p.getNom());
        this.p.setImage(p.getImage());
        this.p.setImg(p.getImg());
        this.p.setRole(p.getRole());
        this.p.setNationalite(p.getNationalite());
        this.p.setDate_naissance(p.getDate_naissance());
        this.p.setEquipe(p.getEquipe());
    }
    
}
