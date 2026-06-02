package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public class Dot extends Alteration {
    private int degats;

    public Dot(int dureeDeVie, int degats) {
        super(dureeDeVie);
        this.degats = degats;
    }

    @Override
    public void affecter(Maladie m){
        m.prendreDegats(degats);
    }
}
