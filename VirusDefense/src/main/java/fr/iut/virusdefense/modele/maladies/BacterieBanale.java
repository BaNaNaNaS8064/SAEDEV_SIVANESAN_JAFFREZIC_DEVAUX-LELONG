package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class BacterieBanale extends Maladie{

    public BacterieBanale(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 150, 0.01, 10);
    }
}
