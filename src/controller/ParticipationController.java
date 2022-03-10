/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entite.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.*;


public class ParticipationController implements Initializable {

    @FXML
    private ComboBox<Pilote> pilotes;
    @FXML
    private ComboBox<course> courses;
    @FXML
    private ComboBox<Equipe> equipes;
    @FXML
    private TextField Points;
    @FXML
    private TextField Position;
    @FXML
    private TableView<Participation> table;
    @FXML
    private TableColumn<Participation, String> col_pilote;
    @FXML
    private TableColumn<Participation, Integer> col_num;
    @FXML
    private TableColumn<Participation, String> col_equipe;
    @FXML
    private TableColumn<Participation, String> col_course;
    @FXML
    private TableColumn<Participation, Integer> col_saison;
    @FXML
    private TableColumn<Participation, Integer> col_position;
    @FXML
    private TableColumn<Participation, Integer> col_points;
    @FXML
    private AnchorPane body;
    @FXML
    private ComboBox<Saison> saison;
    @FXML
    private ComboBox<course> course;
    @FXML
    private ComboBox<Pilote> pilote;
    @FXML
    private ComboBox<Equipe> equipe;
    @FXML
    private TableColumn<Participation, Integer> col_grid;
    
    @FXML
    private TextField search;

    public void fillCombo() {
        PiloteService ps = new PiloteService();
        CourseService cs = new CourseService();
        EquipeService es = new EquipeService();
        ps.read1().forEach((pi) -> {
            pilotes.getItems().add(pi);
        });

        cs.read().forEach((c) -> {
            courses.getItems().add(c);
        });
        es.read().forEach((e) -> {
            equipes.getItems().add(e);
        });
    }
    
    public void searching(){
        
        ParticipationService ps = new ParticipationService();   
        ObservableList<Participation> tab = FXCollections.observableArrayList();
        String query = search.getText();
        ps.search(query).forEach((p) -> {
            tab.add(new Participation(p.getParticipation_id(), p.getPilote(), p.getEquipe(), p.getCourse(), p.getQualifying(), p.getGrid(), p.getPosition(), p.getPoints()));
        });
        col_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        col_num.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPilote().getNumero()));
        col_equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
        col_saison.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourse().getSaison_year().getYear()));
        col_position.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPosition()));
        col_points.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPoints()));
        table.setItems(tab);
        
    }

    public void fillFilterCombo() {
        SaisonService ss = new SaisonService();
        PiloteService ps = new PiloteService();
        CourseService cs = new CourseService();
        EquipeService es = new EquipeService();

        ss.read().forEach((s) -> {
            saison.getItems().add(s);
        });

        ps.read1().forEach((pi) -> {
            pilote.getItems().add(pi);
        });

        cs.read().forEach((c) -> {
            course.getItems().add(c);
        });
        es.read().forEach((e) -> {
            equipe.getItems().add(e);
        });
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
    public void hundleAddButton(ActionEvent event) throws IOException, SQLException {
        ParticipationService ps = new ParticipationService();
        QualifyingService qs = new QualifyingService();
        Qualifying q;

        if (Position.getText().isEmpty()
                || Points.getText().isEmpty() || pilotes.getSelectionModel().isEmpty()
                || equipes.getSelectionModel().isEmpty() || courses.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Please provide all informations");
            alert.show();
        } else if (!isInt(Position) || !isInt(Points)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please provide numbers ");
            alert.show();
        } else {

            if (!qs.hasData(courses.getValue(), pilotes.getValue())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Veuillez fournir des informations de qualification avant d'ajouter des informations de participation");
                alert.show();
                reset();
            } else {
                q = qs.readByCP(courses.getValue(), pilotes.getValue());
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
                Position.setText("");
                Points.setText("");
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Ajouté avec succé");
                alert.show();
                reset();
            }
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

    @FXML
    public void filterButton() throws IOException {
        ParticipationService ps = new ParticipationService();
        ObservableList<Participation> tab = FXCollections.observableArrayList();
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
        Integer eq = null;
        if (!checkIfNull(equipe)) {
            eq = equipe.getValue().getEquipe_id();
        }
        ps.filter(ye, co, pi, eq).forEach((p) -> {
            tab.add(new Participation(p.getParticipation_id(), p.getPilote(), p.getEquipe(), p.getCourse(), p.getQualifying(), p.getGrid(), p.getPosition(), p.getPoints()));
        });
        col_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        col_num.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPilote().getNumero()));
        col_equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
//        col_saison.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourse().getSaison_year().getYear()));
        col_position.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPosition()));
        col_points.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPoints()));

        table.setItems(tab);
    }

    @FXML
    public void OpenUpdateParticpation() throws IOException {

        UpdateParticipationController upc = new UpdateParticipationController();
        Participation p = table.getSelectionModel().getSelectedItem();

        upc.setParticipation(p);
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateParticipation.fxml"));
        final Node node;
        node = fxmlLoader.load();
        AnchorPane pane = new AnchorPane(node);
        body.getChildren().setAll(pane);
    }

    @FXML
    public void reset() throws IOException {
        course.getSelectionModel().clearSelection();
        pilote.getSelectionModel().clearSelection();
        equipe.getSelectionModel().clearSelection();
        saison.getSelectionModel().clearSelection();
        ParticipationService ps = new ParticipationService();
        ObservableList<Participation> tab = FXCollections.observableArrayList();
        ps.read().forEach((p) -> {
            tab.add(new Participation(p.getParticipation_id(), p.getPilote(), p.getEquipe(), p.getCourse(), p.getQualifying(), p.getGrid(), p.getPosition(), p.getPoints()));
        });
        col_pilote.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote().getNom()));
        col_num.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPilote().getNumero()));
        col_equipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse().getCourse_nom()));
//        col_saison.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourse().getSaison_year().getYear()));
        col_grid.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQualifying().getPosition()));
        col_position.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPosition()));
        col_points.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPoints()));
        table.setItems(tab);
    }

    public void sendingMail() throws Exception{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Evoyer mail");
        alert.setContentText("Envoyer mail a"+table.getSelectionModel().getSelectedItem().getEquipe());
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ParticipationService ps = new ParticipationService();
            ps.sendMail(table.getSelectionModel().getSelectedItem().getEquipe().getMail(), table.getSelectionModel().getSelectedItem());
            try {
                reset();
            } catch (IOException ex) {
                Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @FXML
    public void DeleteParticipation() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresssion");
        alert.setContentText("Voulez-vous vraiment supprimer cette participation ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ParticipationService ps = new ParticipationService();
            ps.delete(table.getSelectionModel().getSelectedItem());
            try {
                reset();
            } catch (IOException ex) {
                Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
