/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entite.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.*;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class VitrineParticipationController implements Initializable {

    @FXML
    private TableView<Participation> table;
    @FXML
    private TableColumn<Participation, String> c_course;
    @FXML
    private TableColumn<Participation, String> c_pilote;
    @FXML
    private TableColumn<Participation, String> c_equipe;
    @FXML
    private TableColumn<Participation, String> c_num;
    @FXML
    private TableColumn<Participation, String> c_voiture;
    @FXML
    private TableColumn<Participation, String> c_q1;
    @FXML
    private TableColumn<Participation, String> c_q2;
    @FXML
    private TableColumn<Participation, String> c_q3;
    @FXML
    private TableColumn<Participation, String> c_grid;
    @FXML
    private TableColumn<Participation, String> c_position;
    @FXML
    private TableColumn<Participation, String> c_points;
    @FXML
    private ListView<Saison> list_saison;
    @FXML
    private ListView<course> list_course;
    @FXML
    private ListView<Pilote> list_pilote;
    @FXML
    private ListView<Equipe> list_equipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reset();
        fillList();
    }

    public void fillList() {
        SaisonService ss = new SaisonService();
        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();

        ss.read().forEach((s) -> {
            list_saison.getItems().add(s);
        });
        ps.read1().forEach((s) -> {
            list_pilote.getItems().add(s);
        });
        es.read().forEach((s) -> {
            list_equipe.getItems().add(s);
        });
        cs.read().forEach((s) -> {
            list_course.getItems().add(s);
        });
    }
    
    public void clearselected(){
        list_course.getSelectionModel().clearSelection();
        list_equipe.getSelectionModel().clearSelection();
        list_saison.getSelectionModel().clearSelection();
        list_pilote.getSelectionModel().clearSelection();
        reset();
    }

    public void handleListViewActionA() throws IOException {
        ObservableList<Participation> tab = FXCollections.observableArrayList();
        ParticipationService ps = new ParticipationService();
        Integer ls = null;
        if (!checkIfNull(list_saison)) {
            ls = list_saison.getSelectionModel().getSelectedItem().getYear();
        }

        Integer lp = null;
        if (!checkIfNull(list_pilote)) {
            lp = list_pilote.getSelectionModel().getSelectedItem().getPilote_id();
        }

        Integer le = null;
        if (!checkIfNull(list_equipe)) {
            le = list_equipe.getSelectionModel().getSelectedItem().getEquipe_id();
        }
        Integer lc = null;
        if (!checkIfNull(list_course)) {
            lc = list_course.getSelectionModel().getSelectedItem().getCourse_id();
        }

              ps.filter(ls, lc, lp, le).forEach((p) -> {
            tab.add(new Participation(p.getParticipation_id(), p.getPilote(), p.getEquipe(), p.getCourse(), p.getQualifying(), p.getGrid(), p.getPosition(), p.getPoints()));
        });

        c_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
        c_equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        c_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        c_voiture.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getVoiture()));
        c_num.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPilote().getNumero())));
        c_q1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQualifying().getQ1()));
        c_q2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQualifying().getQ2()));
        c_q3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQualifying().getQ3()));
        c_grid.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQualifying().getPosition())));
        c_position.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPosition())));
        c_points.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPoints())));

        table.setItems(tab);

    }

    public boolean checkIfNull(ListView x) {
        return x.getSelectionModel().isEmpty();
    }

    public void reset() {

        ParticipationService ps = new ParticipationService();
        ObservableList<Participation> t = FXCollections.observableArrayList();
        ps.read().forEach((p) -> {
            t.add(new Participation(p.getParticipation_id(), p.getPilote(), p.getEquipe(), p.getCourse(), p.getQualifying(), p.getGrid(), p.getPosition(), p.getPoints()));
        });
        c_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
        c_equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        c_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        c_voiture.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getVoiture()));
        c_num.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPilote().getNumero())));
        c_q1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQualifying().getQ1()));
        c_q2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQualifying().getQ2()));
        c_q3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQualifying().getQ3()));
        c_grid.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQualifying().getPosition())));
        c_position.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPosition())));
        c_points.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPoints())));

        table.setItems(t);
    }

}
