package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class RecSimple extends Reconnaissance{

    public RecSimple (double ligne, double colonne, List<Maladie> maladies, double portee, int nombreCiblesMax){
        super(ligne, colonne, maladies, portee, nombreCiblesMax);
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m) && m.voit(getLigne(), getColonne(),true);
    }
}
