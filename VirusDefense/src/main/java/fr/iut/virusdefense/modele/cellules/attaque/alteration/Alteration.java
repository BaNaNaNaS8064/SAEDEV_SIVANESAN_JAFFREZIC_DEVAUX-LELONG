package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration {
    private int dureeDeVie;

    public Alteration(int dureeDeVie){
        this.dureeDeVie = dureeDeVie;
    }

    public int getDureeDeVie() {
        return dureeDeVie;
    }

    public abstract Alteration copieAlteration();

    public void agir(Maladie m){
        dureeDeVie--;
        affecter(m);
    }

    public abstract void affecter(Maladie m);
}
