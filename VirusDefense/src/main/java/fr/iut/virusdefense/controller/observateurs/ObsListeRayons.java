package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Observe la liste des maladies et se charge de créer et supprimer les sprites
 */
public class ObsListeRayons implements ListChangeListener<Rayon> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneDessin;

    public ObsListeRayons(Pane p){
        paneDessin = p;
    }

    public void creerSprite(Rayon r){
        Line spriteTir = new Line(r.getColonne()*32, r.getLigne()*32, r.getColonne2()*32, r.getLigne2()*32);
        spriteTir.setStroke(Color.WHITE);
        spriteTir.setId(r.getId());
        paneDessin.getChildren().add(spriteTir);
    }

    @Override
    public void onChanged(Change<? extends Rayon> c) {
        while (c.next()){
            for (Rayon r : c.getAddedSubList())
                creerSprite(r);
            for (Rayon r : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + r.getId()));
        }
    }
}

