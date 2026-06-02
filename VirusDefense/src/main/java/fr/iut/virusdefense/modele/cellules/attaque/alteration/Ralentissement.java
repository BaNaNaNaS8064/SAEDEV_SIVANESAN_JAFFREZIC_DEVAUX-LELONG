package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public class Ralentissement extends Alteration{
    private double coefVitesse;

    public Ralentissement(int dureeDeVie, double coefVitesse){
        super(dureeDeVie);
        this.coefVitesse = coefVitesse;
    }

    @Override
    public void affecter(Maladie m) {
        m.ralentir(coefVitesse);
    }
}
