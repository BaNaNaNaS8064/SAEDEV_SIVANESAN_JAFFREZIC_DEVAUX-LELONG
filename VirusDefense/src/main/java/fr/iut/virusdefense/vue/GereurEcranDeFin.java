package fr.iut.virusdefense.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GereurEcranDeFin {

    private final Pane paneDessin;

    private Rectangle fond;

    private Timeline animation;

    public GereurEcranDeFin(Pane paneDessin){
        this.paneDessin = paneDessin;
        initAnimation();
    }

    public void initFond(Pane paneDessin){
        fond = new Rectangle(paneDessin.getWidth(), paneDessin.getHeight(), Color.BLACK);
        fond.setOpacity(0);
        paneDessin.getChildren().add(fond);
    }

    public void initAnimation(){
        animation = new Timeline();
        animation.setCycleCount(10);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1), e ->
                fond.setOpacity(fond.getOpacity() + 0.1)
        ));
    }

    public void demarrerAnimation(){
        initFond(paneDessin);
        animation.playFromStart();
    }

}
