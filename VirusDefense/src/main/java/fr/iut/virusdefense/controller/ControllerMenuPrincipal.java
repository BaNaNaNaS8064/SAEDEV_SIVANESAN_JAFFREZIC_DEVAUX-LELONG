package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPrincipal implements Initializable {
    @FXML public Pane paneMenuPrincipal;
    @FXML public ChoiceBox<String> menuChoixNiveau;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String[] niveaux = new File(Main.class.getResource("niveaux/").toURI()).list();
            menuChoixNiveau.getItems().addAll(niveaux);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void commencerJeu() throws IOException {
        if (menuChoixNiveau.getValue() != null){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vd-view.fxml"));
            BorderPane borderPane = new BorderPane(fxmlLoader.load());
            ((Controller) fxmlLoader.getController()).changerNiveauEtJouer(menuChoixNiveau.getValue());
            paneMenuPrincipal.getChildren().clear();
            paneMenuPrincipal.getChildren().add(borderPane);
        }
    }
}
