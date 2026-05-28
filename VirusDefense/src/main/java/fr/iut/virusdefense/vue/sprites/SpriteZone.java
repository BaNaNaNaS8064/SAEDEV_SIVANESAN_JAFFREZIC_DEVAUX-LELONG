package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpriteZone extends Circle {
    private final Zone c;

    public SpriteZone(Zone c){
        super();
        this.c = c;
        creerSprite();
    }

    public void creerSprite(){
        setCenterX(c.getColonne()*32);
        setCenterY(c.getLigne()*32);
        setRadius(c.getRayonZone()*32);


        setFill(Color.LIGHTGRAY);
        setOpacity(0.2);
        setId(c.getId());
    }

}
