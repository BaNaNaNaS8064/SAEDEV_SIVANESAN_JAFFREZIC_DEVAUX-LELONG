package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.maladie.Maladie;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Observe la liste des maladies et se charge de créer et supprimer les sprites
 */
public class ObsListeMaladies implements ListChangeListener<Maladie> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneMaladies;

    public ObsListeMaladies(Pane p){
        paneMaladies = p;
    }

    /**
     * Créé un sprite pour m et l'ajoute dans paneMaladies
     * @param m une maladie
     */
    private void creerSprite(Maladie m){
        Circle c = new Circle(10);

        c.setFill(Color.GREEN);
        c.translateXProperty().bind(m.xProperty().multiply(48));
        c.translateYProperty().bind(m.yProperty().multiply(48));
        c.setId(c.getId());

        paneMaladies.getChildren().add(c);
    }

    @Override
    public void onChanged(Change<? extends Maladie> c) {
        while (c.next()){
            for (Maladie m : c.getAddedSubList())
                creerSprite(m);
            for (Maladie m : c.getRemoved())
                paneMaladies.getChildren().remove(paneMaladies.lookup("#" + m.getId()));
        }
    }
}
