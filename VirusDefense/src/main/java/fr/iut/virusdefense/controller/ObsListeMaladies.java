package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Maladie;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ObsListeMaladies implements ListChangeListener<Maladie> {

    private final Pane paneMaladies;

    public ObsListeMaladies(Pane p){
        paneMaladies = p;
    }

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
