package fr.iut.virusdefense.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tuiles extends ImageView {
    private final int ligne;
    private final int colonne;

    public Tuiles(String image_path, int ligne, int colonne){
        super(image_path);
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setImagePath(String image_path){
        super.setImage(new Image(image_path));
    }
}
