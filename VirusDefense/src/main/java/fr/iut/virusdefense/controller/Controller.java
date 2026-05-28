package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.controller.observateurs.*;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.Sainple;
import fr.iut.virusdefense.vue.*;
import fr.iut.virusdefense.vue.sprites.AssociationImage;
import fr.iut.virusdefense.vue.sprites.Tuile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private Timeline gameLoop;

    // centre
    @FXML public Pane paneDessin;
    @FXML public TilePane tuiles;

    // haut -> vagues
    @FXML public Label labelVagueActuelle;
    @FXML public Label labelVagueMax;

    // haut -> vie
    @FXML public ProgressBar barreDeVie;
    @FXML public Label labelPvActuels;
    @FXML public Label labelPvMax;

    // droite
    @FXML public Label labelSolde;
    @FXML public ToggleGroup toggleGrpCellules;

    @FXML public Label labelCoutSainple;
    @FXML public Label labelCoutLasère;
    @FXML public Label labelCoutBrouaïeuse;
    @FXML public Label labelCoutMuleTyple;
    @FXML public Label labelCoutSnaïpeur;
    @FXML public Label labelCoutRizCoCher;
    @FXML public Label labelCoutKonsantré;
    @FXML public Label labelCoutPouazon;
    @FXML public Label labelCoutBrulHure;

    // vue
    private AfficheurDeCarte afficheurDeCarte;

    // modèle
    private Environnement environnement;

    //controllerPackage
    private TuileEvent tuileEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();
        environnement.getMaladies().addListener(new ObsListeMaladies(paneDessin));
        environnement.getRayons().addListener(new ObsListeRayons(paneDessin));
        environnement.getZones().addListener(new ObsListeZones(paneDessin));

        afficheurDeCarte = new AfficheurDeCarte(environnement, tuiles);
        tuileEvent = new TuileEvent(environnement, toggleGrpCellules, afficheurDeCarte, paneDessin);
        tuileEvent.ajoutEventPane();

        labelSolde.textProperty().bind(environnement.getJoueur().pcProperty().asString());
        labelVagueActuelle.textProperty().bind(environnement.getNiveau().numVagueProperty().add(1).asString());
        labelVagueMax.setText("/" + environnement.getNiveau().nombreDeVagues());

        environnement.getJoueur().pvProperty().addListener(new ObsVieJoueur(new GereurBarreDeVie(barreDeVie, labelPvActuels, labelPvMax, environnement.getJoueur().getPv())));
        environnement.statutPartieProperty().addListener(new ObsStatutPartie(new GereurEcranDeFin(paneDessin)));

        labelCoutSainple.setText("" + Sainple.getCoutBase());

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