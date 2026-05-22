package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Rayon;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Observe la liste des maladies et se charge de créer et supprimer les sprites
 */
public class ObsListeTir implements ListChangeListener<Rayon> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneDessin;

    public ObsListeTir(Pane p){
        paneDessin = p;
    }

    public void creerSprite(Rayon t){
        Line spriteTir = new Line(t.getColonne()*32, t.getLigne()*32, t.getColonne2()*32, t.getLigne2()*32);
        spriteTir.setStroke(Color.WHITE);
        spriteTir.setId(t.getId());
        paneDessin.getChildren().add(spriteTir);
    }

    @Override
    public void onChanged(Change<? extends Rayon> c) {

        while (c.next()){
            for (Rayon t : c.getAddedSubList())
                creerSprite(t);
            for (Rayon t : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + t.getId()));
        }
    }
}

