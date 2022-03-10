/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaone;

import entite.Equipe;
import entite.Membre;
import entite.Pilote;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import service.UserService;
/**
 *
 * @author nechi
 */


public class FormulaOne extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
        final Node node = fxmlLoader.load();
        StackPane root = new StackPane(node);
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
    
}
