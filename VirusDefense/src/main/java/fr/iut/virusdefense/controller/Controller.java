package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.maladie.*;
import fr.iut.virusdefense.modele.Terrain;
import fr.iut.virusdefense.modele.Tuiles;
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
    private Terrain terrain;
    private int tailleTuiles;

    @FXML
    public Pane paneMaladie;

    @FXML
    public TilePane tuiles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        terrain = new Terrain();
        terrain.getMaladies().addListener(new ObsListeMaladies(paneMaladie));

        tailleTuiles = 48;
        initTuiles();

        terrain.ajouter(new BactérieBanale(terrain, 0, 5.5));

        initGameLoop();
        gameLoop.play();
    }

    private void initGameLoop(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.017), e -> uneFrame()));
    }

    private void initTuiles(){
        initTailleTuilesEtPaneMaladies();

        for (int i=0; i<terrain.getMap().length; i++)
            for (int j = 0; j < terrain.getMap()[i].length; j++)
                tuiles.getChildren().add(new ImageView(Tuiles.imageDe(terrain.getMap()[i][j])));
    }

    private void initTailleTuilesEtPaneMaladies(){
        double largeurVoulue = tailleTuiles * terrain.getLargeur();
        tuiles.setMaxWidth(largeurVoulue);
        tuiles.setMinWidth(largeurVoulue);
        paneMaladie.setMaxWidth(largeurVoulue);
        paneMaladie.setMinWidth(largeurVoulue);

        double hauteurVoulue = tailleTuiles * terrain.getHauteur();
        tuiles.setMaxHeight(hauteurVoulue);
        tuiles.setMinHeight(hauteurVoulue);
        paneMaladie.setMaxHeight(hauteurVoulue);
        paneMaladie.setMinHeight(hauteurVoulue);
    }

    private void uneFrame(){
        terrain.unTour();
    }
}