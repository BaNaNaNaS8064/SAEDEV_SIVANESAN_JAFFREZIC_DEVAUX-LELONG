package fr.iut.virusdefense.vue.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tuile extends ImageView {
    private final int ligne;
    private final int colonne;

    public Tuile(String url, int ligne, int colonne){
        super(url);
        this.ligne = ligne;
        this.colonne = colonne;
        setRotate((int)(Math.random() * 4) * 90);
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setImage(String url){
        setImage(new Image(url));
    }
}
