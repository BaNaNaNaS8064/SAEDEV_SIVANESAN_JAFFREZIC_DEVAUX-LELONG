package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GestionnaireEcranDeFin {

    private final Pane paneDessin;

    private ImageView fond;

    private Timeline animation;

    public GestionnaireEcranDeFin(Pane paneDessin){
        this.paneDessin = paneDessin;
        initAnimation();
    }

    public void initFond(String urlImage){
        fond = new ImageView(String.valueOf(Main.class.getResource("images/utilitaires/" + urlImage)));
        fond.setOpacity(0);
        paneDessin.getChildren().add(fond);
    }

    public void initAnimation(){
        animation = new Timeline();
        animation.setCycleCount(10);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(0.05), e ->{
                fond.setOpacity(fond.getOpacity() + 0.1);
        }));
    }

    public void demarrerAnimationDefaite(){
        demarrerAnimation("perdu.png");
    }

    public void demarrerAnimationVictoire(){
        demarrerAnimation("gagne.png");
    }

    private void demarrerAnimation(String urlImage){
        initFond(urlImage);
        animation.playFromStart();
    }

    public void retirerFond(){
        paneDessin.getChildren().remove(fond);
    }
}
