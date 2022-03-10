/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author nechi
 */
import entite.Equipe;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import service.EquipeService;

/**
 * FXML Controller class
 *
 * @author nechi
 */
public class AjouterEquipeController implements Initializable {
    //Attribut ajouter equipe
    @FXML
    private TextField nom;
    @FXML
    private TextField voiture;
    @FXML
    private TextField pays;
    @FXML
    private Button add;
    @FXML
    private Button choosefile;
    @FXML
    private Label file;
    @FXML
    private Label myFile;
    @FXML
    private ImageView image;
    
    @FXML
    private Label textNom;
    
    @FXML
    private ImageView ImgNom;
    
     @FXML
    private ImageView ImgFile;

    @FXML
    private Label textFile;
    
    @FXML
    private Label textVoiture;
    
     @FXML
    private ImageView ImgVoiture;
     
     
     @FXML
    private Label textPays;
    
     @FXML
    private ImageView ImgPays;
     
     
      @FXML
    private TextField email;

    @FXML
    private ImageView ImgEmail;

    @FXML
    private Label textEmail;
    /**
     * Initializes the controller class.
     */
    
    
    private boolean controlnom;
    private boolean controlvoiture;
    private boolean controlpays;
    private boolean controlfile;
    private boolean controlmail;
    
    Image tic=new Image("/view/images/tic.jpg");
    Image notic=new Image("/view/images/notic.png");
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
                controlnom=false;
                controlvoiture=false;
                controlpays=false;
                controlfile=false;
                controlmail=false;
    }
    
    
    @FXML
    void VerifEmail(){
        if(email.getText().length() < 6 || !email.getText().contains("@") || !email.getText().contains(".") || (email.getText().indexOf("@") > email.getText().indexOf("."))){
            controlmail=false;
            textEmail.setText("Vérifier votre mail (@ et . )");
            textEmail.setVisible(true);
            ImgEmail.setImage(notic);
        }else{
            controlmail=true;
            textEmail.setVisible(false);
            ImgEmail.setImage(tic);
        }
    }
    
    
    @FXML
    void VerifNom(){
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
    void VerifVoiture(){
        if(voiture.getText().length() < 1){
            controlvoiture=false;
            textVoiture.setText("Nom de la voiture obligatoire");
            textVoiture.setVisible(true);
            ImgVoiture.setImage(notic);
        }else{
            controlvoiture=true;
            textVoiture.setVisible(false);
            ImgVoiture.setImage(tic);
        }
    }
    
    
    @FXML
    void VerifPays(){
        if(pays.getText().length() < 1){
            controlpays=false;
            textPays.setText("Le champs pays est obligatoire");
            textPays.setVisible(true);
            ImgPays.setImage(notic);
        }else{
            controlpays=true;
            textPays.setVisible(false);
            ImgPays.setImage(tic);
        }
    }
    
    //Methodes ajouter équipe
    
    @FXML
    private void ChooseFile() throws FileNotFoundException{
        try {
            String filename;
            JFileChooser chooser= new JFileChooser();
            chooser.showOpenDialog(null);
            File f=chooser.getSelectedFile();
            if(f != null){
                filename = f.getAbsolutePath();
                file.setText(filename);
                Image img = new Image(new FileInputStream(filename));
                image.setImage(img);
                image.setVisible(true);
                //Déplacer l'image
                String newPath="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\images\\";
                File sourceFile=null;
                File destinationFile=null;
                String nFile = f.getName();
                myFile.setText(nFile);
                sourceFile= new File(filename);
                destinationFile=new File(newPath+nFile);
                if(!destinationFile.exists()){
                   Files.copy(sourceFile.toPath() , destinationFile.toPath()); 
                }else{
                    Notifications not=Notifications.create()
                    .title("Warning")
                    .text("Image déjà existe")
                    .hideCloseButton()
                    .position(Pos.TOP_RIGHT);
                    not.darkStyle();
                    not.showWarning();
                }
                ImgFile.setImage(tic);
                controlfile=true;
                textFile.setVisible(false);
            }else{
                textFile.setText("Sélection image obligatoire");
                
                ImgFile.setImage(notic);
                controlfile=false;
            }
            
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hundleButtonAction(ActionEvent event) {
        if(controlnom && controlvoiture && controlpays && controlfile && controlmail){
            EquipeService es=new EquipeService();
            String logo=myFile.getText();
            Equipe e1=new Equipe(nom.getText(),email.getText(),logo,voiture.getText(),pays.getText());
            es.insert(e1);
            //setfield
            nom.setText("");
            file.setText("Aucun fichier choisir");
            myFile.setText("");
            voiture.setText("");
            pays.setText("");
            email.setText("");
            image.setVisible(false);
            //set control
            ImgNom.setImage(notic);
            controlnom=false;
            ImgFile.setImage(notic);
            controlfile=false;
            ImgVoiture.setImage(notic);
            controlvoiture=false;
            ImgPays.setImage(notic);
            controlpays=false;
            ImgEmail.setImage(notic);
            controlmail=false;
            
            Notifications not=Notifications.create()
                .title("Succé")
                .text("L'équipe est ajouté avec succé")
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
    
    
}
