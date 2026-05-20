package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Carte;
import fr.iut.virusdefense.modele.Deplacement;
import fr.iut.virusdefense.modele.cellule.Cellule;
import fr.iut.virusdefense.modele.cellule.Sainple;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.vue.SpritesTuiles;
import fr.iut.virusdefense.vue.Tuiles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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
    public ToggleGroup cellules;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();
        environnement.getMaladies().addListener(new ObsListeMaladies(paneMaladie));

        environnement.ajouter(new BactérieBanale(environnement, 0, 2));


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

    public void poserCellules(int ligne, int colonne) {
        Cellule c = null;
        if (((RadioButton) cellules.getSelectedToggle()).getId().equals("RbSainple")) {
            c = new Sainple(environnement, colonne, ligne);
            environnement.ajouterCellule(c);
            ((Tuiles)tuiles.getChildren().get(ligne*20 + colonne)).setImagePath(SpritesTuiles.imageDe(environnement.getCarte().getValeurCase(ligne, colonne)));
        }

        //Permet de vérifier que un chemin et possible, sinon ne pose pas la tour
        environnement.getDeplacement().faireAlgo();
        if (!environnement.getDeplacement().peutAllerALObjectif(List.of(2,0))){
            environnement.retirerCellule(c);
            ((Tuiles)tuiles.getChildren().get(ligne*20 + colonne)).setImagePath(SpritesTuiles.imageDe(environnement.getCarte().getValeurCase(ligne, colonne)));
            environnement.getDeplacement().faireAlgo();
        }

    }

    private void ajouterEventTuile(){
        for (int i = 0; i<(environnement.getCarte().getHauteur() * environnement.getCarte().getLargeur()); i++) {
            Tuiles t = (Tuiles) tuiles.getChildren().get(i);


            if(environnement.getCarte().getValeurCase(t.getLigne(), t.getColonne()) == Carte.VIDE)
                t.setOnMousePressed(mouseEvent -> {
                        poserCellules(t.getLigne(), t.getColonne());
                });
        }
    }
}