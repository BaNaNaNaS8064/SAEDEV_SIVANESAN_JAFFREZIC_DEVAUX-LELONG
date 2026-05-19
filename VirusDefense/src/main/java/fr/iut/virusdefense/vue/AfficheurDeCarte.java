package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.modele.Terrain;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * Un afficheur de carte se charge de lire la carte dans le modèle puisde l'afficher
 * dans le TilePane dans la vue
 */
public class AfficheurDeCarte {

    private Terrain terrain;
    private TilePane carte;

    public AfficheurDeCarte(Terrain terrain, TilePane carte) {
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
