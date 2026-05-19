package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.cellule.Cellule;
import fr.iut.virusdefense.modele.cellule.Sainple;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.vue.Tuiles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Timeline gameLoop;
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

        tailleTuiles = 48;
        initTuilesEtPaneMaladies();

        environnement.ajouter(new BactérieBanale(environnement, 0, 2));
        Cellule c1 = new Sainple(environnement , 3 , 2);
        c1.reconnaissanceEnnemi();

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
     * Initialise tuiles et paneMaladie
     */
    private void initTuilesEtPaneMaladies(){
        initTailleTuilesEtPaneMaladies();

        for (int i = 0; i< environnement.getMap().getHauteur(); i++)
            for (int j = 0; j < environnement.getMap().getLargeur(); j++)
                tuiles.getChildren().add(new ImageView(Tuiles.imageDe(environnement.getMap().getValeurCase(i,j))));
    }

    /**
     * Fixe les tailles de tuiles et paneMaladie
     */
    private void initTailleTuilesEtPaneMaladies(){
        double largeurVoulue = tailleTuiles * environnement.getMap().getLargeur();
        tuiles.setMaxWidth(largeurVoulue);
        tuiles.setMinWidth(largeurVoulue);
        paneMaladie.setMaxWidth(largeurVoulue);
        paneMaladie.setMinWidth(largeurVoulue);

        double hauteurVoulue = tailleTuiles * environnement.getMap().getHauteur();
        tuiles.setMaxHeight(hauteurVoulue);
        tuiles.setMinHeight(hauteurVoulue);
        paneMaladie.setMaxHeight(hauteurVoulue);
        paneMaladie.setMinHeight(hauteurVoulue);
    }

    /**
     * Méthode exécutée à chaque tour
     */
    private void uneFrame(){
        environnement.unTour();
    }
}