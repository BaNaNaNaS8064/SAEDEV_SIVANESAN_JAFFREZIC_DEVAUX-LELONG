package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        ImageView img = new ImageView(String.valueOf(Main.class.getResource("maladies/BB.png")));

        img.translateXProperty().bind(m.colonneProperty().multiply(48));
        img.translateYProperty().bind(m.ligneProperty().multiply(48));
        img.setId(m.getId());

        paneMaladies.getChildren().add(img);
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
