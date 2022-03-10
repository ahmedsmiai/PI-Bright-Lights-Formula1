/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitrine;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.AcceuilController;
import controller.AddUserController;
import entite.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.controlsfx.control.Notifications;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author win10LIGHT
 */
public class InscriptionController implements Initializable {

       @FXML
    private AnchorPane Inscription;
       
    @FXML
    private JFXTextField JFXTextFieldUsername;

    @FXML
    private JFXTextField JFXTextFielEmail;

    @FXML
    private JFXPasswordField JFXTextFieldPassword;
    
        @FXML
    private JFXTextField jtextFieldTel;
    
      @FXML
    private ImageView image;

    @FXML
    private Label myFile;

    @FXML
    private Label Path;
    
       @FXML
    private Label reponseTel;

    @FXML
    private Label reponseMail;
    
     @FXML
    private Label labelUsername;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelImage;
    
    @FXML
    private Label labelTel;
    
     private UserService us;
      private static final String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
      
      private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.us = new UserService();
    }    
     @FXML
    void onInscrire(ActionEvent event) throws IOException, Exception {
          int status = 1;
        String role = "Spectateur";
        String username = JFXTextFieldUsername.getText();
        String email = JFXTextFielEmail.getText();
        String password = JFXTextFieldPassword.getText();
        String image_name = myFile.getText();
        String tel = jtextFieldTel.getText();
        // String tel = jtextFieldTel.getText();
         User u = new User (username, email,tel , password,role, image_name,status);
        if(JFXTextFieldUsername.getText().isEmpty()){labelUsername.setText("veuillez saisir votre username");}
        else if(JFXTextFielEmail.getText().isEmpty() || !email.matches(EMAIL_PATTERN)){labelEmail.setText("veuillez saisir votre @mail");}
        else if (jtextFieldTel.getText().isEmpty() || !jtextFieldTel.getText().matches("^[0-9]+$") || jtextFieldTel.getText().length() != 8){labelPassword.setText("veuillez saisir votre N° du telelphone");}
        else if (JFXTextFieldPassword.getText().isEmpty()){labelPassword.setText("veuillez saisir votre mot de passe");}
         
        else if (image.getImage() == null){labelImage.setText("veuillez inserer votre photo");}  
        else{
            
                 us.insertUserPst(u);
            us.envoyerMail(email);
                     
                      final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/vitrine/Login.fxml"));
                    final Node node;
                    try {
                        node = fxmlLoader.load();
                        AnchorPane pane=new AnchorPane(node);
                        Inscription.getChildren().setAll(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        
        }
       
    }
    
     @FXML
    void onChoisi(ActionEvent event) throws IOException {
try {
          String filename;
            JFileChooser chooser= new JFileChooser();
            chooser.showOpenDialog(null);
            File f=chooser.getSelectedFile();
                filename = f.getAbsolutePath();
                Path.setText(filename);
                Image img = new Image(new FileInputStream(filename));
                image.setImage(img);
                image.setVisible(true);
                //Déplacer l'image
                String newPath="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\images\\User\\";
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
                //Files.copy(sourceFile.toPath() , destinationFile.toPath());          
                
            //Fin déplacer image
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
