package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class Tumeur extends Maladie{
    public Tumeur(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 5000, 0.005, 500);
    }

    @Override
    public void faireJouerAlterations() {
        getAlterations().clear();
    }
}