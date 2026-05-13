package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Terrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Timeline gameLoop;
    private Terrain terrain;

    @FXML
    public TilePane tuiles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        terrain = new Terrain();
        System.out.println(Arrays.deepToString(terrain.getMap()));
        initTuiles();

        initGameLoop();
        gameLoop.play();
    }

    private void initGameLoop(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds((double)1/16), e -> uneFrame()));
    }

    private void initTuiles(){
        int tailleTuiles = 32;
        tuiles.setMaxWidth(tailleTuiles * terrain.getLargeur());
        tuiles.setMaxHeight(tailleTuiles * terrain.getHauteur());
        for (int i=0; i<terrain.getMap().length; i++)
            for (int j=0; j<terrain.getMap()[i].length; j++)
                tuiles.getChildren().add(new Rectangle(tailleTuiles, tailleTuiles, switch(terrain.getMap()[i][j]){
                    case 0 -> Color.BLACK;
                    case 1 -> Color.DARKRED;
                    default -> Color.PURPLE;
                }));

    }

    private void uneFrame(){

    }
}
