package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class Parasite extends Maladie{

    public Parasite(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 120, 0.02, 7);
    }
}