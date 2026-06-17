package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.controller.AssociationImage;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.vue.sprites.Tuile;
import javafx.scene.layout.TilePane;

/**
 * Un afficheur de carte se charge de lire la carte dans le modèle puis de l'afficher
 * dans le TilePane dans la vue
 */
public class AfficheurDeCarte {

    private Environnement environnement;
    private TilePane carte;
    private AfficheurDeChemin afficheurDeChemin;

    public AfficheurDeCarte(Environnement environnement, TilePane carte, AfficheurDeChemin afficheurDeChemin) {
        this.environnement = environnement;
        this.carte = carte;
        this.afficheurDeChemin = afficheurDeChemin;
        resetCarte();
    }

    /**
     * Construit la carte dans la vue
     */
    public void resetCarte(){
        resetTailleCarte();

        carte.getChildren().clear();

        for (int i = 0; i< environnement.getCarte().getHauteur(); i++)
            for (int j = 0; j < environnement.getCarte().getLargeur(); j++) {
                carte.getChildren().add(new Tuile(AssociationImage.imageDe(environnement.getCarte().getCode(i, j)), i, j));
            }
        afficheurDeChemin.dessinerChemin();
    }

    public void rechargerEmplacement(int ligne, int colonne){
        Tuile emplacement = (Tuile) carte.getChildren().get(ligne * environnement.getCarte().getLargeur() + colonne);
        emplacement.setImage(AssociationImage.imageDe(environnement.getCarte().getCode(ligne, colonne)));
        afficheurDeChemin.dessinerChemin();
    }

    /**
     * Fixe la taille de la carte dans la vue
     */
    private void resetTailleCarte(){
        double largeurVoulue = 32 * environnement.getCarte().getLargeur();
        carte.setMaxWidth(largeurVoulue);
        carte.setMinWidth(largeurVoulue);

        double hauteurVoulue = 32 * environnement.getCarte().getHauteur();
        carte.setMaxHeight(hauteurVoulue);
        carte.setMinHeight(hauteurVoulue);
    }

}
