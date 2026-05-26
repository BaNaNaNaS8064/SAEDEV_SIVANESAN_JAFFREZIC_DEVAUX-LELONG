package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public class Dot extends Alteration {
    private int degats;

    public Dot(int degats , int dureeDeVie ) {
        super(dureeDeVie);
        this.degats = degats;
    }

    public int getDegats() {
        return degats;
    }

    @Override
    public Alteration copieAlteration() {
        return new Dot(degats,getDureeDeVie());
    }

    @Override
    public void agir(Maladie m){
        super.agir(m);
        m.prendreDegats(getDegats());
    }
}
