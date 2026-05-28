package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SpriteRayon extends Line {

    private final Rayon r;

    public SpriteRayon(Rayon r){
        super();
        this.r = r;
        creerSprite();
    }

    public void creerSprite(){
        setStartX(r.getColonne()*32);
        setStartY(r.getLigne()*32);
        setEndX(r.getCible().getColonne()*32);
        setEndY(r.getCible().getLigne()*32);

        setStroke(Color.WHITE);
        setId(r.getId());
    }

}
