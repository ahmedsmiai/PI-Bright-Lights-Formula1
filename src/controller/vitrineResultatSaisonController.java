/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entite.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.*;
import service.ParticipationService;
import service.SaisonService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class vitrineResultatSaisonController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private ListView<Saison> listSaison;

    @FXML
    private TableColumn<Participation, String> col_course;
    @FXML
    private TableColumn<Participation, String> col_date;
    @FXML
    private TableColumn<Participation, String> col_winner;
    @FXML
    private TableColumn<Participation, String> col_equipe;
    @FXML
    private TableColumn<Participation, String> col_voiture;
    @FXML
    private TableView<Participation> tab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillList();
        int y = 2022;
        try {
            reset(y);
        } catch (IOException ex) {
            Logger.getLogger(vitrineResultatSaisonController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fillList() {
        SaisonService ss = new SaisonService();
        ss.read().forEach((s) -> {
            listSaison.getItems().add(s);
        });
    }

    @FXML
    public void handleListViewAction() throws IOException {
        int selected = listSaison.getSelectionModel().getSelectedItem().getYear();
        reset(selected);
    }

    public void clearselected() throws IOException {
        listSaison.getSelectionModel().clearSelection();
        reset(2022);
    }

    public void reset(int year) throws IOException {

        titre.setText("Resultats courses " + year);
        ParticipationService ps = new ParticipationService();
        ObservableList<Participation> t = FXCollections.observableArrayList();
        ps.readWinners(year).forEach((p) -> {
            t.add(new Participation(p.getParticipation_id(), p.getPilote(), p.getEquipe(), p.getCourse(), p.getQualifying(), p.getGrid(), p.getPosition(), p.getPoints()));
        });
        col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
        col_equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        col_winner.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        col_date.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getCourse().getDate())));
        col_voiture.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getVoiture()));
        
        tab.setItems(t);
    }

}
