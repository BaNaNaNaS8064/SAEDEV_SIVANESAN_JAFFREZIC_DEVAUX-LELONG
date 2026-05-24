package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.vue.sprites.SpriteMaladie;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Observe la liste des maladies et se charge de créer et supprimer les sprites
 */
public class ObsListeMaladies implements ListChangeListener<Maladie> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneDessin;

    public ObsListeMaladies(Pane p){
        paneDessin = p;
    }

    @Override
    public void onChanged(Change<? extends Maladie> c) {
        while (c.next()){
            for (Maladie m : c.getAddedSubList())
                paneDessin.getChildren().add(new SpriteMaladie(m));
            for (Maladie m : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + m.getId()));
        }
    }
}
