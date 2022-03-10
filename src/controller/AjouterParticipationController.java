package Controller;

import service.*;
import entite.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AjouterParticipationController implements Initializable {

    @FXML
    private ComboBox<Pilote> pilotes;
    @FXML
    private ComboBox<course> courses;
    @FXML
    private ComboBox<Equipe> equipes;
    @FXML
    private TextField Grid;
    @FXML
    private TextField Points;
    @FXML
    private TextField Position;

    
    public void fillCombo() {
        PiloteService ps = new PiloteService();
        CourseService cs = new CourseService();
        EquipeService es = new EquipeService();

        ps.read().forEach((pi) -> {
            pilotes.getItems().add(pi);
        });

        cs.read().forEach((c) -> {
            courses.getItems().add(c);
        });
        es.read().forEach((e) -> {
            equipes.getItems().add(e);
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCombo();
    }
    @FXML
    public void hundleAddButton(ActionEvent event) {
        ParticipationService ps = new ParticipationService();

        if (Grid.getText().isEmpty() || Position.getText().isEmpty()
                || Points.getText().isEmpty() || pilotes.getSelectionModel().isEmpty()
                || equipes.getSelectionModel().isEmpty() || courses.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Please provide all informations");
            alert.show();
        } else if (!isInt(Grid) || !isInt(Position) || !isInt(Points)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please provide numbers ");
            alert.show();
        } else {
            QualifyingService qs = new QualifyingService();
            Qualifying q = qs.readByCP(courses.getValue(), pilotes.getValue());
            Participation p1 = new Participation(
                    pilotes.getValue(),
                    equipes.getValue(),
                    courses.getValue(),
                    q,
                    q.getPosition(),
                    Integer.parseInt(Position.getText()),
                    Integer.parseInt(Points.getText())
            );
            ps.insert(p1);
            courses.getSelectionModel().clearSelection();
            pilotes.getSelectionModel().clearSelection();
            equipes.getSelectionModel().clearSelection();
            Grid.setText("");
            Position.setText("");
            Points.setText("");
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Ajouté avec succé");
            alert.show();
        }
    }

    private boolean isInt(TextField input) {
        try {
            int val = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
