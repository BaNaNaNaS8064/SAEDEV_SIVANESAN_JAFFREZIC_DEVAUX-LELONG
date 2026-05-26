package fr.iut.virusdefense.modele.cellules.attaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public class Ralentissement extends Alteration{
    private double coefficientVitesse;

    public Ralentissement(double coefficientVitesse, int dureeDeVie){
        super(dureeDeVie);
        this.coefficientVitesse = coefficientVitesse;
    }

    @Override
    public Alteration copieAlteration() {
        return new Ralentissement(coefficientVitesse,getDureeDeVie());
    }

    @Override
    public void agir(Maladie m) {
        super.agir(m);
        m.ralentir(coefficientVitesse);
    }
}
