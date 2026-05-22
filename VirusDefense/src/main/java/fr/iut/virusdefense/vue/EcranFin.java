package fr.iut.virusdefense.vue;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EcranFin {
    public EcranFin(Pane parent, boolean victoire){

        Group grp = new Group();

        Rectangle rect = new Rectangle(parent.getWidth(),parent.getHeight());
        rect.setFill(Color.rgb(0,0,0, 0.1));
        Label lbFin = new Label();
        if(victoire) lbFin.setText("Victoire");
        else lbFin.setText("Défaite");
        lbFin.setTextFill(Color.WHITE);
        lbFin.setTranslateX(parent.getWidth()/2 - lbFin.getWidth()/2);
        lbFin.setTranslateY(parent.getHeight()/2 - lbFin.getHeight()/2);

        grp.getChildren().addAll(rect,lbFin);
        parent.getChildren().add(grp);
    }
}
