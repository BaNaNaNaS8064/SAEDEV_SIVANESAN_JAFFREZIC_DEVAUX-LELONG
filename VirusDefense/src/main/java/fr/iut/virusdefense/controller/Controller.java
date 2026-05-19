package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Timeline gameLoop;
    private AfficheurDeCarte afficheurDeCarte;
    private Environnement environnement;
    private int tailleTuiles;

    @FXML
    public Pane paneMaladie;

    @FXML
    public TilePane tuiles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();
        environnement.getMaladies().addListener(new ObsListeMaladies(paneMaladie));
        afficheurDeCarte = new AfficheurDeCarte(environnement, tuiles);

        environnement.ajouter(new BactérieBanale(environnement, 0, 2));

        initGameLoop();
        gameLoop.play();
    }

    /**
     * Créé et démmare la gameLoop
     */
    private void initGameLoop(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.017), e -> uneFrame()));
    }

    /**
     * Méthode exécutée à chaque tour
     */
    private void uneFrame(){
        environnement.unTour();
    }
}