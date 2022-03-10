/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import service.*;
import entite.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class QualifyingController implements Initializable {

    @FXML
    private AnchorPane body;
    @FXML
    private TableView<Qualifying> table;
    @FXML
    private TableColumn<Qualifying, Integer> col_ID;
    @FXML
    private TableColumn<Qualifying, String> col_pilote;
    @FXML
    private TableColumn<Qualifying, Integer> col_num;
    @FXML
    private TableColumn<Qualifying, String> col_course;
    @FXML
    private TableColumn<Qualifying, Integer> col_saison;
    @FXML
    private TableColumn<Qualifying, Integer> col_q1;
    @FXML
    private TableColumn<Qualifying, Integer> col_q2;
    @FXML
    private TableColumn<Qualifying, Integer> col_q3;
    @FXML
    private TableColumn<Qualifying, Integer> col_pos;

    @FXML
    private ComboBox<Saison> saison;
    @FXML
    private ComboBox<course> course;
    @FXML
    private ComboBox<Pilote> pilote;

    @FXML
    private ComboBox<course> courses;
    @FXML
    private ComboBox<Pilote> pilotes;
    @FXML
    private TextField Q2;
    @FXML
    private TextField Q1;
    @FXML
    private TextField Q3;
    @FXML
    private TextField position;

    /**
     * Initializes the controller class.
     */
    public void fillCombo() {
        PiloteService ps = new PiloteService();
        CourseService cs = new CourseService();

        ps.read1().forEach((pi) -> {
            pilotes.getItems().add(pi);
        });

        cs.read().forEach((c) -> {
            courses.getItems().add(c);
        });
    }

    public void fillFilterCombo() {
        SaisonService ss = new SaisonService();
        PiloteService ps = new PiloteService();
        CourseService cs = new CourseService();

        ss.read().forEach((s) -> {
            saison.getItems().add(s);
        });

        ps.read1().forEach((pi) -> {
            pilote.getItems().add(pi);
        });

        cs.read().forEach((c) -> {
            course.getItems().add(c);
        });

    }

    public void reset() throws IOException {
        course.getSelectionModel().clearSelection();
        pilote.getSelectionModel().clearSelection();
        saison.getSelectionModel().clearSelection();
        QualifyingService qs = new QualifyingService();

        ObservableList<Qualifying> tab = FXCollections.observableArrayList();
        qs.read().forEach((q) -> {
            tab.add(new Qualifying(q.getQualifying_id(), q.getQ1(), q.getQ2(), q.getQ3(), q.getPosition(), q.getPilote(), q.getCourse()));
        });
        col_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        col_num.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPilote().getNumero()));
        col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
//        col_saison.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourse().getSaison_year().getYear()));
        col_q1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        col_q2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        col_q3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        col_pos.setCellValueFactory(new PropertyValueFactory<>("position"));
        table.setItems(tab);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillFilterCombo();
        fillCombo();
        try {
            reset();
        } catch (IOException ex) {
            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkIfNull(ComboBox x) {
        return x.getSelectionModel().isEmpty();
    }

    @FXML
    private boolean isInt(TextField input) {
        try {
            int val = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void filterButton() throws IOException {
        QualifyingService qs = new QualifyingService();
        ObservableList<Qualifying> tab = FXCollections.observableArrayList();
        Integer ye = null;
        if (!checkIfNull(saison)) {
            ye = saison.getValue().getYear();
        }
        Integer co = null;
        if (!checkIfNull(course)) {
            co = course.getValue().getCourse_id();
        }
        Integer pi = null;
        if (!checkIfNull(pilote)) {
            pi = pilote.getValue().getPilote_id();
        }

        qs.filter(ye, co, pi).forEach((q) -> {
            tab.add(new Qualifying(q.getQualifying_id(), q.getQ1(), q.getQ2(), q.getQ3(), q.getPosition(), q.getPilote(), q.getCourse()));
        });
        col_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        col_num.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPilote().getNumero()));
        col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
//        col_saison.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourse().getSaison_year().getYear()));
        col_q1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        col_q2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        col_q3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        col_pos.setCellValueFactory(new PropertyValueFactory<>("position"));

        table.setItems(tab);
    }

    @FXML
    public void addQualifying(ActionEvent event) throws IOException {
        QualifyingService qs = new QualifyingService();

        if (position.getText().isEmpty()
                || Q1.getText().isEmpty() || Q2.getText().isEmpty()
                || Q3.getText().isEmpty()
                || pilotes.getSelectionModel().isEmpty()
                || courses.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please provide all informations");
            alert.show();
        } else if (!isInt(position) || !isInt(Q1) || !isInt(Q2) || !isInt(Q3)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please provide numbers ");
            alert.show();
        } else {

            Qualifying q1 = new Qualifying(
                    Q1.getText(),
                    Q2.getText(),
                    Q3.getText(),
                    Integer.parseInt(position.getText()),
                    pilotes.getValue(),
                    courses.getValue()
            );
            qs.insert(q1);
            courses.getSelectionModel().clearSelection();
            pilotes.getSelectionModel().clearSelection();

            position.setText("");
            Q1.setText("");
            Q2.setText("");
            Q3.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Ajouté avec succé");
            alert.show();
            reset();
        }
    }

    @FXML
    public void OpenUpdateQualifying() throws IOException {
        
        UpdateQualifyingController uqc = new UpdateQualifyingController();
        Qualifying q = table.getSelectionModel().getSelectedItem();

      
       uqc.setQualifying(q);
         System.out.println(q);

        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateQualifying.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
       body.getChildren().setAll(pane);
    }

    @FXML
    public void deleteQualifying() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette participation ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            QualifyingService qs = new QualifyingService();
            qs.delete(table.getSelectionModel().getSelectedItem());
            try {
                reset();
            } catch (IOException ex) {
                Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
