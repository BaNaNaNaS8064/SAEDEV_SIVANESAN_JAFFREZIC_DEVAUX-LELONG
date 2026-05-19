package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.modele.Terrain;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class AffichageCarte {

    private Terrain terrain;
    private TilePane carte;

    public AffichageCarte(Terrain terrain, TilePane carte) {
        this.terrain = terrain;
        this.carte = carte;
        resetCarte();
    }

    /**
     * Construit la carte dans la vue
     */
    public void resetCarte(){
        resetTailleCarte();

        for (int i=0; i<terrain.getMap().length; i++)
            for (int j = 0; j < terrain.getMap()[i].length; j++)
                carte.getChildren().add(new ImageView(SpritesTuiles.imageDe(terrain.getMap()[i][j])));
    }

    /**
     * Fixe la taille de la carte dans la vue
     */
    private void resetTailleCarte(){
        double largeurVoulue = 48 * terrain.getLargeur();
        carte.setMaxWidth(largeurVoulue);
        carte.setMinWidth(largeurVoulue);

        double hauteurVoulue = 48 * terrain.getHauteur();
        carte.setMaxHeight(hauteurVoulue);
        carte.setMinHeight(hauteurVoulue);
    }

}
