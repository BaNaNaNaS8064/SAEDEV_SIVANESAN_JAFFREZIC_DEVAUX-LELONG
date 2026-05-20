package fr.iut.virusdefense.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tuile extends ImageView {
    private final int ligne;
    private final int colonne;

    public Tuile(String url, int ligne, int colonne){
        super(url);
        this.ligne = ligne;
        this.colonne = colonne;
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
