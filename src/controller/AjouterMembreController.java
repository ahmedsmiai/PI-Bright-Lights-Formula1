/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.StringUtils;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import service.EquipeService;
import service.MembreService;
import service.PiloteService;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class AjouterMembreController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField role;
    @FXML
    private TextField nat;
    @FXML
    private DatePicker date;
    @FXML
    private Button add;
    
    @FXML
    private ComboBox<Equipe> equipe;
    
    @FXML
    private ComboBox<String> ListRole;
    
    
    @FXML
    private Label LabNum;
    
    @FXML
    private TextField TextNum;
    
    @FXML
    private Label file;
    @FXML
    private Label myFile;
    @FXML
    private ImageView image;
    
    //déclaration control saisie
    @FXML
    private ImageView ImgNom;

    @FXML
    private Label textNom;

    @FXML
    private ImageView ImgRole;

    @FXML
    private Label textRole;

    @FXML
    private ImageView ImgNat;

    @FXML
    private Label textNat;

    @FXML
    private ImageView ImgFile;

    @FXML
    private Label textFile;

    @FXML
    private ImageView ImgDate;

    @FXML
    private Label textDate;

    @FXML
    private ImageView ImgEquipe;

    @FXML
    private Label textEquipe;

    @FXML
    private ImageView ImgNum;

    @FXML
    private Label textNum;
    
    private boolean controlnom;
    private boolean controlrole;
    private boolean controlnat;
    private boolean controlfile;
    private boolean controldate;
    private boolean controlequipe;
    private boolean controlnum;
    
    Image tic=new Image("/view/images/tic.jpg");
    Image notic=new Image("/view/images/notic.png");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialiser control false
        controlnom=false;
        controlrole=false;
        controlnat=false;
        controlfile=false;
        controldate=false;
        controlequipe=false;
        controlnum=false;
        
        //Remplisser la liste des équipes
        EquipeService es=new EquipeService();
        for(Equipe e:es.read()){
            equipe.getItems().add(e);
        }
        
        //Remplisser la liste des roles
        ListRole.getItems().add("Chef d'équipe");
        ListRole.getItems().add("Mécanicien");
        ListRole.getItems().add("Pilote");
        
        //Hide Numero
        LabNum.setVisible(false);
        TextNum.setVisible(false);
        ImgNum.setVisible(false);
        
    }
    
    @FXML
    void VerifEquipe(){
        if(equipe.getSelectionModel().getSelectedIndex()==-1){
            controlequipe=false;
            textEquipe.setText("Choix d'équipe obligatoire");
            textEquipe.setVisible(true);
            ImgEquipe.setImage(notic);
        }else{
            controlequipe=true;
            textEquipe.setVisible(false);
            ImgEquipe.setImage(tic);
        }
    }
    
    @FXML
    void VerifDate(){
       int Myear= date.getValue().getYear();
       Date d=new Date();
       Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        int year = calendar.get(Calendar.YEAR);
       if(Myear >= year-20){
            controldate=false;
            textDate.setText("Le membre est trés petit");
            textDate.setVisible(true);
            ImgDate.setImage(notic);
       }else{
           controldate=true;
            textDate.setVisible(false);
            ImgDate.setImage(tic);
        }
    }
    
     @FXML
    void VerifNat() {
        if(nat.getText().length() < 1){
            controlnat=false;
            textNat.setText("Le champs Nationalité est obligatoire");
            textNat.setVisible(true);
            ImgNat.setImage(notic);
        }else{
            controlnat=true;
            textNat.setVisible(false);
            ImgNat.setImage(tic);
        }
    }

    @FXML
    void VerifNom() {
        if(nom.getText().length() < 6){
            controlnom=false;
            textNom.setText("Minimum 6 caractéres");
            textNom.setVisible(true);
            ImgNom.setImage(notic);
        }else{
            controlnom=true;
            textNom.setVisible(false);
            ImgNom.setImage(tic);
        }
    }
    
     @FXML
    void VerifNum() {
        //String num=TextNum.getText();
        if(TextNum.getText().length() < 1 && ListRole.getSelectionModel().getSelectedItem().equals("Pilote")){
            controlnum=false;
            textNum.setText("Le champ numero est obligatoire");
            textNum.setVisible(true);
            ImgNum.setImage(notic);
        }else{
            controlnum=true;
            textNum.setVisible(false);
            ImgNum.setImage(tic);
        }
    }
    
    
    @FXML
    private void ChooseFile() throws FileNotFoundException{
        try {
            String filename;
            JFileChooser chooser= new JFileChooser();
            chooser.showOpenDialog(null);
            File f=chooser.getSelectedFile();
            if(f == null){
                controlfile=false;
                textFile.setText("Le champ image est obligatoire");
                textFile.setVisible(true);
                ImgFile.setImage(notic);
            }else{
                controlfile=true;
                textFile.setText("Champ image obligatoire");
                textFile.setVisible(false);
                ImgFile.setImage(tic);
               filename = f.getAbsolutePath();
                file.setText(filename);
                Image img = new Image(new FileInputStream(filename));
                image.setImage(img);
                image.setVisible(true);
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
                    controlfile=false;
                    textFile.setVisible(false);
                    ImgFile.setImage(notic);
                    Notifications not=Notifications.create()
                    .title("Warning")
                    .text("Image déjà existe")
                    .hideCloseButton()
                    .position(Pos.TOP_RIGHT);
                    not.darkStyle();
                    not.showError();
                    
                    controlfile=false;
                    textFile.setText("Changer l'image");
                    textFile.setVisible(true);
                    ImgFile.setImage(notic);
                }
                
            }
            
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void AjouterMembre(ActionEvent event) throws ParseException {
        
        if(controlrole && controlnom && controlnat && controlfile && controldate && controlequipe && controlnum  ){
            String photo=myFile.getText();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Equipe ep= equipe.getSelectionModel().getSelectedItem();
            String r=ListRole.getSelectionModel().getSelectedItem();
            if(r.equals("Pilote")){
                PiloteService ps=new PiloteService();
                Pilote p=new Pilote(parseInt(TextNum.getText()),nom.getText(),photo,r,nat.getText(),df.parse(date.getValue().toString()),ep);
                ps.insert(p);
            }else{
                MembreService ms=new MembreService();
                Membre m=new Membre(nom.getText(),photo,r,nat.getText(),df.parse(date.getValue().toString()),ep);
                ms.insert(m);
            }
            //set field
            file.setText("Aucun fichier choisir");
            myFile.setText("");
            nom.setText("");
            nat.setText("");
            TextNum.setText("");
            equipe.getSelectionModel().select(0);
            ListRole.getSelectionModel().select(0);
            image.setVisible(false);
            date.setValue(null);
            //set controls
            controlfile=false;
            controlnom=false;
            controlrole=false;
            controlnat=false;
            controldate=false;
            controlequipe=false;
            controlnum=false;
            
            ImgFile.setImage(notic);
            ImgNom.setImage(notic);
            ImgRole.setImage(notic);
            ImgNat.setImage(notic);
            ImgDate.setImage(notic);
            ImgEquipe.setImage(notic);
            ImgNum.setImage(notic);
            Notifications not=Notifications.create()
                .title("Succé")
                .text("Le membre est ajouté avec succé")
                .hideCloseButton()
                .position(Pos.TOP_RIGHT);
                not.darkStyle();
                not.showInformation();
        }else{
            Notifications not=Notifications.create()
                .title("Ouups !")
                .text("Vérifier les champs")
                .hideCloseButton()
                .position(Pos.TOP_RIGHT);
                not.darkStyle();
                not.showError();
        }
            
        
        
    }
    
    @FXML
    private void ShowNumero(ActionEvent event){
        String r=ListRole.getSelectionModel().getSelectedItem();
        if(r.equals("")){
            controlrole=false;
            textRole.setText("Le role est obligatoire");
            textRole.setVisible(true);
            ImgRole.setImage(notic);
        }else{
            if(r.equals("Pilote")){
                ImgNum.setVisible(true);
            //Show Numero
            LabNum.setVisible(true);
            TextNum.setVisible(true);
            }else{
                controlnum=true;
                //Hide Numero
                LabNum.setVisible(false);
                TextNum.setVisible(false);
            }
            
            controlrole=true;
            textRole.setVisible(false);
            ImgRole.setImage(tic);
        }
        
    }
    
    

    
}
