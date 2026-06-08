package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPrincipal implements Initializable {
    @FXML public Pane paneMenuPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void commencerJeu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vd-view.fxml"));
        BorderPane borderPane = new BorderPane(fxmlLoader.load());
        paneMenuPrincipal.getChildren().add(borderPane);
    }
}
