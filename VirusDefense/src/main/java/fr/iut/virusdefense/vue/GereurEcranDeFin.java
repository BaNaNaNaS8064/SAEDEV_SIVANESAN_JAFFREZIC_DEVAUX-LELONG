package fr.iut.virusdefense.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GereurEcranDeFin {

    private final Pane paneEcranFin;

    public GereurEcranDeFin(Pane paneEcranFin){
        this.paneEcranFin = paneEcranFin;
    }

    public void demarrerAnimation(){
        Rectangle r = new Rectangle(paneEcranFin.getWidth(), paneEcranFin.getHeight(), Color.BLACK);
        r.setOpacity(0);

        Timeline animation = new Timeline();
        animation.setCycleCount(10);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1), e -> {
            r.setOpacity(r.getOpacity() + 0.1);
            System.out.println("frame animmmmm opp" + r.getOpacity());
        }));

        animation.playFromStart();

        paneEcranFin.getChildren().add(r);
    }

}
