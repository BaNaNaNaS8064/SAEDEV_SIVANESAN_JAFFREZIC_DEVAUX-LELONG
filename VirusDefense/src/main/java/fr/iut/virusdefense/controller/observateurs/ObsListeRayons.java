package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.vue.sprites.SpriteRayon;
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

    @Override
    public void onChanged(Change<? extends Rayon> c) {
        while (c.next()){
            for (Rayon r : c.getAddedSubList())
                paneDessin.getChildren().add(new SpriteRayon(r));
            for (Rayon r : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + r.getId()));
        }
    }
}

