package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration {
    private int dureeDeVie;
    private Maladie m;

    public Alteration(int dureeDeVie){
        this.dureeDeVie = dureeDeVie;
    }

    public int getDureeDeVie() {
        return dureeDeVie;
    }

    public void setMaladie(Maladie m) {
        this.m = m;
    }

    public abstract Alteration copieAlteration();

    public void agir(){
        dureeDeVie--;
        affecter(this.m);
    }

    public Maladie getM() {
        return m;
    }

    public boolean finDeVie(){
        return (0 >= dureeDeVie || !m.estVivant());
    }

    public abstract void affecter(Maladie m);
}
