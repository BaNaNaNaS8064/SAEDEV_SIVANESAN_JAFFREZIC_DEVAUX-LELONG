package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;



import fr.iut.virusdefense.modele.maladie.*;
import fr.iut.virusdefense.modele.Terrain;
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
        tuiles.setMaxWidth(tailleTuiles * terrain.getLargeur());
        tuiles.setMaxHeight(tailleTuiles * terrain.getHauteur());
        tuiles.setMinWidth(tailleTuiles * terrain.getLargeur());
        tuiles.setMinHeight(tailleTuiles * terrain.getHauteur());
        paneMaladie.setMaxWidth(tailleTuiles * terrain.getLargeur());
        paneMaladie.setMaxHeight(tailleTuiles * terrain.getHauteur());
        paneMaladie.setMinWidth(tailleTuiles * terrain.getLargeur());
        paneMaladie.setMinHeight(tailleTuiles * terrain.getHauteur());

        tuiles.getChildren().clear();
        for (int i=0; i<terrain.getMap().length; i++) {
            for (int j = 0; j < terrain.getMap()[i].length; j++) {
                tuiles.getChildren().add(new ImageView(String.valueOf(Main.class.getResource("tuiles/Tuile" + terrain.getMap()[i][j] + ".png"))));
            }
        }
    }

    private void uneFrame(){
        terrain.unTour();
    }
}
