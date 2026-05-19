package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.modele.Environnement;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * Un afficheur de carte se charge de lire la carte dans le modèle puisde l'afficher
 * dans le TilePane dans la vue
 */
public class AfficheurDeCarte {

    private Environnement environnement;
    private TilePane carte;

    public AfficheurDeCarte(Environnement environnement, TilePane carte) {
        this.environnement = environnement;
        this.carte = carte;
        resetCarte();
    }

    /**
     * Construit la carte dans la vue
     */
    public void resetCarte(){
        resetTailleCarte();

        for (int i = 0; i< environnement.getMap().getHauteur(); i++)
            for (int j = 0; j < environnement.getMap().getLargeur(); j++)
                carte.getChildren().add(new ImageView(SpritesTuiles.imageDe(environnement.getMap().getValeurCase(i,j))));
    }

    /**
     * Fixe la taille de la carte dans la vue
     */
    private void resetTailleCarte(){
        double largeurVoulue = 48 * environnement.getMap().getLargeur();
        carte.setMaxWidth(largeurVoulue);
        carte.setMinWidth(largeurVoulue);

        double hauteurVoulue = 48 * environnement.getMap().getHauteur();
        carte.setMaxHeight(hauteurVoulue);
        carte.setMinHeight(hauteurVoulue);
    }

}
