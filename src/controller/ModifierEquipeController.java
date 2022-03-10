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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class ModifierEquipeController implements Initializable {
    //Attribut Modifier equipe
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
    private TextField email;
    
    @FXML
    private AnchorPane body;
    
    @FXML
    private Label id;
    
      @FXML
    private Label textNom;

    @FXML
    private Label textPays;

    @FXML
    private Label textVoiture;

    @FXML
    private Label textImg;
    
   private static Equipe equipe=new Equipe();
   
   private boolean controlnom;
   private boolean controlvoiture;
   private boolean controlfile;
   private boolean controlpays;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String idChar=Integer.toString(equipe.getEquipe_id());
        id.setText(idChar);
        nom.setText(equipe.getNom());
        voiture.setText(equipe.getVoiture());
        pays.setText(equipe.getPays_origin());
        file.setText(equipe.getLogo());
        myFile.setText(equipe.getLogo());
        image.setImage(equipe.getImg().getImage());
       // System.out.println(equipe.getImg());
       
       
       controlnom = true;
       controlvoiture = true ;
       controlfile = true;
       controlpays = true;
        
    }
    
    
    @FXML
    void VerifNom() {
        if(nom.getText().length() < 6){
            controlnom=false;
            textNom.setText("Minimum 6 caractéres");
            textNom.setVisible(true);
            //ImgNom.setImage(notic);
        }else{
            controlnom=true;
            textNom.setVisible(false);
           // ImgNom.setImage(tic);
        }
    }

    @FXML
    void VerifPays() {
        if(pays.getText().length() < 1){
            controlpays=false;
            textPays.setText("Le champs pays est obligatoire");
            textPays.setVisible(true);
            //ImgPays.setImage(notic);
        }else{
            controlpays=true;
            textPays.setVisible(false);
           // ImgPays.setImage(tic);
        }

    }

    @FXML
    void VerifVoiture() {
        if(voiture.getText().length() < 1){
            controlvoiture=false;
            textVoiture.setText("Nom de la voiture obligatoire");
            textVoiture.setVisible(true);
            //ImgVoiture.setImage(notic);
        }else{
            controlvoiture=true;
            textVoiture.setVisible(false);
            //ImgVoiture.setImage(tic);
        }
    }
    
    //Methodes Modifier équipe
    
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
                //ImgFile.setImage(tic);
                controlfile=true;
                textImg.setVisible(false);
            }else{
                textImg.setText("Sélection image obligatoire");
                
                //ImgFile.setImage(notic);
                controlfile=false;
            }
            
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void UpdateEquipe() throws IOException {
        if(controlnom && controlvoiture && controlpays && controlfile){
            EquipeService es=new EquipeService();
            File f=new File(myFile.getText());
            String logo=f.getName();
            int ID=Integer.parseInt(id.getText());
            Equipe e1=new Equipe(ID,nom.getText(),logo,voiture.getText(),pays.getText());
            es.update(e1);
            
            Notifications not=Notifications.create()
                .title("Succé")
                .text("L'équipe est modifier avec succé")
                .hideCloseButton()
                .position(Pos.TOP_RIGHT);
                not.darkStyle();
                not.showInformation();
                
                final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Equipe.fxml"));
                final Node node;
                node = fxmlLoader.load();
                AnchorPane pane=new AnchorPane(node);
                body.getChildren().setAll(pane);
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

    public void setEquipe(Equipe e) {
        equipe.setEquipe_id(e.getEquipe_id());
        equipe.setNom(e.getNom());
        equipe.setLogo(e.getLogo());
        equipe.setVoiture(e.getVoiture());
        equipe.setImg(e.getImg());
        equipe.setPays_origin(e.getPays_origin());
    }
    
    
    
    
    
}
