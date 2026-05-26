package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.cellules.attaque.Attaque;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration {
    private int dureeDeVie;

    public Alteration(int dureeDeVie){
        this.dureeDeVie = dureeDeVie;
    }

    public int getDureeDeVie() {
        return dureeDeVie;
    }

    public void agir(Maladie m){
        dureeDeVie--;
    }

    public abstract Alteration copieAlteration();
}
