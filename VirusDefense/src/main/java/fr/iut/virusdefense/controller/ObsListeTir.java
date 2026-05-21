package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Tir;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Observe la liste des maladies et se charge de créer et supprimer les sprites
 */
public class ObsListeTir implements ListChangeListener<Tir> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneMaladies;

    public ObsListeTir(Pane p){
        paneMaladies = p;
    }

    public void creerSprite(Tir t){
        Line spriteTir = new Line(t.getColonne()*48, t.getLigne()*48, t.getColonneArrivee()*48, t.getLigneArrivee()*48);
        spriteTir.setFill(Color.WHITE);
        spriteTir.setId(t.getId());
        paneMaladies.getChildren().add(spriteTir);
    }

    @Override
    public void onChanged(Change<? extends Tir> c) {

        while (c.next()){
            for (Tir t : c.getAddedSubList())
                creerSprite(t);
            for (Tir t : c.getRemoved())
                paneMaladies.getChildren().remove(paneMaladies.lookup("#" + t.getId()));
        }
    }
}

