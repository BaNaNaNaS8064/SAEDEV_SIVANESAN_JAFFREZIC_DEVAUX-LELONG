package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Carte;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.Sainple;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.vue.SpritesTuiles;
import fr.iut.virusdefense.vue.Tuile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Timeline gameLoop;
    private AfficheurDeCarte afficheurDeCarte;
    private Environnement environnement;

    @FXML
    public Pane paneMaladie;

    @FXML
    public TilePane tuiles;

    @FXML
    public ProgressBar barreDeVie;

    @FXML
    public Label labelPvActuels;

    @FXML
    public Label labelTotalPV;

    @FXML
    public ToggleGroup cellules;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();
        environnement.getMaladies().addListener(new ObsListeMaladies(paneMaladie));

        initBarreVie();

        afficheurDeCarte = new AfficheurDeCarte(environnement, tuiles);
        ajouterEventTuile();

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

    /**
     * Méthode qui gere tout ce qui concerne la Barre de Vie et qui lie les listeners et les binds
     */
    public void initBarreVie() {
        int pvTotaux = environnement.getJoueur().getPv();
        labelTotalPV.setText("" + pvTotaux);

        labelPvActuels.textProperty().bind(environnement.getJoueur().pvProperty().asString());

        environnement.getJoueur().pvProperty().addListener(((observable, oldValue, newValue) -> barreDeVie.setProgress(newValue.doubleValue() / pvTotaux)));
    }

    public void poserCellules(int ligne, int colonne) {
        Cellule c = null;
        boolean maladiePeutPasFinir = false;
        if (((RadioButton) cellules.getSelectedToggle()).getId().equals("RbSainple")) {
            c = new Sainple(environnement, ligne, colonne);

            environnement.ajouterCellule(c);
            ((Tuile) tuiles.getChildren().get(ligne * 20 + colonne)).setImage(SpritesTuiles.imageDe(environnement.getCarte().getCode(ligne, colonne)));

        }

        //Permet de vérifier que un chemin et possible, sinon ne pose pas la tour
        environnement.getDeplacement().faireAlgo();
        int i = 0;
        while(!maladiePeutPasFinir && i < environnement.getMaladies().size()){
            if(environnement.getDeplacement().estBloquee(environnement.getMaladies().get(i).position()))
                maladiePeutPasFinir = true;
            i++;
        }

        if (environnement.getDeplacement().estBloquee(List.of(2, 0)) || maladiePeutPasFinir){
            environnement.retirerCellule(c);
            ((Tuile)tuiles.getChildren().get(ligne*20 + colonne)).setImage(SpritesTuiles.imageDe(environnement.getCarte().getCode(ligne, colonne)));
            environnement.getDeplacement().faireAlgo();
        }

    }

    private void ajouterEventTuile(){
        for (int i = 0; i<(environnement.getCarte().getHauteur() * environnement.getCarte().getLargeur()); i++) {
            Tuile t = (Tuile) tuiles.getChildren().get(i);


            if(environnement.getCarte().getCode(t.getLigne(), t.getColonne()) == Carte.VIDE)
                t.setOnMousePressed(mouseEvent -> {
                        poserCellules(t.getLigne(), t.getColonne());
                });
        }
    }
}