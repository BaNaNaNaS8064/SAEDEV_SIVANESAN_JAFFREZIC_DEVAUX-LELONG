package fr.iut.virusdefense.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GestionnaireEcranDeFin {

    private final Pane paneDessin;

    private Rectangle fond;

    private Label label;

    private Timeline animation;

    public GestionnaireEcranDeFin(Pane paneDessin){
        this.paneDessin = paneDessin;
        initAnimation();
    }

    public void initFond(){
        fond = new Rectangle(paneDessin.getWidth(), paneDessin.getHeight(), Color.BLACK);
        fond.setOpacity(0);
        paneDessin.getChildren().add(fond);
    }

    public void initLabel(String texte){
        label = new Label(texte);
        label.setTextFill(Color.WHITE);
        label.setOpacity(0);
        label.setTranslateX((paneDessin.getWidth() - label.getWidth()) / 2);
        label.setTranslateY((paneDessin.getHeight() - label.getHeight()) / 2);
        paneDessin.getChildren().add(label);
    }

    public void initAnimation(){
        animation = new Timeline();
        animation.setCycleCount(10);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(0.05), e ->{
                fond.setOpacity(fond.getOpacity() + 0.1);
                label.setOpacity(label.getOpacity() + 0.1);
        }));
    }

    public void demarrerAnimationDefaite(){
        demarrerAnimation("Défaite");
    }

    public void demarrerAnimationVictoire(){
        demarrerAnimation("Victoire");
    }

    private void demarrerAnimation(String texte){
        initFond();
        initLabel(texte);
        animation.playFromStart();
    }

}
