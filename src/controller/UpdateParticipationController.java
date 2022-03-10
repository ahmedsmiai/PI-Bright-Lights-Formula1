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
import service.ParticipationService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class UpdateParticipationController implements Initializable {

    private static Participation part = new Participation();
    @FXML
    private TextField grid;
    @FXML
    private TextField position;
    @FXML
    private TextField points;
    @FXML
    private TextField course;
    @FXML
    private TextField pilote;
    @FXML
    private TextField equipe;
    @FXML
    private AnchorPane body;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String idChar = Integer.toString(part.getParticipation_id());
        course.setText(part.getCourse().getCourse_nom());
        pilote.setText(part.getPilote().getNom());
        equipe.setText(part.getEquipe().getNom());
        grid.setText(String.valueOf(part.getGrid()));
        position.setText(String.valueOf(part.getPosition()));
        points.setText(String.valueOf(part.getPoints()));
    }

    public void setParticipation(Participation p) {
        part.setCourse(p.getCourse());
        part.setEquipe(p.getEquipe());
        part.setPilote(p.getPilote());
        part.setParticipation_id(p.getParticipation_id());
        part.setGrid(p.getGrid());
        part.setPosition(p.getPoints());
        part.setPoints(p.getPoints());
        part.setQualifying(p.getQualifying());
    }

    public void UpdateParticipation() throws IOException {
        ParticipationService ps = new ParticipationService();

        int ID = part.getParticipation_id();
        Pilote p = part.getPilote();
        Equipe e = part.getEquipe();
        course c = part.getCourse();
        Qualifying q = part.getQualifying();
        int pos = Integer.parseInt(position.getText());
        int poi = Integer.parseInt(points.getText());
        int gr = Integer.parseInt(grid.getText());

        Participation p1 = new Participation(ID, p, e, c, q, gr, pos, poi);

        ps.update(p1);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Participation à été modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Participation.fxml"));
            final Node node;
            node = fxmlLoader.load();
            AnchorPane pane = new AnchorPane(node);
            body.getChildren().setAll(pane);
        }
    }

    public void CancelBTN() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Participation.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
        body.getChildren().setAll(pane);
    }
}
