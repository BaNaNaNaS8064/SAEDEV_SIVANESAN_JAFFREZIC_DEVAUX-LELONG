package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.controller.observateurs.*;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private Timeline gameLoop;

    // centre
    @FXML public Pane paneDessin;
    @FXML public Pane paneLignes;
    @FXML public TilePane tuiles;

    // haut -> vagues
    @FXML public Label labelVagueActuelle;
    @FXML public Label labelVagueMax;

    // haut -> vie
    @FXML public ProgressBar barreDeVie;
    @FXML public Label labelPvActuels;
    @FXML public Label labelPvMax;

    //haut button
    @FXML public Button startButton;

    // droite
    @FXML public Label labelSolde;
    @FXML public ToggleGroup toggleGrpCellules;

    @FXML public Label labelCoutSainple;
    @FXML public Label labelCoutLasere;
    @FXML public Label labelCoutBrouaieuse;
    @FXML public Label labelCoutMuleTyple;
    @FXML public Label labelCoutSnaipeur;
    @FXML public Label labelCoutRizCoCher;
    @FXML public Label labelCoutKonsantre;
    @FXML public Label labelCoutPouazon;
    @FXML public Label labelCoutBrulHure;

    // vue
    private AfficheurDeCarte afficheurDeCarte;
    private AfficheurDeChemin afficheurDeChemin;

    // modèle
    private Environnement environnement;

    //controllerPackage
    private GestionnaireClickCarte gestionnaireClickCarte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();

        afficheurDeCarte = new AfficheurDeCarte(environnement, tuiles, new AfficheurDeChemin(environnement, paneLignes));
        gestionnaireClickCarte = new GestionnaireClickCarte(environnement, toggleGrpCellules, afficheurDeCarte);

        initLabels();
        initObservateurs();

        initGameLoop();
        gameLoop.play();
    }

    private void initObservateurs(){
        environnement.getMaladies().addListener(new ObsListeMaladies(paneDessin));
        environnement.getRayons().addListener(new ObsListeRayons(paneDessin));
        environnement.getZones().addListener(new ObsListeZones(paneDessin));
        environnement.getJoueur().pvProperty().addListener(new ObsVieJoueur(new GestionnaireBarreDeVie(barreDeVie, labelPvActuels, labelPvMax, environnement.getJoueur().getPv())));
        environnement.statutPartieProperty().addListener(new ObsStatutPartie(new GestionnaireEcranDeFin(paneDessin)));
    }

    private void initLabels(){
        labelSolde.textProperty().bind(environnement.getJoueur().pcProperty().asString());
        labelVagueActuelle.textProperty().bind(environnement.getNiveau().numVagueProperty().add(1).asString());
        labelVagueMax.setText("/" + environnement.getNiveau().nombreDeVagues());

        labelCoutSainple.setText("" + Sainple.getCoutBase());
        labelCoutLasere.setText("" + Lasere.getCoutBase());
        labelCoutBrouaieuse.setText("" + Brouaileuse.getCoutBase());
        labelCoutMuleTyple.setText("" + MuleTyple.getCoutBase());
        labelCoutSnaipeur.setText("" + Snaipeur.getCoutBase());
        labelCoutRizCoCher.setText("?");
        labelCoutKonsantre.setText("" + Konsantre.getCoutBase());
        labelCoutPouazon.setText("" + Pouazon.getCoutBase());
        labelCoutBrulHure.setText("?");
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

    @FXML
    public void clickTuiles(MouseEvent mouseEvent) {
        gestionnaireClickCarte.gererClick(mouseEvent);
    }

    @FXML
    public void démarrerVague(MouseEvent mouseEvent) {
        environnement.getNiveau().passerProchaineVague();
        startButton.setDisable(true);
        startButton.setVisible(false);
    }
}