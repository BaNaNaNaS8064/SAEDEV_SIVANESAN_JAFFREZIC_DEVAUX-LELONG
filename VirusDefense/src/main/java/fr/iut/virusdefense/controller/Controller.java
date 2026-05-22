package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Params;
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
import javafx.scene.input.MouseButton;
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
    public Pane paneDessin;

    @FXML
    public TilePane tuiles;

    @FXML
    public ProgressBar barreDeVie;

    @FXML
    public Label labelPvActuels;

    @FXML
    public Label labelTotalPV;

    @FXML
    public Label soldeNb;

    @FXML
    public ToggleGroup cellules;

    @FXML
    public Label lbVague;

    /* Label des cout des tours */
    @FXML
    public Label lbCoutSainple;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();
        environnement.getMaladies().addListener(new ObsListeMaladies(paneDessin));
        environnement.getTirs().addListener(new ObsListeTir(paneDessin));

        initBarreVie();

        afficheurDeCarte = new AfficheurDeCarte(environnement, tuiles);
        ajouterEventTuile();

        soldeNb.textProperty().bind(environnement.getJoueur().pcProperty().asString());
        lbVague.textProperty().bind(environnement.getNiveau().numVagueProperty().add(1).asString());

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

            if(environnement.getJoueur().getPc()>=50){
                environnement.ajouterCellule(c);
                afficheurDeCarte.reloadEmplacementCarte(ligne, colonne);

            }
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
            environnement.retirerCellule(c, true);
            ((Tuile)tuiles.getChildren().get(ligne*environnement.getCarte().getLargeur() + colonne)).setImage(SpritesTuiles.imageDe(environnement.getCarte().getCode(ligne, colonne)));
            environnement.getDeplacement().faireAlgo();
        }else{
            ajouterEventTuile();

        }
    }

    private void ajouterEventTuile(){
        for (int i = 0; i<(environnement.getCarte().getHauteur() * environnement.getCarte().getLargeur()); i++) {
            Tuile t = (Tuile) tuiles.getChildren().get(i);

            t.setOnMousePressed(event -> {});

            if(environnement.getCarte().getCode(t.getLigne(), t.getColonne()) != Params.codeTuile.MUR)
                t.setOnMousePressed(mouseEvent -> {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && environnement.getCarte().getCode(t.getLigne(), t.getColonne()) == Params.codeTuile.VIDE)
                        poserCellules(t.getLigne(), t.getColonne());
                    else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && environnement.getCarte().getCode(t.getLigne(), t.getColonne()) != Params.codeTuile.VIDE) {
                        environnement.retirerCelluleA(t.getLigne(), t.getColonne(), false);
                        afficheurDeCarte.reloadEmplacementCarte(t.getLigne(), t.getColonne());
                    }
                });
        }
    }
}