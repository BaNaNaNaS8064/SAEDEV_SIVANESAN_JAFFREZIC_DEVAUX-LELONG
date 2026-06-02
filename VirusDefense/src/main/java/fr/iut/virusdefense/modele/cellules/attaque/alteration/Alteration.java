package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration {
    private int dureeDeVie;
    private Maladie m;

    public Alteration(int dureeDeVie){
        this.dureeDeVie = dureeDeVie;
    }

    public Maladie getM() {
        return m;
    }

    public void setMaladie(Maladie m) {
        this.m = m;
    }

    public boolean finDeVie(){
        return (0 >= dureeDeVie || !m.estVivant());
    }

    public void agir(){
        dureeDeVie--;
        affecter(this.m);
    }

    public abstract void affecter(Maladie m);
}
