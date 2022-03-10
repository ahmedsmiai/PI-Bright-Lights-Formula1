/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entite.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.QualifyingService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class UpdateQualifyingController implements Initializable {

    private static Qualifying qlf = new Qualifying();

    @FXML
    private TextField course;
    @FXML
    private TextField pilote;
    @FXML
    private TextField position;
    @FXML
    private TextField q1;
    @FXML
    private TextField q2;
    @FXML
    private TextField q3;
    @FXML
    private AnchorPane body;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String idChar = Integer.toString(qlf.getQualifying_id());
        course.setText(qlf.getCourse().getCourse_nom());
        pilote.setText(qlf.getPilote().getNom());
        q1.setText(qlf.getQ1());
        q2.setText(String.valueOf(qlf.getQ2()));
        q3.setText(String.valueOf(qlf.getQ3()));
        position.setText(String.valueOf(qlf.getPosition()));
    }

    public void setQualifying(Qualifying q) {
        qlf.setCourse(q.getCourse());
        qlf.setPilote(q.getPilote());
        qlf.setQ1(q.getQ1());
        qlf.setQ2(q.getQ2());
        qlf.setQ3(q.getQ3());
        qlf.setPosition(q.getPosition());
        qlf.setQualifying_id(q.getQualifying_id());
    }

    public void UpdateQualifying() throws IOException {
        QualifyingService qs = new QualifyingService();
        
        
        int ID = qlf.getQualifying_id();
        course c = qlf.getCourse();
        Pilote p = qlf.getPilote();
        String Q1 = q1.getText();
        String Q2=q2.getText();
        String Q3=q3.getText();
        int pos = Integer.parseInt(position.getText());
       
        Qualifying ql = new Qualifying(ID, Q1, Q2, Q3, pos, p, c);
        
        qs.update(ql);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Qualifying à été modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Qualifying.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane = new AnchorPane(node);
            body.getChildren().setAll(pane);
        }
    }

    public void CancelBTN() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Qualifying.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
        body.getChildren().setAll(pane);
    }
}
